package com.a306.fanftasy.domain.user.controller;


import com.a306.fanftasy.domain.user.dto.UserDetailDTO;
import com.a306.fanftasy.domain.user.dto.UserJoinDTO;
import com.a306.fanftasy.domain.user.dto.UserUpdateDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(String address){

        return ResponseEntity.ok(userService.login(address));
    }

    @PostMapping("/join")
    public String join(@RequestBody UserJoinDTO userJoinDTO){
        userService.join(userJoinDTO);
        return "회원가입 완료";
    }

    @GetMapping("/{address}")
    public UserDetailDTO userDetail(String address){
        return userService.getUserDetail(address);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO ){
       userService.updateUser(userUpdateDTO);
        return ResponseEntity.accepted().build();
    }
}
