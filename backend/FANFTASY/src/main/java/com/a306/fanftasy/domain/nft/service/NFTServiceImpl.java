package com.a306.fanftasy.domain.nft.service;


import com.a306.fanftasy.domain.like.repository.NFTLikeRepository;
import com.a306.fanftasy.domain.nft.dto.*;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.nft.exception.NFTCreateException;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.nft.repository.NFTSourceRepository;
import com.a306.fanftasy.domain.user.dto.UserLoginDTO;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;

import java.io.IOException;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import com.a306.fanftasy.domain.user.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

@Service
@Slf4j
@RequiredArgsConstructor
public class NFTServiceImpl implements NFTService {
  private final NFTRepository nftRepository;
  private final NFTSourceRepository nftSourceRepository;
  private final UserRepository userRepository;
  private final NFTLikeRepository nftLikeRepository;

  private final BasicService basicService;

  //1. NFT 생성
  @Override
  public void addNFT(NFTCreateDTO nftCreateDTO)
      throws IOException, ExecutionException, InterruptedException {
    try {
      log.info("---------------------------------");
      //3. NFT Source 데이터 + file CID ipfs + Metadata CID이 DTO에 담겨서 들어옴
      //4. NFT생성 스마트컨트랙트 호출
      long artistId = nftCreateDTO.getRegArtistId();
      //등록 아티스트
      User artist = userRepository.findByUserId(artistId);
      //등록 갯수
      long totalNum = nftCreateDTO.getTotalNum();
      double originPrice = nftCreateDTO.getOriginPrice();
      LocalDateTime regDate = nftCreateDTO.getRegDate();
      String metaCID = nftCreateDTO.getMetaCID();
      log.info("NFT Source 저장 시작");
      //NFT Source생성
      long createdNum = 0;
      NFTSource nftSource = NFTSource.builder()
          .title(nftCreateDTO.getTitle())
          .content(nftCreateDTO.getContent())
          .totalNum(0)
          .originPrice(originPrice)
          .regArtist(artist)
          .fileType(nftCreateDTO.getFileType())
          .fileCID(nftCreateDTO.getFileCID())
          .metaCID(metaCID)
          .regDate(regDate)
          .remainNum(0)
          .likeNum(0)
          .build();

      //개별 nft 생성
      String artistAddress = artist.getAddress();
      nftSourceRepository.save(nftSource);
      log.info("nft 콘텐츠 등록 완료");

      log.info("개별 nft 생성 시작");
      for (int i = 1; i <= totalNum; i++
      ) {
        long tokenID;
        try {
          //스마트 컨트랙트로 tokenID 받아오기
          tokenID = basicService.create(artistAddress, metaCID);
        } catch (Exception e) {
          log.info("토큰 발급 재실행");
          tokenID = basicService.create(artistAddress, metaCID);
        }
        NFT nft = NFT.builder()
            .nftId(tokenID)
            .owner(artist)
            .isOnSale(false)
            .currentPrice(originPrice)
            .transactionTime(regDate)
            .nftSource(nftSource)
            .editionNum(i)
            .build();
        log.info("nft generated : " + nft.toString());
        nftRepository.save(nft);
        createdNum++;
      }//for-each
      nftSource.updateTotalNum(createdNum);
      nftSource.updateRemainNum(createdNum);
      if (createdNum == 0) {
        nftSourceRepository.delete(nftSource);
        throw new NFTCreateException("nft 생성 실패");
      } else {
        nftSourceRepository.save(nftSource);
        log.info("CreatedNum : " + createdNum);
      }
    } catch (Exception e) {
      throw e;
    }//catch
  }

  //6. 회원 소유 NFT목록 반환
  @Override
  public List<NFTListDTO> getNFTListByOwnerId(long ownerId) {
    try {
      User owner = User.builder().userId(ownerId).build();
      List<NFT> entityList = nftRepository.findByOwnerOrderByTransactionTimeDesc(owner);
      //엔티티를 DTO로 변환
      List<NFTListDTO> result = entityList.stream().map(m -> NFTListDTO.fromEntity(m)).collect(
          Collectors.toList());
      return result;
    }//try
    catch (Exception e) {
      throw e;
    }//catch
  }//getNFTListByOwnerId

  // 마켓 플레이스 NFT목록 반환
  @Override
  public List<NFTMarketListDTO> getNFTList(int orderType, int saleType, String keyword) {
    try {
      List<NFTSource> entityList = null;
      List<NFTMarketListDTO> result = new ArrayList<>();
      // orderType : 1, 2, 3
      // => 최신순, 가격 높은 순, 가격 낮은 순
      // saleType : 1, 2
      // => 판매중, 판매완료

      // 판매중, 최신순
      if (saleType == 1) {
        if (orderType == 1) {
          List<Long> nftSourceIdIsOnSale = nftRepository.findNftSourceIdIsOnSale(); // 판매 중인 nftSourceId 최신순
          for (Long nftSourceId : nftSourceIdIsOnSale) {
            NFTSource nftSource = nftRepository.findNFTMarketListOrderByRegDate(nftSourceId,
                keyword);
            if (nftSource != null) {
              NFTMarketListDTO nftMarketListDTO = NFTMarketListDTO.fromEntity(nftSource);
              nftMarketListDTO.setCurrentPrice(nftRepository.findMinCurrentPrice(nftSourceId));
               result.add(nftMarketListDTO);
            }
          }
          // 판매중, 가격 높은 순
        } else if (orderType == 2) {
          List<Long> nftSourceIdIsOnSale = nftRepository.findNftSourceIdIsOnSaleOrderByCurrentPriceDesc(); // 판매 중인 nftSourceId 가격 높은 순
          for (Long nftSourceId : nftSourceIdIsOnSale) {
            NFTSource nftSource = nftRepository.findNFTMarketListOrderByRegDate(nftSourceId,
                keyword);
            if (nftSource != null) {
              NFTMarketListDTO nftMarketListDTO = NFTMarketListDTO.fromEntity(nftSource);
              nftMarketListDTO.setCurrentPrice(nftRepository.findMinCurrentPrice(nftSourceId));
              result.add(nftMarketListDTO);
            }
          }

          // 판매중, 가격 낮은 순
        } else {
          List<Long> nftSourceIdIsOnSale = nftRepository.findNftSourceIdIsOnSaleOrderByCurrentPrice(); // 판매 중인 nftSourceId 가격 낮은 순
          for (Long nftSourceId : nftSourceIdIsOnSale) {
            NFTSource nftSource = nftRepository.findNFTMarketListOrderByRegDate(nftSourceId,
                keyword);
            if (nftSource != null) {
              NFTMarketListDTO nftMarketListDTO = NFTMarketListDTO.fromEntity(nftSource);
              nftMarketListDTO.setCurrentPrice(nftRepository.findMinCurrentPrice(nftSourceId));
              result.add(nftMarketListDTO);
            }
          }
        }
        // 미판매
      } else {
        // 미판매 최신순
        if (orderType == 1) {
          List<Long> nftSourceIdIsOnSale = nftRepository.findNftSourceIdsNotOnSaleOrderByRegDateDesc(); // 판매 중인 nftSourceId 가격 낮은 순
          for (Long nftSourceId : nftSourceIdIsOnSale) {
            NFTSource nftSource = nftRepository.findNFTMarketListOrderByRegDate(nftSourceId,
                keyword);
            if (nftSource != null) {
              NFTMarketListDTO nftMarketListDTO = NFTMarketListDTO.fromEntity(nftSource);
              nftMarketListDTO.setCurrentPrice(0.0);
              result.add(nftMarketListDTO);
            }
          }
        }
      }
      return result;
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public NFTDetailDTO getNFTDetail(long nftSourceId, Long userId) {
    List<NFT> resultList;
    NFTDetailDTO nftDetailDTO;
    Optional<Double> currentPriceOpt = nftRepository.findByNftSourceId(nftSourceId);
    Double currentPrice = currentPriceOpt.orElse(0.0);
    Pageable pageable = PageRequest.of(0, 1);
    if (currentPrice == 0.0) {
      resultList = nftRepository.findByIdMaxResult(nftSourceId, pageable);
      nftDetailDTO = NFTDetailDTO.fromEntity(resultList.get(0));
      nftDetailDTO.setCurrentPrice(0);
    } else {
      resultList = nftRepository.findByIdAndByCurrentPriceMaxResult(nftSourceId, currentPrice,
          pageable);
      nftDetailDTO = NFTDetailDTO.fromEntity(resultList.get(0));
    }

    NFT nft = nftRepository.findById(nftDetailDTO.getNftId()).orElse(null);

    if (userId == null) {
      nftDetailDTO.updateUserLike(false);
    } else {
      User userEntity = userRepository.findByUserId(userId);
      boolean userLike = false;
      if (nftLikeRepository.findByNftAndUser(nft, userEntity) != null) {
        userLike = true;
      }
      nftDetailDTO.updateUserLike(userLike);
    }
    return nftDetailDTO;
  }

  //7. 개인이 보유한 NFT 상세
  @Override
  public NFTDetailDTO getNFT(long nftId) {
    try {
      NFT nft = nftRepository.findById(nftId);
      NFTDetailDTO nftDetailDTO = NFTDetailDTO.fromEntity(nft);
      //      //좋아요 찾기
//      //securitycontext holder에서 user를 꺼내서
      UserLoginDTO userLoginDTO = (UserLoginDTO) SecurityContextHolder.getContext()
          .getAuthentication().getPrincipal();
      long userId = userLoginDTO.getUserId();
      User userEntity = userRepository.findByUserId(userId);
      boolean userLike = false;
      if (nftLikeRepository.findByNftAndUser(nft, userEntity) != null) {
        userLike = true;
      }
      //로그인된 userid와 nftsourceid 를 통해서 nftsourcelike가 존재하는지 find
      // 반환값이 null이 아니면 userLike = true;
      nftDetailDTO.updateUserLike(userLike);
      return nftDetailDTO;
    } catch (Exception e) {
      throw e;
    }//catch
  }

  //8.개인거래
  @Override
  public void modifyNFT(NFTTradeDTO nftTradeDTO) {
    try {
      NFT nftEntity = nftRepository.findById(nftTradeDTO.getNftId());
      User nowOwner = nftEntity.getOwner();
      String nowOwnerAddress = nowOwner.getAddress();
      User newOwner = User.builder().userId(nftTradeDTO.getBuyerId()).build();
      String newOwnerAddress = newOwner.getAddress();
      double price = nftTradeDTO.getTransactionPrice();
      String tokenUri = nftEntity.getTokenUri();
      //스마트 컨트랙트 호출해서
      //1. tokenUri에 해당되는 nft 소유자 newOwner로 변경해주고
      //2. newOwner의 잔액을 nowOwner의 잔액으로 변경시키는 트랜잭션 필요
      //trade(nowOwnerAddress, newOwnerAddress, tokenUri, price)
      nftEntity.updateIsOnSale(false);
      nftEntity.updateOwner(newOwner);
      nftEntity.updateTransactionTime(LocalDateTime.now());
      nftEntity.updateCurrentPrice(price);
      nftRepository.save(nftEntity);
    } catch (Exception e) {
      throw e;
    }//catch
  }

  private String CONTRACT_ADDRESS = "컨트랙트 주소";

  //smartContract
  public String createNFT(String artistAddress, String metaCID) throws IOException {
    //NFT 발행 트랜잭션 호출하는 method
    Web3j web3j = Web3j.build(new HttpService("https://fanftasy.kro.kr/network"));
    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
    System.out.println(web3ClientVersion.getWeb3ClientVersion());
    return null;
  }

  public void trade(String nowOwnerAddress, String newOwnerAddress, String tokenUri, double price)
      throws IOException {
    //NFT 거래 트랜잭션을 발생시키는 method
    Web3j web3j = Web3j.build(new HttpService("https://fanftasy.kro.kr/network"));
    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
    System.out.println(web3ClientVersion.getWeb3ClientVersion());
  }
}
