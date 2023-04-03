package com.a306.fanftasy.domain.user.controller;


import com.a306.fanftasy.domain.response.ResponseDefault;
import com.a306.fanftasy.domain.user.dto.UserDetailDTO;
import com.a306.fanftasy.domain.user.dto.UserJoinDTO;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.dto.UserUpdateDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.service.S3Service;
import com.a306.fanftasy.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.aspectj.bridge.Message;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.a306.fanftasy.util.JwtTokenUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final S3Service s3Service;
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

    @PutMapping("/profile/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @RequestPart("profileImg") MultipartFile profileImg) {
    String imgUrl = null;
    ResponseDefault responseDefault = null;
    User user = userService.findByUserId(id);
    if (user == null) {
        responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("유저 없음")
                .data(null)
                .build();
        return new ResponseEntity<>(responseDefault, HttpStatus.NOT_FOUND);
    } else {
            try {
                s3Service.saveUploadFile(user, profileImg);
                responseDefault = ResponseDefault.builder()
                        .success(true)
                        .messege("성공")
                        .data(null)
                        .build();
                return new ResponseEntity<>(responseDefault, HttpStatus.OK);
            } catch (IOException e) {
                responseDefault = ResponseDefault.builder()
                        .success(false)
                        .messege("실패")
                        .data(null)
                        .build();
                return new ResponseEntity<>(responseDefault, HttpStatus.NOT_FOUND);
            }
        }

    }

    @PutMapping("/modi")//수정클릭
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        userService.updateUser(userUpdateDTO);
        ResponseDefault responseDefault = null;
        responseDefault = ResponseDefault.builder()
                .success(true)
                .messege("SUCCESS")
                .data(null)
                .build();
        return new ResponseEntity<>(responseDefault, HttpStatus.OK);
    }

    @GetMapping("/modi/{id}")//주는거
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id){
        User user = userService.findByUserId(id);
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        UserUpdateDTO updateDTO = userUpdateDTO.fromEntity(user);
        ResponseDefault responseDefault = null;
        if (user != null) {
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .data(updateDTO)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        } else {
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@PathVariable("id") Long id) {
            User user = userService.findByUserId(id);
            userService.logout(user.getUserId());
            return ResponseEntity.accepted().build();
        }

}
