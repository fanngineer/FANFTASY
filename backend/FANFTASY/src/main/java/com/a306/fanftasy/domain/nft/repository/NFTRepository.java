package com.a306.fanftasy.domain.nft.repository;

import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.user.entity.User;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

public interface NFTRepository extends JpaRepository<NFT, Long> {
  NFT findById(long nftId);

//  @Query("SELECT n FROM NFT n WHERE n.nftSource = :nftSource And n.nftSource.regArtist = n.owner")
  NFT findFirstByNftSourceAndOwner(NFTSource nftSource, User regArtist );

  List<NFT> findByOwnerOrderByTransactionTimeDesc(User owenr);

//  @Query("SELECT n1 FROM NFT n1 WHERE n1.isOnSale = true "
//          + "AND n1.nftSource.title like %:keyword% or n1.nftSource.content like %:keyword% or n1.nftSource.regArtist.nickname like %:keyword%"
//          + "AND n1.currentPrice = (SELECT MIN(n2.currentPrice) FROM NFT n2 "
//          + "WHERE n2.nftSource = n1.nftSource AND n2.isOnSale = true) "
//          + "AND n1.transactionTime = (SELECT MAX(n3.transactionTime) FROM NFT n3 "
//          + "WHERE n3.nftSource = n1.nftSource AND n3.currentPrice = n1.currentPrice AND n3.isOnSale = true) "
//          + "AND n1.nftId = (SELECT MIN(n4.nftId) FROM NFT n4 "
//          + "WHERE n4.nftSource = n1.nftSource AND n4.currentPrice = n1.currentPrice "
//          + "AND n4.transactionTime = n1.transactionTime AND n4.isOnSale = true)")
//  List<NFT> findByOnSaleOrderByPriceByCondition(@Param("keyword") String keyword);

  /**
   * 판매중인 NFT 중 최신순 현재 판매 중인 가장 작은 값 찾기
   */
  // 판매중인 NFTSourceId 찾기, 판매자가 올린 최신순 []
  @Query("select n.nftSource.nftSourceId from NFT n where n.isOnSale = true group by n.nftSource order by max(n.transactionTime) DESC")
  List<Long> findNftSourceIdIsOnSale();
  // 판매중인 NFTSourceId 중 검색
//  @Query("SELECT n FROM NFTSource n WHERE n.nftSourceId IN :nftSourceIdIsOnSale AND (n.content like %:keyword% or n.title like %:keyword% or n.regArtist.nickname like %:keyword%)")
//  List<NFTSource> findNFTMarketListOrderByRegDate(@Param("nftSourceIdIsOnSale") List<Long> nftSourceIdIsOnSale, @Param("keyword") String keyword);
  @Query("SELECT n FROM NFTSource n WHERE n.nftSourceId = :nftSourceIdIsOnSale AND (:keyword IS NULL OR n.regArtist.nickname LIKE %:keyword% OR n.title LIKE %:keyword% OR n.content LIKE %:keyword%)")
  NFTSource findNFTMarketListOrderByRegDate(@Param("nftSourceIdIsOnSale") Long nftSourceIdIsOnSale, @Param("keyword") String keyword);

  // 판매 값 중 가장 낮은 값 찾기
  @Query("select min(n.currentPrice) from NFT n where n.isOnSale = true and n.nftSource.nftSourceId = :nftSourceId")
  Double findMinCurrentPrice(@Param("nftSourceId") Long nftSourceId);


  // 판매 중인 NFTSourceId 찾기, 가격 높은 순
  @Query("select n.nftSource.nftSourceId from NFT n where n.isOnSale = true group by n.nftSource order by min(n.currentPrice) DESC, max(n.transactionTime) DESC")
  List<Long> findNftSourceIdIsOnSaleOrderByCurrentPriceDesc();

  // 판매 중인 NFTSourceId 찾기, 가격 낮은 순
  @Query("select n.nftSource.nftSourceId from NFT n where n.isOnSale = true group by n.nftSource order by min(n.currentPrice), max(n.transactionTime) DESC")
  List<Long> findNftSourceIdIsOnSaleOrderByCurrentPrice();


  // 미판매 NFTSourceId 찾기, 최신순
  @Query("SELECT n.nftSource.nftSourceId " +
          "FROM NFT n " +
          "WHERE n.nftSource NOT IN (" +
          "SELECT n1.nftSource " +
          "FROM NFT n1 " +
          "WHERE n1.isOnSale = true " +
          "GROUP BY n1.nftSource" +
          ") AND n.nftSource.regArtist.userId != n.owner.userId " +
          "GROUP BY n.nftSource.nftSourceId " +
          "ORDER BY n.nftSource.regDate DESC")
  List<Long> findNftSourceIdsNotOnSaleOrderByRegDateDesc();

  @Query("select n from NFT n where n.nftSource.nftSourceId = :nftSourceId and n.currentPrice = :currentPrice order by n.transactionTime DESC")
  List<NFT> findByIdAndByCurrentPriceMaxResult(@Param("nftSourceId") Long nftSourceId, @Param("currentPrice") double currentPrice, Pageable pageable);

  @Query("select n from NFT n where n.nftSource.nftSourceId = :nftSourceId order by n.transactionTime DESC")
  List<NFT> findByIdAndByCurrentPrice(@Param("nftSourceId") Long nftSourceId, Pageable pageable);

  @Query("select min(n.currentPrice) from NFT n where n.nftSource.nftSourceId = :nftSourceId and n.isOnSale = true")
  Optional<Double> findByNftSourceId(Long nftSourceId);

  @Query("select n from NFT n where n.nftSource.nftSourceId = :nftSourceId order by n.editionNum")
  List<NFT> findByIdMaxResult(@Param("nftSourceId") Long nftSourceId, Pageable pageable);
}
