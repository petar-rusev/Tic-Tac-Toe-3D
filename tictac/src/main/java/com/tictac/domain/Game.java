package com.tictac.domain;

/**
 * Created by petar on 9/26/2016.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tictac.enums.GameStatus;
import com.tictac.enums.GameType;
import com.tictac.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="second_player_id",nullable = true)
    private User secondPlayer;

    @ManyToOne
    @JoinColumn(name = "first_player_id",nullable = false)
    private User firstPlayer;

    @Column(name= "first_player_piece_code",nullable=false)
    private String firstPlayerPieceCode;

    @Column(name= "game_type",nullable=false)
    private String gameType;

    @Column(name= "game_status",nullable=false)
    private String gameStatus;

    @Column(name= "created",nullable = false)
    private Date created;
}
