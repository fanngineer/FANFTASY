package com.a306.fanftasy.domain.log.repository;

import com.a306.fanftasy.domain.log.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByNftNftIdOrderByTransactionTimeDesc(Long nftId);

}
