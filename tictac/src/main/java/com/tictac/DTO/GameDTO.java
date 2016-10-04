package com.tictac.DTO;

import com.tictac.enums.GameType;
import com.tictac.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by petar on 9/27/2016.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private int id;
    private String piece;
}
