package com.a306.fanftasy.domain.user;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String address;
    private String email;
    private String nickname;
    @Column(name = "profile_img")
    private String profileImg;
    private String phone;
    private String role;
    @Column(name = "total_sales")
    private long totalSales;
    @Column(name = "total_price")
    private double totalPrice;
    private String company;
    @Column(name = "refresh_token")
    private String refreshToken;
}
