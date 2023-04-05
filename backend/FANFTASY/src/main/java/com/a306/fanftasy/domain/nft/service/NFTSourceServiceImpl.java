package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.like.repository.NFTSourceLikeRepository;
import com.a306.fanftasy.domain.nft.dto.NFTSourceDetailDTO;
import com.a306.fanftasy.domain.nft.dto.NFTSourceListDTO;
import com.a306.fanftasy.domain.nft.dto.NFTSourceTradeDTO;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.nft.repository.NFTSourceRepository;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.dto.UserPublicDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import java.time.LocalDateTime;
import com.a306.fanftasy.domain.user.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.a306.fanftasy.domain.user.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NFTSourceServiceImpl implements NFTSourceService {

  private final NFTSourceRepository nftSourceRepository;
  private final NFTRepository nftRepository;
  private final UserRepository userRepository;
  private final NFTSourceLikeRepository nftSourceLikeRepository;

  @Override
  public List<NFTSourceListDTO> getNFTSourceList(int orderType, int page, String keyword) {
    try {
      List<NFTSource> entityList = null;
      List<NFTSourceListDTO> result=null;
      //(page-1)*6+1부터 page*6까지의 결과를 가져와야함
      //OrderType은 1,2,3 최신순,판매량,판매금액 순
      //keyword가 null이면 무시
      switch (orderType) {
        case 1: //최신순
          entityList = nftSourceRepository.findByConditionOrderByRegDate(keyword);
          break;
        case 2: //잔여량 적은순
          entityList = nftSourceRepository.findByConditionOrderByRemainNumAsc(keyword);
          break;
        case 3: //금액높은순
          entityList = nftSourceRepository.findByConditionOrderByOriginPriceAsc(keyword);
          break;
        case 4:
          entityList = nftSourceRepository.findByConditionOrderByOriginPriceDesc(keyword);
      }//switch
      //엔티티를 DTO로 변환

      result = entityList.stream().map(m -> NFTSourceListDTO.fromEntity(m)).collect(
          Collectors.toList());
      log.info(result.toString());
      return result;
    } catch (Exception e) {
      throw e;
    }//catch
  }

  //마켓에서 nft를 눌러서 상세페이지를 볼때
  @Override
  public NFTSourceDetailDTO getNFTSourceDetail(long nftSourceId, Long userId)   {
      NFTSource nftSource = nftSourceRepository.findById(nftSourceId);
      NFTSourceDetailDTO nftSourceDetailDTO = NFTSourceDetailDTO.fromEntity(nftSource);
//      //좋아요 찾기
//      //securitycontext holder에서 user를 꺼내서


      //원본 security 일단 막아둠
//      UserLoginDTO userLoginDTO = (UserLoginDTO) SecurityContextHolder.getContext()
//          .getAuthentication().getPrincipal();
//      long userId = userLoginDTO.getUserId();

      //테스트용 48L -> 그누그누
    if (userId == null) {
      nftSourceDetailDTO.updateUserLike(false);
      return nftSourceDetailDTO;
    } else {
        User userEntity = userRepository.findByUserId(userId);//좋아요 클릭 여부 확인
        boolean userLike = false;
        if (nftSourceLikeRepository.findByNftSourceAndUser(nftSource, userEntity) != null) {
          userLike = true;
        }
        //로그인된 userid와 nftsourceid 를 통해서 nftsourcelike가 존재하는지 find
        // 반환값이 null이 아니면 userLike = true;
        nftSourceDetailDTO.updateUserLike(userLike);
        return nftSourceDetailDTO;
    }
    //catch
  }

  //아티스트 페이지에서 아티스트가 발매한 NFT 목록 조회
  @Override
  public List<NFTSourceListDTO> getNFTSourceListByArtist(long regArtistId) {
    try {
      User regArtist = User.builder().userId(regArtistId).build();
      List<NFTSource> entityList = nftSourceRepository.findByRegArtistOrderByRegDateDesc(regArtist);

      //엔티티를 DTO로 변환
      List<NFTSourceListDTO> result = entityList.stream().map(m -> NFTSourceListDTO.fromEntity(m))
          .collect(
              Collectors.toList());
      return result;
    }//try
    catch (Exception e) {
      throw e;
    }//catch
  }//getNFTListByArtist

  @Override
  public void modifyNFTSource(NFTSourceTradeDTO nftSourceTradeDTO) {
    try {
      NFTSource nftSourceEntity = nftSourceRepository.findById(nftSourceTradeDTO.getNftSourceId());
      //잔여량 확인
      long remainNum = nftSourceEntity.getRemainNum();
      log.info("잔여량 : " + remainNum);
      if (remainNum <= 0) {
//        throw new Exception();
      }
      //구매자
      User owner = User.builder().userId(nftSourceTradeDTO.getBuyerId()).build();
      //해당 컨텐츠에 해당되는 nft중에 하나만 반환
      NFT nftEntity = nftRepository.findFirstByNftSourceAndOwner(nftSourceEntity,nftSourceEntity.getRegArtist());
      if(nftEntity == null) throw new NullPointerException();
      log.info("거래될 nft : " + nftEntity.toString());
      //대상 nft에 대해서 변경저장
      nftEntity.updateIsOnSale(false);
      nftEntity.updateOwner(owner);
      nftEntity.updateTransactionTime(LocalDateTime.now());
      nftRepository.save(nftEntity);
      //해당 컨텐츠에 대해서 잔여량 수정
      nftSourceEntity.updateRemainNum(remainNum - 1);
      nftSourceRepository.save(nftSourceEntity);
      //판매 아티스트 총 금액과 갯수 늘려주기
      User artist = nftSourceEntity.getRegArtist();
      artist.plusTotalSales(1);
      artist.plusTotalPrice(nftSourceEntity.getOriginPrice());
      userRepository.save(artist);
    }//try
    catch (Exception e) {
      throw e;
    }//catch


  }


}
