package com.a306.fanftasy.domain.user.service;

import com.a306.fanftasy.domain.nft.service.EthereumService;
import com.a306.fanftasy.domain.user.dto.UserDetailDTO;
import com.a306.fanftasy.domain.user.dto.UserJoinDTO;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.dto.UserUpdateDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import com.a306.fanftasy.util.JwtTokenUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtTokenUtil jwtTokenUtil;
  private final S3Service s3Service;
  private final EthereumService ethereumService;

    private final UserSecurityService userSecurityService;

    public UserLoginDTO login(String address){
        User user = userRepository.findByAddress(address);
        if(user!=null){
                  try {
                Long userId=user.getUserId();
                String nickname=user.getNickname();
                String profileImg=userSecurityService.aesDecrypt(user.getProfileImg(),userSecurityService.hexToByteArray(user.getUserKey()));
                String role = userSecurityService.aesDecrypt(user.getRole(),userSecurityService.hexToByteArray(user.getUserKey()));
                return UserLoginDTO.of(userId,nickname,address,role,profileImg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
        else{
            return null;
        }
    }

    @Override
    public void join(UserJoinDTO userJoinDTO)   {
        UserJoinDTO userencrypt=new UserJoinDTO();
        if(userJoinDTO.getProfileImg()==null){
            userJoinDTO.setProfileImg("https://fanftasy.s3.ap-northeast-2.amazonaws.com/profileImg/8c64c983-1b80-40fb-bcc1-366f3322cbb2.png");//default 이미지
        }
        try {
            byte[] key=userSecurityService.generateKey("AES",128);
            userencrypt.setAddress(userJoinDTO.getAddress());
            userencrypt.setName(userSecurityService.aesEncrypt(userJoinDTO.getName(),key));
            userencrypt.setNickname(userJoinDTO.getNickname());
            userencrypt.setEmail(userSecurityService.aesEncrypt(userJoinDTO.getEmail(),key));
            userencrypt.setPhone(userSecurityService.aesEncrypt(userJoinDTO.getPhone(),key));
            userencrypt.setRole(userSecurityService.aesEncrypt(userJoinDTO.getRole(),key));
            userencrypt.setCompany(userSecurityService.aesEncrypt(userJoinDTO.getCompany(),key));
            userencrypt.setProfileImg(userSecurityService.aesEncrypt(userJoinDTO.getProfileImg(),key));
            userencrypt.setKey(userSecurityService  .byteArrayToHex(key));
            //userJoinDTO.setNickname(userencrypt.getNickname());
            // log.info("Nickname: "+userJoinDTO.getNickname());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        userRepository.save(User.ofUser(userencrypt));
    }

    @Override
    public UserDetailDTO getUserDetail(String address) throws IOException {
        User user = userRepository.findByAddress(address);
        double balance = ethereumService.getBalance(address);
        try {
            return UserDetailDTO.builder()
                    .name(userSecurityService.aesDecrypt(user.getName(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .address(user.getAddress())
                    .email(userSecurityService.aesDecrypt(user.getEmail(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .nickname(user.getNickname())
                    .profileImg(userSecurityService.aesDecrypt(user.getProfileImg(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .phone(userSecurityService.aesDecrypt(user.getPhone(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .role(userSecurityService.aesDecrypt(user.getRole(),userSecurityService.hexToByteArray(user.getUserKey())))
                    .totalPrice(user.getTotalPrice())
                    .totalSales(user.getTotalSales())
                    .balance(balance)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findByAddress(userUpdateDTO.getAddress());
        byte[] key=userSecurityService.hexToByteArray(user.getUserKey());
        log.info(userUpdateDTO.getNickname());
        try {
            user.setAddress(userUpdateDTO.getAddress());
            user.setNickname(userUpdateDTO.getNickname());
            if (userUpdateDTO.getProfileImg() != null) {
               user.setProfileImg (userSecurityService.aesEncrypt(userUpdateDTO.getProfileImg(),key));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        if (StringUtils.hasText(userUpdateDTO.getNickname())) {
//            user.setNickname(userUpdateDTO.getNickname());
//        }
//        if (StringUtils.hasText(userUpdateDTO.getProfileImg())) {
//            user.setProfileImg(userUpdateDTO.getProfileImg());
//        }

    userRepository.save(user);
  }

  @Override
  public User findByUserId(Long userId) {
    User user = userRepository.findByUserId(userId);
    return user;
  }

  @Override
  public void logout(Long userId) {

  }


}
