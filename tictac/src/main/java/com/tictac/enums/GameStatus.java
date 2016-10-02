package com.tictac.enums;

/**
 * Created by petar on 9/26/2016.
 */
public enum GameStatus {
    IN_PROGRESS{
        @Override
        public String toString(){
            return "IN_PROGRESS";
        }
    },
    FIRST_PLAYER_WON{
        @Override
        public String toString(){
            return "FIRST_PLAYER_WON";
        }
    },
    SECOND_PLAYER_WON{
        @Override
        public String toString(){
            return "SECOND_PLAYER_WON";
        }
    },
    TIE{
        @Override
        public String toString(){
            return "TIE";
        }
    },
    TIMEOUT{
        @Override
        public String toString(){
            return "TIMEOUT";
        }
    },
    WAITS_FOR_PLAYER{
        @Override
        public String toString(){
            return "WAITS_FOR_PLAYER";
        }
    }
}
