package com.tictac.enums;

/**
 * Created by petar on 9/26/2016.
 */
public enum GameType {
    COMPETITION{
        @Override
        public String toString(){
            return "COMPETITION";
        }
    },
    COMPUTER{
        @Override
        public String toString(){
            return "COMPUTER";
        }
    }
}
