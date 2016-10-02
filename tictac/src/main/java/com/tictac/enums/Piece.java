package com.tictac.enums;

/**
 * Created by petar on 9/26/2016.
 */
public enum Piece {
    X{
        @Override
        public String toString(){
            return "X";
        }
    },
    O{
        @Override
        public String toString(){
            return "O";
        }
    }
}
