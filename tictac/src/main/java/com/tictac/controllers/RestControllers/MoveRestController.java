package com.tictac.controllers.RestControllers;

/**
 * Created by petar on 9/29/2016.
 */
import com.tictac.DTO.CreateMoveDTO;
import com.tictac.DTO.CreateMoveResponceDTO;
import com.tictac.DTO.MoveDTO;
import com.tictac.domain.Game;
import com.tictac.domain.Move;
import com.tictac.services.*;
import com.tictac.services.PlayerService;
import com.tictac.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/move")
public class MoveRestController {

    @Autowired
    private MoveService moveService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CreateMoveResponceDTO createMove(@RequestBody CreateMoveDTO createMoveDTO){

        return gameService.move(createMoveDTO.getBoardX(),createMoveDTO.getBoardY(),createMoveDTO.getBoardZ(),createMoveDTO.getGameId());
    }

//    @RequestMapping(value = "/autocreate", method = RequestMethod.GET)
//    public Move autoCreateMove(){
//        Long gameId = (Long) httpSession.getAttribute("gameId");
//
//        Move move = moveService.autoCreateMove(gameService.getGame(gameId));
//
//        Game game = gameService.getGame(gameId);
//        gameService.updateGameStatus(gameService.getGame(gameId),moveService.checkCurrentGameStatus(game));
//
//        return move;
//    }

//    @RequestMapping(value = "/list",method = RequestMethod.GET)
//    public List<MoveDTO> getMovesInGame(){
//        Long gameId = (Long) httpSession.getAttribute("gameId");
//
//        return moveService.getMovesInGame(gameService.getGame(gameId));
//    }

//    @RequestMapping(value = "/check", method = RequestMethod.GET)
//    public List<Position> validateMoves(){
//        Long gameId = (Long) httpSession.getAttribute("gameId");
//        return moveService.getPlayerMovePositionsInGame(gameService.getGame(gameId),playerService.getLoggedUser());
//    }

//    @RequestMapping(value = "/turn",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public boolean isPlayerTurn(){
//        Long gameId = (Long) httpSession.getAttribute("gameId");
//        return moveService.isPlayerTurn(gameService.getGame(gameId),gameService.getGame(gameId).getFirstPlayer(),gameService.getGame(gameId).getSecondPlayer());
//    }
}
