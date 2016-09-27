package com.tictac.service;

import com.tictac.domain.Game;
import com.tictac.domain.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by petar on 9/27/2016.
 */

public class GameLogic {
    private final Game game;

    public GameLogic(Game game){
        this.game = game;
    }

    public static boolean checkVertical3DWin(List<Position>positions){
        int counter=0;

        for(int i= 0 ;i < positions.size();i++){
            Position tempOne = positions.get(i);
            for(int j = i+1;j < positions.size();j++){
                if(counter == 3){
                    break;
                }
                if(tempOne.getBoardX() == positions.get(j).getBoardX() ||
                        tempOne.getBoardY() == positions.get(j).getBoardY() &&
                                tempOne.getBoardZ() != positions.get(j).getBoardZ()){
                    counter++;
                }
            }
        }

        return counter == 3;
    }

    public static boolean checkVerticalOneLayerWin(List<Position>positions){
        int counter = 0;

        for(int i=0;i < positions.size();i++){
            Position tempOne = positions.get(i);
            for(int j = i+1;j < positions.size();j++){
                if(counter == 3){
                    break;
                }
                if(tempOne.getBoardZ() == positions.get(j).getBoardZ() &&
                        tempOne.getBoardY() == positions.get(j).getBoardY()){
                    counter++;
                }
            }
        }

        return counter == 3;
    }

    public static boolean checkHorizontalOneLayerWin(List<Position>positions){
        int counter = 0;

        for(int i=0;i < positions.size();i++){
            Position tempOne = positions.get(i);
            for(int j = i+1;j < positions.size();j++){
                if(counter == 3){
                    break;
                }
                if(tempOne.getBoardZ() == positions.get(j).getBoardZ() &&
                        tempOne.getBoardX() == positions.get(j).getBoardX()){
                    counter++;
                }
            }
        }

        return counter == 3;
    }

    public static boolean checkDiagonalsOneLayerWin(List<Position>positions){

        int counterLeftTopToRightBottom = 0;
        int counterLeftBottomToRightTop = 0;

        //Check leftTop to rightBottom diagonal
        for(int i=0;i<positions.size();i++){

            if(positions.get(i).getBoardX() == 3 &&
                positions.get(i).getBoardY() == 1 ||
                    positions.get(i).getBoardX() == 2 &&
                        positions.get(i).getBoardY() == 2 ||
                            positions.get(i).getBoardX() == 1 &&
                                    positions.get(i).getBoardY() == 3
                ){
                if(counterLeftTopToRightBottom == 3){
                     break;
                }
                counterLeftTopToRightBottom++;
            }
        }

        //Check leftBottom to rightTop diagonal
        for(int i=0;i<positions.size();i++){
            if(positions.get(i).getBoardX() == 1 &&
                    positions.get(i).getBoardY() == 1 ||
                    positions.get(i).getBoardX() == 2 &&
                            positions.get(i).getBoardY() == 2 ||
                    positions.get(i).getBoardX() == 3 &&
                            positions.get(i).getBoardY() == 3
                    ){
                if(counterLeftBottomToRightTop == 3){
                    break;
                }
                counterLeftBottomToRightTop++;

            }
        }

        return counterLeftBottomToRightTop == 3 || counterLeftTopToRightBottom == 3;
    }

    static boolean isWinner(List<Position> positions){
        return checkVertical3DWin(positions);
    }

    static List<Position> getAllPositions(){
        List<Position> positions = new ArrayList<>();

        for(int level = 1; level <= 3; level++){
            for(int row = 1; row <= 3; row++){
                for(int col = 1; col <= 3; col++){
                    positions.add(new Position(row,col,level));
                }
            }
        }

        return positions;
    }

    static boolean playerTurn(int numberOfFirstPlayerMovesInGame, int numberOfSecondPlayerMovesInGame){
        return numberOfFirstPlayerMovesInGame == numberOfSecondPlayerMovesInGame || numberOfFirstPlayerMovesInGame == 0;
    }

    static boolean isBoardIsFull(List<Position> takenPositions){
        return takenPositions.size() == 27;
    }

    static List<Position> getOpenPositions(List<Position> takenPositions){
        return getAllPositions().stream().filter(p -> !takenPositions.contains(p)).collect(Collectors.toList());
    }

    static Position nextAutoMove(List<Position> takenPositions){
        return getOpenPositions(takenPositions).get(0);
    }
}
