package com.tictac.service;

import com.tictac.DTO.CreateMoveDTO;
import com.tictac.DTO.GameDTO;
import com.tictac.DTO.MoveDTO;
import com.tictac.domain.Game;
import com.tictac.domain.Move;
import com.tictac.domain.Player;
import com.tictac.domain.Position;
import com.tictac.enums.GameType;
import com.tictac.enums.GameStatus;
import com.tictac.enums.Piece;
import com.tictac.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by petar on 9/27/2016.
 */
@Service
@Transactional
public class MoveService {

    private final MoveRepository moveRepository;

    @Autowired
    public MoveService(MoveRepository moveRepository){
        this.moveRepository = moveRepository;
    }

    public Move createMove(Game game,Player player, CreateMoveDTO createMoveDTO){
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
        Move move = new Move();
        //To do create game logic
        move.setBoardX(1);
        move.setBoardY(1);
        move.setBoardZ(1);
        move.setCreated(new Date());
        move.setPlayer(null);
        move.setGame(game);

        moveRepository.save(move);

        return move;
    }

    public GameStatus checkCurrentGameStatus(Game game){
        return GameStatus.IN_PROGRESS;
    }

    public List<MoveDTO> getMovesInGame(Game game){
        List<Move> movesInGame = moveRepository.findByGame(game);
        List<MoveDTO> moves = new ArrayList<>();
        Piece currentPiece = game.getFirstPlayerPieceCode();

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

            currentPiece = currentPiece == Piece.X ? Piece.O :Piece.X;

        }

        return moves;
    }

    public List<Position> getPlayerMovePositionsInGame(Game game,Player player){
        return moveRepository.findByGameAndPlayer(game,player).stream()
                .map(move -> new Position(move.getBoardX(),move.getBoardY(),move.getBoardZ()))
                .collect(Collectors.toList());
    }

    public int getTheNumberOfPlayerMovesInGame(Game game,Player player){
        return moveRepository.countByGameAndPlayer(game,player);
    }

    public boolean isPlayerTurn(Game game, Player firstPlayer, Player secondPlayer){
        //To do create game logic
        return true;
    }
}
