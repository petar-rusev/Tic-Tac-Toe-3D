package com.tictac.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by petar on 10/4/2016.
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateMoveResponceDTO {
    private String message;
    private int[][][] gameBoard;

    public CreateMoveResponceDTO(int[][][] gameBoard){
        this.gameBoard = gameBoard;
    }

}
