package com.a306.fanftasy.domain.log.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogCreateDTO {

    private Long nftId; // 토큰 uri
    private String sellUserAddress; // 판매자 지갑주소
    private String buyUserAddress; // 구매자 지갑주소
    private Long transactionTime; // 거래 시간 유닉스 타임스탬프
    private double transactionPrice; // 거래 가격
}
