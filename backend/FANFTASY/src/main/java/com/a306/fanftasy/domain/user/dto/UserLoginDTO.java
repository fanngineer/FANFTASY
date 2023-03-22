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
    private String address;
    private String nickname;
    private String accessToken;
    public static UserLoginDTO of(String accessToken, String nickname,String address){
        return UserLoginDTO.builder()
                .nickname(nickname)
                .accessToken(accessToken)
                .address(address)
                .build();
    }
}
