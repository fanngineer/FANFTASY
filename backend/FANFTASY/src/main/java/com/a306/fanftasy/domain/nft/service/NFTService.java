package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.nft.dto.NFTCreateDTO;
import com.a306.fanftasy.domain.nft.dto.NFTDetailDTO;
import com.a306.fanftasy.domain.nft.dto.NFTTradeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NFTService {
    void addNFT(NFTCreateDTO nftCreateDTO);

    NFTDetailDTO getNFT(long nftId);

    void modifyNFT(NFTTradeDTO nftTradeDTO);

    List<NFTDetailDTO> getNFTList(long regArtistId, long ownerId, String keyword);
}
