package com.a306.fanftasy.domain.nft.entity;


import com.a306.fanftasy.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@ToString
@Builder
public class NFT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nft_id")
    private Long nftId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "token_uri")
    private String tokenUri;

    @Column(name = "is_on_sale")
    private Boolean isOnSale;

    @Column(name = "current_price")
    private double currentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reg_artist")
    private User regArtist;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "file_uri")
    private String fileUri;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;
}
