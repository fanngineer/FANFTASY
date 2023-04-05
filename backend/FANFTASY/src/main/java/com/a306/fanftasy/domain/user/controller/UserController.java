package com.a306.fanftasy.domain.user.controller;


import com.a306.fanftasy.domain.nft.service.EthereumService;
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
    private final EthereumService ethereumService;
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
        ResponseDefault responseDefault = null;
        try {
            userService.join(userJoinDTO);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        } catch (Exception e) {
            log.error("회원 가입 실패");
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> userDetail(@Param("address") String address) throws IOException {
        ResponseDefault responseDefault = null;
        try {
            UserDetailDTO userDetailDTO=userService.getUserDetail(address);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .data(userDetailDTO)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        } catch (Exception e) {
            log.error("상세 정보 조회 실패");
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    @PostMapping("/profile/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @RequestPart("profileImg") MultipartFile profileImg) {
        log.info("이미지 변경 요청");
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
                        .messege("SUCCESS")
                        .data(user.getProfileImg())
                        .build();
                return new ResponseEntity<>(responseDefault, HttpStatus.OK);
            } catch (IOException e) {
                responseDefault = ResponseDefault.builder()
                        .success(false)
                        .messege("FAIL")
                        .data(null)
                        .build();
                return new ResponseEntity<>(responseDefault, HttpStatus.NOT_FOUND);
            }
        }

    }

    @PutMapping("/modi")//수정클릭
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        ResponseDefault responseDefault = null;
        try {
            userService.updateUser(userUpdateDTO);
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .data(null)
                    .build();
            return new ResponseEntity<>(responseDefault, HttpStatus.OK);
        } catch (Exception e) {
            log.error("회원 정보 수정 실패");
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
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

    @GetMapping("/charge")
    public ResponseEntity<?> charge(@RequestParam Long id,@RequestParam double ether) {
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                .messege("SUCCESS")
                .data(ethereumService.charge(id, ether))
                .success(true).build();
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            responseDefault = ResponseDefault.builder()
                .success(false)
                .messege("FAIL").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }
}
