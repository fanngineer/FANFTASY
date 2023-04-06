package com.a306.fanftasy.domain.log.dto;

import com.a306.fanftasy.domain.log.entity.Log;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogListDTO {

    private String sellUserNickName;
    private String sellUserAddress;
    private String buyUserNickName;
    private String buyUserAddress;
    private Long transactionTime;
    private double transactionPrice;

    public static LogListDTO fromEntity(Log log) {
        return LogListDTO.builder()
                .sellUserNickName(log.getSellUser().getNickname())
                .sellUserAddress(log.getSellUser().getAddress())
                .buyUserNickName(log.getBuyUser().getNickname())
                .buyUserAddress(log.getBuyUser().getAddress())
                .transactionTime(log.getTransactionTime())
                .transactionPrice(log.getTransactionPrice())
                .build();
    }
}
