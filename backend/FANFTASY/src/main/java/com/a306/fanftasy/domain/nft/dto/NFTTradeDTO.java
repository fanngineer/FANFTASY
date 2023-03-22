/*
* 거래 정보를 반영하기 위해 받아오는 DTO
* */


package com.a306.fanftasy.domain.nft.dto;


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
public class NFTTradeDTO {
    int nftId;
    int buyerId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime transactionTime;

}
