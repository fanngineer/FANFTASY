package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.nft.dto.NFTSourceDetailDTO;
import com.a306.fanftasy.domain.nft.dto.NFTSourceListDTO;
import com.a306.fanftasy.domain.nft.dto.NFTSourceTradeDTO;
import java.util.List;

public interface NFTSourceService {


  List<NFTSourceListDTO> getNFTSourceList(int OrderType, int page, String keyword);

  NFTSourceDetailDTO getNFTSourceDetail(long nftSourceId, Long userId);

  List<NFTSourceListDTO> getNFTSourceListByArtist(long artistId);
  //////////
  void modifyNFTSource(NFTSourceTradeDTO nftSourceTradeDTO);
}
