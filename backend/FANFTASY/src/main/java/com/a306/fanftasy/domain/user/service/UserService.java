package com.a306.fanftasy.domain.user.service;

import com.a306.fanftasy.domain.user.dto.*;
import com.a306.fanftasy.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
 UserLoginDTO login(String address) ;

 void join(UserJoinDTO userJoinDTO);


 UserDetailDTO getUserDetail(String address) throws IOException;

 void updateUser(UserUpdateDTO userUpdateDTO);

 User findByUserId(Long userId);

 void logout(Long userId);

 UserOtherDetailDTO getOtherUserDetail(long userId);
}
