package com.a306.fanftasy.domain.boardlike;

import com.a306.fanftasy.domain.board.Board;
import com.a306.fanftasy.domain.nft.NFT;
import com.a306.fanftasy.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class BoardLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_like_user_id")
    private User boardLikeUserId;
}
