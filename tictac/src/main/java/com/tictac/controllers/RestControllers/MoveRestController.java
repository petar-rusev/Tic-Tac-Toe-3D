package com.tictac.controllers.RestControllers;

/**
 * Created by petar on 9/29/2016.
 */
import com.tictac.DTO.CreateMoveDTO;
import com.tictac.DTO.CreateMoveResponseDTO;
import com.tictac.services.*;
import com.tictac.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/move")
public class MoveRestController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CreateMoveResponseDTO createMove(@RequestBody CreateMoveDTO createMoveDTO){

        return gameService.move(createMoveDTO.getBoardX(),createMoveDTO.getBoardY(),createMoveDTO.getBoardZ(),createMoveDTO.getGameId());
    }

}
