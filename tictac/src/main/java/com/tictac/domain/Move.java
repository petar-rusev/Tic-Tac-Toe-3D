package com.tictac.domain;

/**
 * Created by petar on 9/26/2016.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "board_x", nullable = false)
    private int boardX;

    @Column(name = "board_y", nullable = false)
    private int boardY;

    @Column(name = "board_z", nullable = false)
    private int boardZ;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = true)
    private User player;

    @Column(name = "created",nullable = false)
    private Date created;

}
