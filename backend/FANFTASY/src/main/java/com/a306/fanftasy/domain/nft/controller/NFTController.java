package com.a306.fanftasy.domain.nft.controller;

import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;
import com.a306.fanftasy.domain.nft.service.NFTService;
import com.a306.fanftasy.domain.nft.service.NFTServiceImpl;
import com.a306.fanftasy.domain.response.ResponseDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/nft")
@Slf4j
public class NFTController {

    NFTService nftService;

    @PostMapping
    public ResponseEntity<?> NFTAdd(NFTCreateDTO nftCreateDto){
        log.info("NFT 생성 요청 : " + nftCreateDto.toString());
        ResponseDefault responseDefault = null;
        try{
            nftService.addNFT(nftCreateDto);
            responseDefault = ResponseDefault.builder().success(true).messege("SUCCESS").build();
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error(e.getMessage());
            responseDefault = ResponseDefault.builder().success(false).messege("fail").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    @GetMapping("/{nftId}")
    public ResponseEntity<?> NFTDetail(@PathVariable long nftId){
        log.info("NFT 조회 요청 : " + nftId);
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                    .messege("SUCCESS")
                    .data(nftService.getNFT(nftId))
                    .success(true).build();
            log.info("NFT 조회 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("NFT 조회 실패");
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL").build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    @PutMapping
    public ResponseEntity<?> NFTTrade(NFTTradeDTO nftTradeDTO){
        log.info("NFT 거래 발생 : " + nftTradeDTO.toString());
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .build();
            log.info("거래 반영 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("거래 실패");
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<?> NFTDetail(@RequestParam long regArtistId, long ownerId, String keyword){
        log.info("NFT 리스트 조회 요청 : " + "아티스트-"+regArtistId+", 소유자"+ownerId+", 검색어"+keyword);
        ResponseDefault responseDefault = null;
        try{
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .data(nftService.getNFTList(regArtistId,ownerId,keyword))
                    .build();
            log.error("목록 조회 성공");
            return ResponseEntity.ok().body(responseDefault);
        }catch (Exception e){
            log.error("목록 조회 실패");
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

}
