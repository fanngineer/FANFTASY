package com.a306.fanftasy.domain.log.service;

import com.a306.fanftasy.domain.log.dto.LogCreateDTO;
import com.a306.fanftasy.domain.log.dto.LogListDTO;
import com.a306.fanftasy.domain.log.entity.Log;
import com.a306.fanftasy.domain.log.repository.LogRepository;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final NFTRepository nftRepository;
    private final UserRepository userRepository;
    
    // Log 정보 저장
    @Override
    public void saveLog(LogCreateDTO logCreateDTO) {
        try {
            NFT nft = nftRepository.findById(logCreateDTO.getNftId()).orElse(null);
            User sellUser = userRepository.findByAddress(logCreateDTO.getSellUserAddress());
            User buyUser = userRepository.findByAddress(logCreateDTO.getBuyUserAddress());
            Log log = Log.builder()
                    .nft(nft)
                    .sellUser(sellUser)
                    .buyUser(buyUser)
                    .transactionTime(logCreateDTO.getTransactionTime())
                    .transactionPrice(logCreateDTO.getTransactionPrice())
                    .build();
            logRepository.save(log);
        } catch (Exception e) {
            throw e;
        }
    }
    
    // NFT에 대한 Log List 호출
    @Override
    public List<LogListDTO> getLogList(Long nftId) {
        try {
            List<Log> logList = logRepository.findByNftNftIdOrderByTransactionTimeDesc(nftId);
            return logList.stream().map(m -> LogListDTO.fromEntity(m)).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }
    
    // Long 형태의 유닉스 스탬프타임을 LocalDateTime으로 변환시켜주는 메서드
    public static LocalDateTime convertUnixTimestampToDateTime(long unixTimestamp) {
        Instant instant = Instant.ofEpochSecond(unixTimestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return dateTime;
    }
}
