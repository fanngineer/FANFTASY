package com.a306.fanftasy.domain.user.service;

import com.a306.fanftasy.domain.user.dto.UserDetailDTO;
import com.a306.fanftasy.domain.user.dto.UserJoinDTO;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.dto.UserUpdateDTO;
import com.a306.fanftasy.domain.user.entity.User;

public interface UserService {
 UserLoginDTO login(String address) ;

 void join(UserJoinDTO userJoinDTO);


 UserDetailDTO getUserDetail(String addresss);

 void updateUser(UserUpdateDTO userUpdateDTO);

 User findByUserId(Long userId);
}
