package com.tictac.domain;

import lombok.*;

/**
 * Created by petar on 9/26/2016.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Position {
    int boardX;
    int boardY;
    int boardZ;

}
