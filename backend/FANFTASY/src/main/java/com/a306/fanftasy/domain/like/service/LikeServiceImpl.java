package com.a306.fanftasy.domain.like.service;

import com.a306.fanftasy.domain.like.entity.NFTLike;
import com.a306.fanftasy.domain.like.entity.NFTSourceLike;
import com.a306.fanftasy.domain.like.repository.NFTLikeRepository;
import com.a306.fanftasy.domain.like.repository.NFTSourceLikeRepository;
import com.a306.fanftasy.domain.nft.entity.NFT;
import com.a306.fanftasy.domain.nft.entity.NFTSource;
import com.a306.fanftasy.domain.nft.repository.NFTRepository;
import com.a306.fanftasy.domain.nft.repository.NFTSourceRepository;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import com.a306.fanftasy.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements  LikeService{

  private final NFTLikeRepository nftLikeRepository;
  private final NFTSourceLikeRepository nftSourceLikeRepository;
  private final NFTRepository nftRepository;
  private final NFTSourceRepository nftSourceRepository;

  private final UserRepository userRepository;

  @Override
  public void sourceLike(long nftSourceId, long userId) {
    //user와 nftsourceId를 통해서 sourcelikeDB에 추가한 후,
    NFTSource nftSource = nftSourceRepository.findById(nftSourceId);
    User user = userRepository.findByUserId(userId);

    NFTSourceLike nftSourceLike = NFTSourceLike.builder()
            .nftSource(nftSource)
                .user(user).build();
    nftSourceLikeRepository.save(nftSourceLike);
    //sourcelike에 좋아요 수를 늘려주기
    long likenum = nftSource.getLikeNum();
    nftSource.updateLikeNum(likenum+1);
    nftSourceRepository.save(nftSource);
  }

  @Override
  public void sourceCancel(long nftSourceId, long userId) {
    //user와 nftsourceId를 통해서 sourcelikeDB에서 삭제한 후,
    NFTSource nftSource = nftSourceRepository.findById(nftSourceId);
    User user = userRepository.findByUserId(userId);
    nftSourceLikeRepository.deleteByNftSourceAndUser(nftSource, user);
    //sourcelike에 좋아요 수 내리기
    long likenum = nftSource.getLikeNum();
    nftSource.updateLikeNum(likenum-1);
    nftSourceRepository.save(nftSource);
  }

  @Override
  public void nftLike(long nftId, long userId) {
    //user와 nftsourceId를 통해서 nftlikeDB에 추가한 후,
    NFT nft = nftRepository.findById(nftId);
    User user = userRepository.findByUserId(userId);

    NFTLike nftLike = NFTLike.builder()
        .nft(nft)
        .user(user).build();
    nftLikeRepository.save(nftLike);
    //nftlike에 좋아요 수 늘리기
    long likenum = nft.getNftLikeNum();
    nft.updateNFTLikeNum(likenum+1);
    nftRepository.save(nft);
  }

  @Override
  public void nftCancel(long nftId, long userId) {
    //user와 nftsourceId를 통해서 nftlikeDB에서 삭제한 후,
    NFT nft = nftRepository.findById(nftId);
    User user = userRepository.findByUserId(userId);
    nftLikeRepository.deleteByNftAndUser(nft, user);
    //nftlike에 좋아요 수 내리기
    long likenum = nft.getNftLikeNum();
    nft.updateNFTLikeNum(likenum-1);
    nftRepository.save(nft);
  }
}
