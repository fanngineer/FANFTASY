package com.a306.fanftasy.domain.log.service;

import com.a306.fanftasy.domain.log.dto.LogCreateDTO;
import com.a306.fanftasy.domain.log.dto.LogListDTO;

import java.util.List;

public interface LogService {
    void saveLog(LogCreateDTO logCreateDTO);

    List<LogListDTO> getLogList(Long nftId);
}
