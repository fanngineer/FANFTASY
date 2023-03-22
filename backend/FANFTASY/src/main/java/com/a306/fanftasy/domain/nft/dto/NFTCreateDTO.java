/*
 *  NFT 생성할때 정보 받아오는 DTO
 * */
package com.a306.fanftasy.domain.nft.dto;

import com.a306.fanftasy.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NFTCreateDTO {
    private String tokenUri;
    private double currentPrice;
    private User regArtist;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime regDate;
    private String fileUri;
}
