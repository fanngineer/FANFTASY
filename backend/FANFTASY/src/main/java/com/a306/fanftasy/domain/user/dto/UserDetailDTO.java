package com.a306.fanftasy.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDTO {

    private String name;
    private String address;
    private String email;
    private String nickname;
    private String profileImg;

    private String phone;

    private String role;

    private int totalSales;

    private int totalPrice;

    private String company;

}
