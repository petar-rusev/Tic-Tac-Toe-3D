package com.tictac.services;

import com.tictac.domain.Position;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by petar on 10/4/2016.
 */

public class GameLogic {
    public static List<List<Position>> getWinningPositions(){
        List<List<Position>> winningPositions = new ArrayList<>();
        //Everything in the bottom level (z=0)
        winningPositions.add(asList(new Position(0,0,0), new Position(1,0,0), new Position(2,0,0)));
        winningPositions.add(asList(new Position(0,1,0), new Position(1,1,0), new Position(2,1,0)));
        winningPositions.add(asList(new Position(0,2,0), new Position(1,2,0), new Position(2,2,0)));
        winningPositions.add(asList(new Position(0,0,0), new Position(0,1,0), new Position(0,2,0)));
        winningPositions.add(asList(new Position(1,0,0), new Position(1,1,0), new Position(1,2,0)));
        winningPositions.add(asList(new Position(2,0,0), new Position(2,1,0), new Position(2,2,0)));
        winningPositions.add(asList(new Position(0,0,0), new Position(1,1,0), new Position(2,2,0)));
        winningPositions.add(asList(new Position(2,0,0), new Position(1,1,0), new Position(0,2,0)));

        //Everything in the middle level (z=1)
        winningPositions.add(asList(new Position(0,0,1), new Position(1,0,1), new Position(2,0,1)));
        winningPositions.add(asList(new Position(0,1,1), new Position(1,1,1), new Position(2,1,1)));
        winningPositions.add(asList(new Position(0,2,1), new Position(1,2,1), new Position(2,2,1)));
        winningPositions.add(asList(new Position(0,0,1), new Position(0,1,1), new Position(0,2,1)));
        winningPositions.add(asList(new Position(1,0,1), new Position(1,1,1), new Position(1,2,1)));
        winningPositions.add(asList(new Position(2,0,1), new Position(2,1,1), new Position(2,2,1)));
        winningPositions.add(asList(new Position(0,0,1), new Position(1,1,1), new Position(2,2,1)));
        winningPositions.add(asList(new Position(2,0,1), new Position(1,1,1), new Position(0,2,1)));

        //Everything in the top layer (z=2)
        winningPositions.add(asList(new Position(0,0,2), new Position(1,0,2), new Position(2,0,2)));
        winningPositions.add(asList(new Position(0,1,2), new Position(1,1,2), new Position(2,1,2)));
        winningPositions.add(asList(new Position(0,2,2), new Position(1,2,2), new Position(2,2,2)));
        winningPositions.add(asList(new Position(0,0,2), new Position(0,1,2), new Position(0,2,2)));
        winningPositions.add(asList(new Position(1,0,2), new Position(1,1,2), new Position(1,2,2)));
        winningPositions.add(asList(new Position(2,0,2), new Position(2,1,2), new Position(2,2,2)));
        winningPositions.add(asList(new Position(0,0,2), new Position(1,1,2), new Position(2,2,2)));
        winningPositions.add(asList(new Position(2,0,2), new Position(1,1,2), new Position(0,2,2)));

        //All the straight columns
        winningPositions.add(asList(new Position(0,0,0), new Position(0,0,1), new Position(0,0,2)));
        winningPositions.add(asList(new Position(1,0,0), new Position(1,0,1), new Position(1,0,2)));
        winningPositions.add(asList(new Position(2,0,0), new Position(2,0,1), new Position(2,0,2)));
        winningPositions.add(asList(new Position(0,1,0), new Position(0,1,1), new Position(0,1,2)));
        winningPositions.add(asList(new Position(1,1,0), new Position(1,1,1), new Position(1,1,2)));
        winningPositions.add(asList(new Position(2,1,0), new Position(2,1,1), new Position(2,1,2)));
        winningPositions.add(asList(new Position(0,2,0), new Position(0,2,1), new Position(0,2,2)));
        winningPositions.add(asList(new Position(1,2,0), new Position(1,2,1), new Position(1,2,2)));
        winningPositions.add(asList(new Position(2,2,0), new Position(2,2,1), new Position(2,2,2)));

        //All the diagonal columns - back to front
        winningPositions.add(asList(new Position(0,0,0), new Position(0,1,1), new Position(0,2,2)));
        winningPositions.add(asList(new Position(1,0,0), new Position(1,1,1), new Position(1,2,2)));
        winningPositions.add(asList(new Position(2,0,0), new Position(2,1,1), new Position(2,2,2)));

        //All the diagonal columns - front to back
        winningPositions.add(asList(new Position(0,2,0), new Position(0,1,1), new Position(0,0,2)));
        winningPositions.add(asList(new Position(1,2,0), new Position(1,1,1), new Position(1,0,2)));
        winningPositions.add(asList(new Position(2,0,0), new Position(2,1,1), new Position(2,0,2)));

        //All the diagonal columns - left to right
        winningPositions.add(asList(new Position(0,0,0), new Position(1,0,1), new Position(2,0,2)));
        winningPositions.add(asList(new Position(0,1,0), new Position(1,1,1), new Position(2,1,2)));
        winningPositions.add(asList(new Position(0,2,0), new Position(1,2,1), new Position(2,2,2)));

        //All the diagonal columns - right to left
        winningPositions.add(asList(new Position(2,0,0), new Position(1,0,1), new Position(0,0,2)));
        winningPositions.add(asList(new Position(2,1,0), new Position(1,1,1), new Position(0,1,2)));
        winningPositions.add(asList(new Position(2,2,0), new Position(1,2,1), new Position(0,2,2)));

        //All the diagonal columns - corner to corner
        winningPositions.add(asList(new Position(0,0,0), new Position(1,1,1), new Position(2,2,2)));
        winningPositions.add(asList(new Position(0,2,0) ,new Position(1,1,1), new Position(2,0,2)));
        winningPositions.add(asList(new Position(2,0,0), new Position(1,1,1), new Position(0,2,2)));
        winningPositions.add(asList(new Position(2,2,0), new Position(1,1,1), new Position(0,0,2)));

        return winningPositions;
    }
    //returns 1 - computer 2 - player
    public static int checkForWinner(int[][][] board){
        List<List<Position>> paths = getWinningPositions();

        for(int i=0;i<paths.size();i++){
            Position first = paths.get(i).get(0);
            Position second = paths.get(i).get(1);
            Position third = paths.get(i).get(2);

            if(board[first.getBoardX()][first.getBoardY()][first.getBoardZ()] ==
                    board[second.getBoardX()][second.getBoardY()][second.getBoardZ()] &&
                    board[third.getBoardX()][third.getBoardY()][third.getBoardZ()] ==
                    board[second.getBoardX()][second.getBoardY()][second.getBoardZ()] &&
                    board[third.getBoardX()][third.getBoardY()][third.getBoardZ()] == 1){
                return 1;
            }else if(board[first.getBoardX()][first.getBoardY()][first.getBoardZ()] ==
                        board[second.getBoardX()][second.getBoardY()][second.getBoardZ()] &&
                        board[third.getBoardX()][third.getBoardY()][third.getBoardZ()] ==
                        board[second.getBoardX()][second.getBoardY()][second.getBoardZ()] &&
                        board[third.getBoardX()][third.getBoardY()][third.getBoardZ()] == 2){
                return 2;
            }
        }

        return 0;
    }

    public static boolean checkForStalemante(int[][][] board){
        int counter = 0;

        for(int i = 0; i < 3;i++){
            for(int j = 0; j < 3;j++){
                for(int k = 0; k < 3;k++){
                    if(board[i][j][k] != 0){
                        counter++;
                    }
                }
            }
        }

        if(counter == 27){
            return true;
        }

        return false;
    }
}
