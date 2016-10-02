package com.tictac.services;

import com.tictac.DTO.CreateMoveDTO;
import com.tictac.DTO.GameDTO;
import com.tictac.DTO.MoveDTO;
import com.tictac.domain.Game;
import com.tictac.domain.Move;
import com.tictac.domain.User;
import com.tictac.domain.Position;
import com.tictac.domain.User;
import com.tictac.enums.GameType;
import com.tictac.enums.GameStatus;
import com.tictac.enums.Piece;
import com.tictac.repository.MoveRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by petar on 9/27/2016.
 */
@Service
@Transactional
@Getter
@Setter
public class MoveService {

    private final MoveRepository moveRepository;
    private Move computerGameMove;
    @Autowired
    public MoveService(MoveRepository moveRepository){
        this.moveRepository = moveRepository;
    }

    public Move createMove(Game game, User player, CreateMoveDTO createMoveDTO){
        Move move  = new Move();
        move.setBoardX(createMoveDTO.getBoardX());
        move.setBoardY(createMoveDTO.getBoardY());
        move.setBoardZ(createMoveDTO.getBoardZ());
        move.setCreated(new Date());
        move.setPlayer(player);
        move.setGame(game);

        moveRepository.save(move);

        return move;
    }

    public Move autoCreateMove(Game game){
        minimax(3,game);
        return computerGameMove;
    }

    public void resetMove(Move move){
        moveRepository.delete(move);
    }

    public String checkCurrentGameStatus(Game game){
        if(isWinner(getPlayerMovePositionsInGame(game,game.getFirstPlayer()))){
            return GameStatus.FIRST_PLAYER_WON.toString();
        }
        else if(isWinner(getPlayerMovePositionsInGame(game,game.getSecondPlayer()))){
            return GameStatus.SECOND_PLAYER_WON.toString();
        }
        else if(isBoardIsFull(getTakenMovePositionsInGame(game))){
            return GameStatus.TIE.toString();
        }
        else{
            return GameStatus.IN_PROGRESS.toString();
        }
    }

    public List<MoveDTO> getMovesInGame(Game game){
        List<Move> movesInGame = moveRepository.findByGame(game);
        List<MoveDTO> moves = new ArrayList<>();
        String currentPiece = game.getFirstPlayerPieceCode();

        for(Move move : movesInGame){
            MoveDTO moveDTO = new MoveDTO();
            moveDTO.setBoardX(move.getBoardX());
            moveDTO.setBoardY(move.getBoardY());
            moveDTO.setBoardZ(move.getBoardZ());
            moveDTO.setCreated(move.getCreated());
            moveDTO.setGameStatus(move.getGame().getGameStatus());
            moveDTO.setUserName(move.getPlayer() == null ? GameType.COMPUTER.toString() : move.getPlayer().getUserName());
            moveDTO.setPiece(currentPiece);
            moves.add(moveDTO);

            currentPiece = currentPiece == "X" ? "O" : "X";
        }

        return moves;
    }

    public List<Position> getTakenMovePositionsInGame(Game game){
        return moveRepository.findByGame(game).stream()
                .map(move -> new Position(move.getBoardX(),move.getBoardY(),move.getBoardZ()))
                .collect(Collectors.toList());
    }

    public List<Position> getPlayerMovePositionsInGame(Game game, User player){
        return moveRepository.findByGameAndPlayer(game,player).stream()
                .map(move -> new Position(move.getBoardX(),move.getBoardY(),move.getBoardZ()))
                .collect(Collectors.toList());
    }

    public int getTheNumberOfPlayerMovesInGame(Game game, User player){
        return moveRepository.countByGameAndPlayer(game,player);
    }

    public boolean isPlayerTurn(Game game, User firstPlayer, User secondPlayer){
        return playerTurn(getTheNumberOfPlayerMovesInGame(game,firstPlayer),getTheNumberOfPlayerMovesInGame(game,secondPlayer));
    }

    //Game Logic
    private boolean isWinner(List<Position> positions){
        return getWinningPositions().stream().anyMatch(positions::containsAll);
    }

    private List<List<Position>> getWinningPositions(){
        List<List<Position>> winningPositions = new ArrayList<>();
        //Everything in the bottom level (z=0)
        winningPositions.add(asList(new Position(0,0,0), new Position(1,0,0), new Position(2,0,0)));
        winningPositions.add(asList(new Position(0,1,0), new Position(1,1,0), new Position(2,1,0)));
        winningPositions.add(asList(new Position(0,2,0), new Position(1,2,0), new Position(2,2,0)));
        winningPositions.add(asList(new Position(0,0,0), new Position(0,1,0), new Position(0,2,0)));
        winningPositions.add(asList(new Position(1,0,0), new Position(1,1,0), new Position(1,2,0)));
        winningPositions.add(asList(new Position(2,0,0), new Position(2,1,0), new Position(2,2,0)));
        winningPositions.add(asList(new Position(0,0,0), new Position(1,1,0), new Position(0,2,0)));
        winningPositions.add(asList(new Position(2,0,0), new Position(1,1,0), new Position(0,2,0)));

        //Everything in the middle level (z=1)
        winningPositions.add(asList(new Position(0,0,1), new Position(1,0,1), new Position(2,0,1)));
        winningPositions.add(asList(new Position(0,1,1), new Position(1,1,1), new Position(2,1,1)));
        winningPositions.add(asList(new Position(0,2,1), new Position(1,2,1), new Position(2,2,1)));
        winningPositions.add(asList(new Position(0,0,1), new Position(0,1,1), new Position(0,2,1)));
        winningPositions.add(asList(new Position(1,0,1), new Position(1,1,1), new Position(1,2,1)));
        winningPositions.add(asList(new Position(2,0,1), new Position(2,1,1), new Position(2,2,1)));
        winningPositions.add(asList(new Position(0,0,1), new Position(1,1,1), new Position(0,2,1)));
        winningPositions.add(asList(new Position(2,0,1), new Position(1,1,1), new Position(0,2,1)));

        //Everything in the top layer (z=2)
        winningPositions.add(asList(new Position(0,0,2), new Position(1,0,2), new Position(2,0,2)));
        winningPositions.add(asList(new Position(0,1,2), new Position(1,1,2), new Position(2,1,2)));
        winningPositions.add(asList(new Position(0,2,2), new Position(1,2,2), new Position(2,2,2)));
        winningPositions.add(asList(new Position(0,0,2), new Position(0,1,2), new Position(0,2,2)));
        winningPositions.add(asList(new Position(1,0,2), new Position(1,1,2), new Position(1,2,2)));
        winningPositions.add(asList(new Position(2,0,2), new Position(2,1,2), new Position(2,2,2)));
        winningPositions.add(asList(new Position(0,0,2), new Position(1,1,2), new Position(0,2,2)));
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
        winningPositions.add(asList(new Position(1,2,0), new Position(1,1,1), new Position(1,2,2)));
        winningPositions.add(asList(new Position(2,0,0), new Position(2,1,1), new Position(2,2,2)));

        //All the diagonal columns - left to right
        winningPositions.add(asList(new Position(0,0,0), new Position(1,0,1), new Position(2,0,2)));
        winningPositions.add(asList(new Position(0,1,0), new Position(1,1,1), new Position(0,1,2)));
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

    private List<Position> getAllPositions(){
        List<Position> positions = new ArrayList<>();

        for(int level = 0; level <= 2; level++){
            for(int row = 0; row <= 2; row++){
                for(int col = 0; col <= 2; col++){
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

    //GENERATE COMPUTER'S MOVES
    private List<Position> getOpenPositions(List<Position> takenPositions){
        return getAllPositions().stream().filter(p -> !takenPositions.contains(p)).collect(Collectors.toList());
    }

    //The minimax algorithm
    private int minimax(int depth,Game game){

        String currentGameStatus = checkCurrentGameStatus(game);

        if(currentGameStatus == GameStatus.FIRST_PLAYER_WON.toString()){
            return +1;
        }
        if(currentGameStatus == GameStatus.SECOND_PLAYER_WON.toString()){
            return -1;
        }

        List<Position> gameMovesAvailable = getOpenPositions(getTakenMovePositionsInGame(game));

        if(gameMovesAvailable.isEmpty()){
            return 0;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for(int i = 0; i<gameMovesAvailable.size();++i){
            Position gameMove = gameMovesAvailable.get(i);
            CreateMoveDTO moveDTO = new CreateMoveDTO(gameMove.getBoardX(),gameMove.getBoardY(),gameMove.getBoardZ());
            if(!isPlayerTurn(game,game.getFirstPlayer(),game.getSecondPlayer())){

                Move createdMove = createMove(game,null,moveDTO);

                int currentScore = minimax(depth+1,game);
                max = Math.max(currentScore,max);

                if(currentScore >= 0){
                    if(depth == 0){
                        computerGameMove = createdMove;
                    }
                }else if(currentScore == 1){
                    resetMove(createdMove);
                    break;
                }
                if(i == gameMovesAvailable.size() - 1 && max > 0){
                    if(depth == 0){
                        computerGameMove = createdMove;
                        break;
                    }
                }
            }

        }

        return (isPlayerTurn(game,game.getFirstPlayer(),game.getSecondPlayer())) ? max : min;
    }

}