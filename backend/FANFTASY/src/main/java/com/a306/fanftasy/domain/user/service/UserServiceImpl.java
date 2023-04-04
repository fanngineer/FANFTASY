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

  public UserLoginDTO login(String address) {
    log.info(address);
    User user = userRepository.findByAddress(address);
    if (user != null) {
      Long userId = user.getUserId();
      String nickname = user.getNickname();
      String profileImg = user.getProfileImg();
      String role = user.getRole();
      return UserLoginDTO.of(userId, nickname, address, role, profileImg);
    } else {
      return null;
    }
  }

  @Override
  public void join(UserJoinDTO userJoinDTO) {
    userRepository.save(User.ofUser(userJoinDTO));
  }

  @Override
  public UserDetailDTO getUserDetail(String addresss) throws IOException {
    User user = userRepository.findByAddress(addresss);
    double balance = ethereumService.getBalance(addresss);
    return UserDetailDTO.builder()
        .name(user.getName())
        .address(user.getAddress())
        .email(user.getEmail())
        .nickname(user.getNickname())
        .profileImg(user.getProfileImg())
        .phone(user.getPhone())
        .role(user.getRole())
        .totalPrice(user.getTotalPrice())
        .totalSales(user.getTotalSales())
        .balance(balance)
        .build();
  }

  @Override
  public void updateUser(UserUpdateDTO userUpdateDTO) {
    User user = userRepository.findByAddress(userUpdateDTO.getAddress());
    log.info(userUpdateDTO.getNickname());
    user.setAddress(userUpdateDTO.getAddress());
    user.setEmail(userUpdateDTO.getEmail());
    user.setNickname(userUpdateDTO.getNickname());

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
