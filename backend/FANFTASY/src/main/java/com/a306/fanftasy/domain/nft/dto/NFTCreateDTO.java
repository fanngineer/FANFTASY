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

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NFTCreateDTO {
    private String title;
    private String content;
    private long totalNum; //발행량
    private double originPrice; //발행가
    private long regArtistId;
    private String fileUri;
    private String fileType; //파일 종류
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime regDate;
    private List<String> tokenUris;
}
