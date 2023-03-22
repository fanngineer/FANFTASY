package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTDetailDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class NFTServiceImpl implements NFTService{
    @Override
    public void addNFT(NFTCreateDTO nftCreateDTO) {

    }

    @Override
    public NFTDetailDTO getNFT(long nftId) {
        return null;
    }

    @Override
    public void modifyNFT(NFTTradeDTO nftTradeDTO) {

    }

    @Override
    public List<NFTDetailDTO> getNFTList(long regArtistId, long ownerId, String keyword) {
        return null;
    }
}
