package com.tictac.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by petar on 9/27/2016.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateMoveDTO {

    @NotNull
    int boardX;

    @NotNull
    int boardY;

    @NotNull
    int boardZ;
}
