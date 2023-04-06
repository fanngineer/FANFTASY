package com.a306.fanftasy.domain.log.entity;

import com.a306.fanftasy.domain.nft.entity.NFT;
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
@Getter
@ToString
@Builder
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_id")
    private NFT nft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_user_id")
    private User sellUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_user_id")
    private User buyUser;

    @Column(name = "transaction_time")
    private Long transactionTime;

    @Column(name = "transaction_price")
    private double transactionPrice;

}
