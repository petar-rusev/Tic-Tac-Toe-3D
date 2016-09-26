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

    static boolean isWinner(List<Position> positions){
        return getWinningPositions().stream().anyMatch(positions::containsAll);
    }

    static List<List<Position>> getWinningPositions(){
        List<List<Position>> winningPositions = new ArrayList<>();

        winningPositions.add(asList(new Position(1,1,1), new Position(1,2,1), new Position(1,3,1)));
        winningPositions.add(asList(new Position(2,1,1), new Position(2,2,1), new Position(2,3,1)));
        winningPositions.add(asList(new Position(3,1,1), new Position(3,2,1), new Position(3,3,1)));

        winningPositions.add(asList(new Position(1,1,2), new Position(1,2,2), new Position(1,3,2)));
        winningPositions.add(asList(new Position(2,1,2), new Position(2,2,2), new Position(2,3,2)));
        winningPositions.add(asList(new Position(3,1,2), new Position(3,2,2), new Position(3,3,2)));

        winningPositions.add(asList(new Position(1,1,3), new Position(1,2,3), new Position(1,3,3)));
        winningPositions.add(asList(new Position(2,1,3), new Position(2,2,3), new Position(2,3,3)));
        winningPositions.add(asList(new Position(3,1,3), new Position(3,2,3), new Position(3,3,3)));

        return winningPositions;
    }

    static List<Position> getAllPositions(){
        List<Position> positions = new ArrayList<>();

        for(int row = 1; row <= 3; row++){
            for(int col = 1; col <= 3; col++){
                for(int level = 1; level <= 3; level++){
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
