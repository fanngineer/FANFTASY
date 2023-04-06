package com.a306.fanftasy.domain.log.controller;

import com.a306.fanftasy.domain.log.dto.LogCreateDTO;
import com.a306.fanftasy.domain.log.service.LogService;
import com.a306.fanftasy.domain.response.ResponseDefault;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/log")
@Slf4j
public class LogController {

    private LogService logService;

    @PostMapping()
    public ResponseEntity<?> saveLog(@RequestBody LogCreateDTO logCreateDTO) {
        ResponseDefault responseDefault = null;
        try {
            logService.saveLog(logCreateDTO);
            log.info("Log 등록 성공");
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(responseDefault);
        } catch (Exception e) {
            log.error("Log 등록 실패");
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .data(null)
                    .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

    @GetMapping("/{nftId}")
    public ResponseEntity<?> getLogList(@PathVariable("nftId") Long nftId) {
        ResponseDefault responseDefault = null;
        try {
            log.info("Log 리스트 조회 성공");
            responseDefault = ResponseDefault.builder()
                    .success(true)
                    .messege("SUCCESS")
                    .data(logService.getLogList(nftId))
                    .build();
            return ResponseEntity.ok().body(responseDefault);
        } catch (Exception e) {
            log.error("Log 리스트 조회 실패");
            responseDefault = ResponseDefault.builder()
                    .success(false)
                    .messege("FAIL")
                    .data(null)
                    .build();
            return ResponseEntity.badRequest().body(responseDefault);
        }
    }

}
