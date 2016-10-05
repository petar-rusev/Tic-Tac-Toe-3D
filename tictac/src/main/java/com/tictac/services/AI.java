package com.tictac.services;

import com.tictac.domain.Position;

import java.util.*;

public class AI {

    private int gameBoard[][][] = new int[3][3][3];
    private int priorities[] = new int[27];

    public GameMove computersGameMove;

    private List<int[]> generateMoves(){
        List<int[]> nextMoves = new ArrayList<int[]>();

        for(int i = 0; i < 3;i++){
            for(int j = 0; j < 3;j++){
                for(int k = 0; k < 3;k++){
                    if(gameBoard[i][j][k] == 0){
                        nextMoves.add(new int[]{i,j,k});
                    }
                }
            }
        }

        return nextMoves;
    }

    public int[][][] getGameBoard(){
        return gameBoard;
    }



    public GameMove makeComputerMove() {
        if(!updatePrioritiesForWinning()){
            if(!updatePrioritiesForBlockingOpponentFromWinning()){
                updatePrioritiesForWinningAndBlockingPaths();
            }
        }

        int maximum = -1;
        for(int i=0;i<priorities.length;i++){
            if(priorities[i] > maximum){
                maximum = priorities[i];
            }
        }

        List<Integer> entriesContainingTheMaximumPriority = new ArrayList<Integer>();
        for(int i=0;i<priorities.length;i++){
            if(priorities[i] == maximum){
                entriesContainingTheMaximumPriority.add(i);
            }
        }

        int indexOfChosenPlay = (int) Math.floor(Math.random() * entriesContainingTheMaximumPriority.size());
        int chosenPlay = entriesContainingTheMaximumPriority.get(indexOfChosenPlay);

        int z = divide(chosenPlay,9);
        int y = divide(chosenPlay - (z*9),3);
        int x = chosenPlay % 3;

        return new GameMove(x,y,z);

    }

    public int divide(int numerator,int denominator){
        int remainder = numerator % denominator;
        int quotient = (numerator - remainder) / denominator;

        return quotient;
    }

    public boolean updatePrioritiesForWinning(){
        List<List<Position>> winningPositions = GameLogic.getWinningPositions();

        for(int i=0;i<winningPositions.size();i++){
            Position first = winningPositions.get(i).get(0);
            Position second = winningPositions.get(i).get(1);
            Position third = winningPositions.get(i).get(2);

            PathStatus path  = new PathStatus(gameBoard,first,second,third);
            path.evaluate();

            if(path.getFriendly() == 2 && path.getNeutral() == 1){
                if(gameBoard[first.getBoardX()][first.getBoardY()][first.getBoardZ()] == 0){
                    priorities[first.getBoardX() + (first.getBoardY()*3) + (first.getBoardZ()*9)]++;
                }
                if(gameBoard[second.getBoardX()][second.getBoardY()][second.getBoardZ()] == 0){
                    priorities[second.getBoardX() + (second.getBoardY()*3) + (second.getBoardZ()*9)]++;
                }
                if(gameBoard[third.getBoardX()][third.getBoardY()][third.getBoardZ()] == 0){
                    priorities[third.getBoardX() + (third.getBoardY()*3) + (third.getBoardZ()*9)]++;
                }
                return true;
            }
        }

        return false;
    }

    public boolean updatePrioritiesForBlockingOpponentFromWinning(){
        List<List<Position>> winningPositions = GameLogic.getWinningPositions();

        for(int i=0;i<winningPositions.size();i++){
            Position first = winningPositions.get(i).get(0);
            Position second = winningPositions.get(i).get(1);
            Position third = winningPositions.get(i).get(2);

            PathStatus path  = new PathStatus(gameBoard,first,second,third);
            path.evaluate();

            if(path.getEnemy() == 2 && path.getNeutral() == 1){
                if(gameBoard[first.getBoardX()][first.getBoardY()][first.getBoardZ()] == 0){
                    priorities[first.getBoardX() + (first.getBoardY()*3) + (first.getBoardZ()*9)]++;
                }
                if(gameBoard[second.getBoardX()][second.getBoardY()][second.getBoardZ()] == 0){
                    priorities[second.getBoardX() + (second.getBoardY()*3) + (second.getBoardZ()*9)]++;
                }
                if(gameBoard[third.getBoardX()][third.getBoardY()][third.getBoardZ()] == 0){
                    priorities[third.getBoardX() + (third.getBoardY()*3) + (third.getBoardZ()*9)]++;
                }
                return true;
            }
        }

        return false;
    }

    public boolean updatePrioritiesForWinningAndBlockingPaths(){
        List<List<Position>> winningPositions = GameLogic.getWinningPositions();

        for(int i=0;i<winningPositions.size();i++){
            Position first = winningPositions.get(i).get(0);
            Position second = winningPositions.get(i).get(1);
            Position third = winningPositions.get(i).get(2);

            PathStatus path  = new PathStatus(gameBoard,first,second,third);
            path.evaluate();

            if(path.getNeutral() == 2){
                if(gameBoard[first.getBoardX()][first.getBoardY()][first.getBoardZ()] == 0){
                    priorities[first.getBoardX() + (first.getBoardY()*3) + (first.getBoardZ()*9)]++;
                }
                if(gameBoard[second.getBoardX()][second.getBoardY()][second.getBoardZ()] == 0){
                    priorities[second.getBoardX() + (second.getBoardY()*3) + (second.getBoardZ()*9)]++;
                }
                if(gameBoard[third.getBoardX()][third.getBoardY()][third.getBoardZ()] == 0){
                    priorities[third.getBoardX() + (third.getBoardY()*3) + (third.getBoardZ()*9)]++;
                }
                return true;
            }
        }

        return false;
    }

    public void makeGameMove(GameMove move, int player) {

        gameBoard[move.getX()][move.getY()][move.getZ()] = player;
    }

    public void resetPriorities(){
        for(int i=0;i<priorities.length;i++){
            priorities[i] = 0;
        }
    }

}

class GameMove {

    private int x;
    private int y;
    private int z;

    public GameMove(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals (Object o){
        GameMove m = (GameMove) o;

        if(this.getX() == m.getX() && this.getY() == m.getY() && this.getZ()==m.getZ()){
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }




    public int getY() {
        return y;
    }



    public int getZ() {
        return z;
    }

}