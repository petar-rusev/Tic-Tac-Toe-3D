package com.tictac.DTO;

import com.tictac.enums.GameStatus;
import com.tictac.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by petar on 9/27/2016.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveDTO {
    private int boardX;
    private int boardY;
    private int boardZ;
    private Date created;
    private String userName;
    private String gameStatus;
    private String piece;

}
