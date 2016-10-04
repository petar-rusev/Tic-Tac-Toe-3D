package com.tictac.services;

/**
 * Created by petar on 10/4/2016.
 */
public class GameLogic {
    public static  boolean hasWinner(int[][][]gameBoard){

        //Everything in the bottom level (z=0)
        if(gameBoard[0][0][0] !=0 && gameBoard[0][0][0] == gameBoard[1][0][0] && gameBoard[0][0][0] == gameBoard[2][0][0]){
            return true;
        }
        if(gameBoard[0][1][0] !=0 && gameBoard[0][1][0] == gameBoard[1][1][0] && gameBoard[2][1][0] == gameBoard[0][1][0]){
            return true;
        }
        if(gameBoard[0][2][0] != 0 && gameBoard[0][2][0] == gameBoard[1][2][0] && gameBoard[0][2][0] == gameBoard[2][2][0]){
            return true;
        }
        if(gameBoard[0][0][0] !=0 && gameBoard[0][0][0] == gameBoard[0][1][0] && gameBoard[0][0][0] == gameBoard[0][2][0]){
            return true;
        }
        if(gameBoard[1][0][0] != 0 && gameBoard[1][0][0] == gameBoard[1][1][0] && gameBoard[1][0][0] == gameBoard[1][2][0]){
            return true;
        }

        if(gameBoard[2][0][0] != 0 && gameBoard[2][0][0] == gameBoard[2][1][0] && gameBoard[2][0][0] == gameBoard[2][2][0]){
            return true;
        }
        if(gameBoard[0][0][0] !=0 && gameBoard[0][0][0] == gameBoard[1][1][0] && gameBoard[0][0][0] == gameBoard[2][2][0]){
            return true;
        }
        if(gameBoard[2][0][0] != 0 && gameBoard[2][0][0] == gameBoard[1][1][0] && gameBoard[2][0][0] == gameBoard[0][2][0]){
            return true;
        }


        //Everything in the middle level (z=1)
        if(gameBoard[0][0][1] != 0 && gameBoard[0][0][1] == gameBoard[1][0][1] && gameBoard[0][0][1] == gameBoard[2][0][1]){
            return true;
        }
        if(gameBoard[0][1][1] != 0 && gameBoard[0][1][1] == gameBoard[1][1][1] && gameBoard[0][1][1] == gameBoard[2][1][1]){
            return true;
        }
        if(gameBoard[0][2][1] != 0 && gameBoard[0][2][1] == gameBoard[1][2][1] && gameBoard[0][2][1] == gameBoard[2][2][1]){
            return true;
        }
        if(gameBoard[0][0][1] != 0 && gameBoard[0][0][1] == gameBoard[0][1][1] && gameBoard[0][0][1] == gameBoard[0][2][1]){
            return true;
        }
        if(gameBoard[1][0][1] != 0 && gameBoard[1][0][1] == gameBoard[1][1][1] && gameBoard[1][0][1] == gameBoard[1][2][1]){
            return true;
        }
        if(gameBoard[2][0][1] != 0 && gameBoard[2][0][1] == gameBoard[2][1][1] && gameBoard[2][0][1] == gameBoard[2][2][1]){
            return true;
        }
        if(gameBoard[0][0][1] != 0 && gameBoard[0][0][1] == gameBoard[1][1][1] && gameBoard[0][0][1] == gameBoard[2][2][1]){
            return true;
        }
        if(gameBoard[2][0][1] != 0 && gameBoard[2][0][1] == gameBoard[1][1][1] && gameBoard[2][0][1] == gameBoard[0][2][1]){
            return true;
        }


        //Everything in the top layer (z=2)
        if(gameBoard[0][0][2] != 0 && gameBoard[0][0][2] == gameBoard[1][0][2] && gameBoard[0][0][2] == gameBoard[2][0][2]){
            return true;
        }
        if(gameBoard[0][1][2] != 0 && gameBoard[0][1][2] == gameBoard[1][1][2] && gameBoard[0][1][2] == gameBoard[2][1][2]){
            return true;
        }
        if(gameBoard[0][2][2] != 0 && gameBoard[0][2][2] == gameBoard[1][2][2] && gameBoard[0][2][2] == gameBoard[2][2][2]){
            return true;
        }
        if(gameBoard[0][0][2] != 0 && gameBoard[0][0][2] == gameBoard[0][1][2] && gameBoard[0][0][2] == gameBoard[0][2][2]){
            return true;
        }
        if(gameBoard[1][0][2] != 0 && gameBoard[1][0][2] == gameBoard[1][1][2] && gameBoard[1][0][2] == gameBoard[1][2][2]){
            return true;
        }
        if(gameBoard[2][0][2] != 0 && gameBoard[2][0][2] == gameBoard[2][1][2] && gameBoard[2][0][2] == gameBoard[2][2][2]){
            return true;
        }
        if(gameBoard[0][0][2] != 0 && gameBoard[0][0][2] == gameBoard[1][1][2] && gameBoard[0][0][2] == gameBoard[2][2][2]){
            return true;
        }
        if(gameBoard[2][0][2] != 0 && gameBoard[2][0][2] == gameBoard[1][1][2] && gameBoard[2][0][2] == gameBoard[0][2][2]){
            return true;
        }

        //All the straight columns
        if(gameBoard[0][0][0] != 0 && gameBoard[0][0][0] !=0&&gameBoard[0][0][0] == gameBoard[0][0][1] && gameBoard[0][0][0] == gameBoard[0][0][2]){
            return true;
        }
        if(gameBoard[1][0][0] != 0 && gameBoard[1][0][0] == gameBoard[1][0][1] && gameBoard[1][0][0] == gameBoard[1][0][2]){
            return true;
        }
        if(gameBoard[2][0][0] != 0 && gameBoard[2][0][0] == gameBoard[2][0][1] && gameBoard[2][0][0] == gameBoard[2][0][2]){
            return true;
        }
        if(gameBoard[0][1][0] != 0 && gameBoard[0][1][0] == gameBoard[0][1][1] && gameBoard[0][1][0] == gameBoard[0][1][2]){
            return true;
        }
        if(gameBoard[1][1][0] != 0 && gameBoard[1][1][0] == gameBoard[1][1][1] && gameBoard[1][1][0] == gameBoard[1][1][2]){
            return true;
        }
        if(gameBoard[2][1][0] != 0 && gameBoard[2][1][0] == gameBoard[2][1][1] && gameBoard[2][1][0] == gameBoard[2][1][2]){
            return true;
        }
        if(gameBoard[0][2][0] != 0 && gameBoard[0][2][0] == gameBoard[0][2][1] && gameBoard[0][2][0] == gameBoard[0][2][2]){
            return true;
        }
        if(gameBoard[1][2][0] != 0 && gameBoard[1][2][0] == gameBoard[1][2][1] && gameBoard[1][2][0] == gameBoard[1][2][2]){
            return true;
        }
        if(gameBoard[2][2][0] != 0 && gameBoard[2][2][0] == gameBoard[2][2][1] && gameBoard[2][2][0] == gameBoard[2][2][2]){
            return true;
        }

        //All the diagonal columns - back to front
        if(gameBoard[0][0][0] != 0 && gameBoard[0][0][0] !=0&&gameBoard[0][0][0] !=0&&gameBoard[0][0][0] == gameBoard[0][1][1] && gameBoard[0][0][0] == gameBoard[0][2][2]){
            return true;
        }
        if(gameBoard[1][0][0] != 0 && gameBoard[1][0][0] == gameBoard[1][1][1] && gameBoard[1][0][0] == gameBoard[1][2][2]){
            return true;
        }
        if(gameBoard[2][0][0] != 0 && gameBoard[2][0][0] == gameBoard[2][1][1] && gameBoard[2][0][0] == gameBoard[2][2][2]){
            return true;
        }


        //All the diagonal columns - front to back
        if(gameBoard[0][2][0] != 0 && gameBoard[0][2][0] == gameBoard[0][1][1] && gameBoard[0][2][0] == gameBoard[0][0][2]){
            return true;
        }
        if(gameBoard[1][2][0] != 0 && gameBoard[1][2][0] == gameBoard[1][1][1] && gameBoard[1][2][0] == gameBoard[1][0][2]){
            return true;
        }
        if(gameBoard[2][0][0] != 0 && gameBoard[2][0][0] == gameBoard[2][1][1] && gameBoard[2][0][0] == gameBoard[2][0][2]){
            return true;
        }

        //All the diagonal columns - left to right
        if(gameBoard[0][0][0] != 0 && gameBoard[0][0][0] == gameBoard[1][0][1] && gameBoard[0][0][0] == gameBoard[2][0][2]){
            return true;
        }
        if(gameBoard[0][1][0] != 0 && gameBoard[0][1][0] == gameBoard[1][1][1] && gameBoard[0][1][0] == gameBoard[2][1][2]){
            return true;
        }
        if(gameBoard[0][2][0] != 0 && gameBoard[0][2][0] == gameBoard[1][2][1] && gameBoard[0][2][0] == gameBoard[2][2][2]){
            return true;
        }

        //All the diagonal columns - right to left
        if(gameBoard[2][0][0] != 0 && gameBoard[2][0][0] == gameBoard[1][0][1] && gameBoard[2][0][0] == gameBoard[0][0][2]){
            return true;
        }
        if(gameBoard[2][1][0] != 0 && gameBoard[2][1][0] == gameBoard[1][1][1] && gameBoard[2][1][0] == gameBoard[0][1][2]){
            return true;
        }
        if(gameBoard[2][2][0] != 0 && gameBoard[2][2][0] == gameBoard[1][2][1] && gameBoard[2][2][0] == gameBoard[0][2][2]){
            return true;
        }

        //All the diagonal columns - corner to corner
        if(gameBoard[0][0][0] != 0 && gameBoard[0][0][0] !=0&&gameBoard[0][0][0] == gameBoard[1][1][1] && gameBoard[0][0][0] == gameBoard[2][2][2]){
            return true;
        }
        if(gameBoard[0][2][0] != 0 && gameBoard[0][2][0] == gameBoard[1][1][1] && gameBoard[0][2][0] == gameBoard[2][0][2]){
            return true;
        }
        if(gameBoard[2][0][0] != 0 && gameBoard[2][0][0] == gameBoard[1][1][1] && gameBoard[2][0][0] == gameBoard[0][2][2]){
            return true;
        }
        if(gameBoard[2][2][0] != 0 && gameBoard[2][2][0] == gameBoard[1][1][1] && gameBoard[2][2][0] == gameBoard[0][0][2]){
            return true;
        }

        return false;
    }
}
