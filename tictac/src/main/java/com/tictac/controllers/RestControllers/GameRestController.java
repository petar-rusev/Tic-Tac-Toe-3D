package com.tictac.controllers.RestControllers;

/**
 * Created by petar on 9/29/2016.
 */
import com.tictac.DTO.GameDTO;
import com.tictac.domain.Game;
import com.tictac.domain.User;
import com.tictac.enums.GameStatus;
import com.tictac.services.GameService;
import com.tictac.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameRestController {
    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Game createNewGame(@RequestBody GameDTO gameDTO){
        Game game = gameService.createNewGame(playerService.getLoggedUser(),gameDTO);
        httpSession.setAttribute("gameId",game.getId());

        return game;
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Game joinGame(@RequestBody GameDTO gameDTO){
        Game game = gameService.joinGame(playerService.getLoggedUser(),gameDTO);
        return game;
    }

    @RequestMapping(value = "/player/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getPlayerGames(){
        return gameService.getPlayerGames(playerService.getLoggedUser());
    }

    @RequestMapping(value = "/{id}")
    public Game getGameProperties(@PathVariable Long id){
        httpSession.setAttribute("gameId", id);

        return gameService.getGame(id);
    }
}
