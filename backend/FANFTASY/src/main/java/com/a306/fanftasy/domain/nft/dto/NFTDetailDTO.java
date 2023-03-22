/*
*  NFT 정보를 조회했을때 반환해 줄 DTO
* */

package com.a306.fanftasy.domain.nft.dto;

import com.a306.fanftasy.domain.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class NFTDetailDTO {
    private int nftId;
    private User owner;
    private String tokenUri;
    private boolean isOnSale;
    private double currentPrice;
    private User regArtist;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime regDate;
    private String fileUri;

}
