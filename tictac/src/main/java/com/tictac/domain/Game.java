package com.tictac.domain;

/**
 * Created by petar on 9/26/2016.
 */

import com.tictac.enums.GameStatus;
import com.tictac.enums.GameType;
import com.tictac.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="second_player_id",nullable = true)
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name = "first_player_id",nullable = false)
    private Player firstPlayer;

    @Enumerated(EnumType.STRING)
    private Piece firstPlayerPieceCode;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Column(name= "created",nullable = false)
    private Date created;
}
