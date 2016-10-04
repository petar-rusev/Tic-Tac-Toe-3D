package com.tictac.services;

import com.tictac.domain.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class TicTacToe {

       private int gameBoard[][][] = new int[3][3][3];

       private List<GameMove> availableGameMoves;

       public GameMove computersGameMove;

       public List<GameMove> getAvailableGameMoves() {

           availableGameMoves = new ArrayList<GameMove>();
           for (int i = 0; i < 3; i++) {
               for (int j = 0; j < 3; j++) {
                   for (int z = 0; z < 3; z++) {
                       if (gameBoard[i][j][z] == 0)
                           availableGameMoves.add(new GameMove(i, j, z));
                   }
               }
           }
           return availableGameMoves;
       }

        public void resetBoard() {


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int z = 0; z < 3; z++) {
                        if (gameBoard[i][j][z] == 3)
                            gameBoard[i][j][z] = 0;
                    }
                }
            }

        }

       public int[][][] getGameBoard(){
           return gameBoard;
       }

       // the minimax algorithm
       public int minimax(int depth, int turn) {

           if (GameLogic.hasWinner(gameBoard))
               return turn;

           List<GameMove> gameMovesAvailable = getAvailableGameMoves();
           if (gameMovesAvailable.isEmpty())
               return 0;

           int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

           for (int i = 0; i < gameMovesAvailable.size(); ++i) {

               GameMove gameMove = gameMovesAvailable.get(i);

               if (turn == 1) {
                   makeGameMove(gameMove, 1);
                   int currentScore = minimax(depth + 1, 2);
                   max = Math.max(currentScore, max);

                   if (depth == 0)
                       System.out.println("info: score for position " + (i+1) + ": " + currentScore);
                   if (currentScore >= 0) {
                       if (depth == 0)
                           computersGameMove = gameMove;
                   } else if (currentScore == 1) {
                       resetGameMove(gameMove);
                       break;
                   }
                   if (i == availableGameMoves.size() - 1 && max > 0) {
                       if (depth == 0)
                           computersGameMove = gameMove;
                   }
               } else if (turn == 2) {
                   makeGameMove(gameMove, 2);
                   int currentScore = minimax(depth + 1, 1);
                   min = Math.min(currentScore, min);
                   if (min == -1) {
                       resetGameMove(gameMove);
                       break;
                   }
               }
//               System.out.print(gameBoard);
                resetGameMove(gameMove);
               System.out.print("ddddd");
//               System.out.print(gameBoard);
           }
           return (turn == 1) ? max : min;
       }

       public void resetGameMove(GameMove move) {
           gameBoard[move.getX()][move.getY()][move.getZ()] = 3;
       }

       public void makeGameMove(GameMove move, int player) {

           gameBoard[move.getX()][move.getY()][move.getZ()] = player;
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