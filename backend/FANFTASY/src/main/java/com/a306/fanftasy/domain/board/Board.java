package com.a306.fanftasy.domain.board;

import com.a306.fanftasy.domain.nft.NFT;
import com.a306.fanftasy.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_write_user_id")
    private User boardWriteUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_id")
    private NFT nftId;

    private String type;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_reg_date")
    private LocalDateTime boardRegDate;

    private int views;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "buy_price")
    private Double buyPrice;

    @Column(name = "sale_price")
    private Double salePrice;
}
