package com.tictac.controllers.RestControllers;

/**
 * Created by petar on 9/29/2016.
 */
import com.tictac.DTO.CreateNewGameDTO;
import com.tictac.DTO.GameDTO;
import com.tictac.domain.Game;
import com.tictac.domain.User;
import com.tictac.enums.GameStatus;
import com.tictac.services.GameService;
import com.tictac.services.MoveService;
import com.tictac.services.PlayerService;
import com.tictac.services.AI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameRestController {
    @Autowired
    GameService gameService;

    @Autowired
    MoveService moveService;

    @Autowired
    PlayerService playerService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateNewGameDTO createNewGame(@RequestBody GameDTO gameDTO){
        CreateNewGameDTO createGameDTO = new CreateNewGameDTO();
        createGameDTO.setGameId(gameService.createNewGame(playerService.getLoggedUser(),gameDTO));

        return createGameDTO;
    }

    @RequestMapping(value = "/player/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, AI>> getPlayerGames(){
        List<Map<String, AI>> games = gameService.getPlayerGames(playerService.getLoggedUser());
        if(games != null){
            return games;
        }
        return new ArrayList<Map<String, AI>>();
    }

    @RequestMapping(value = "/{id}")
    public AI getGameProperties(@PathVariable String id){
        httpSession.setAttribute("gameId", id);

        return gameService.getGame(id);
    }
}
