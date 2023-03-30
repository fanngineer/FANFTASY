package com.a306.fanftasy.domain.user.controller;


import com.a306.fanftasy.domain.response.ResponseDefault;
import com.a306.fanftasy.domain.user.dto.UserDetailDTO;
import com.a306.fanftasy.domain.user.dto.UserJoinDTO;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.dto.UserUpdateDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.a306.fanftasy.util.JwtTokenUtil;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@Param("address") String address) {
        UserLoginDTO userLoginDTO = userService.login(address);
        HttpHeaders headers = new HttpHeaders();
        ResponseDefault responseDefault = null;
        String accessToken = null;

        if (userLoginDTO != null) {//멤버 O
            accessToken = jwtTokenUtil.generateAccessToken(address,userLoginDTO.getUserId(),userLoginDTO.getProfileImg(),userLoginDTO.getNickname(),userLoginDTO.getRole());
            headers.set("accessToken", accessToken);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .data(userLoginDTO)
                    .build();
            return ResponseEntity.ok().headers(headers).body(responseDefault);
        } else {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinDTO userJoinDTO) {
        userService.join(userJoinDTO);
        ResponseDefault responseDefault = null;
        responseDefault = ResponseDefault.builder()
                .success(true)
                .messege("SUCCESS")
                .data(null)
                .build();
        return new ResponseEntity<>(responseDefault, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> userDetail(@Param("address") String address){
        UserDetailDTO userDetailDTO=userService.getUserDetail(address);
        ResponseDefault responseDefault = null;
        responseDefault = ResponseDefault.builder()
                .success(true)
                .messege("SUCCESS")
                .data(userDetailDTO)
                .build();
        return new ResponseEntity<>(responseDefault, HttpStatus.OK);
    }

    @PutMapping("/modi")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO ){
       userService.updateUser(userUpdateDTO);
        ResponseDefault responseDefault = null;
        responseDefault = ResponseDefault.builder()
                .success(true)
                .messege("SUCCESS")
                .data(null)
                .build();
        return new ResponseEntity<>(responseDefault, HttpStatus.OK);
    }
}
