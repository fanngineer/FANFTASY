package com.a306.fanftasy.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private  Long userId;
    private String address;
    private String profileImg;
    private String nickname;
    private String role;
    public static UserLoginDTO of(Long userId,String nickname,String address,String role,String profileImg){
        return UserLoginDTO.builder()
                .userId(userId)
                .nickname(nickname)
                .address(address)
                .role(role)
                .profileImg(profileImg)
                .build();
    }
}
