package com.tictac.services;

import com.tictac.DTO.GameDTO;
import com.tictac.domain.Game;
import com.tictac.domain.User;
import com.tictac.enums.GameStatus;
import com.tictac.enums.GameType;
import com.tictac.repository.GameRepository;
import com.tictac.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by petar on 9/27/2016.
 */
@Service
@Transactional
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public Game createNewGame(User player, GameDTO gameDTO){
        Game game  = new Game();
        game.setFirstPlayer(player);
        game.setGameType(gameDTO.getGameType());
        game.setFirstPlayerPieceCode(gameDTO.getPiece());
        game.setGameStatus(GameStatus.IN_PROGRESS.toString());
        game.setCreated(new Date());
        gameRepository.save(game);

        return game;
    }

    public Game updateGameStatus(Game game, String gameStatus){
        Game g  = getGame(game.getId());
        g.setGameStatus(gameStatus);

        return g;
    }

    public Game joinGame(User player, GameDTO gameDTO){
        Game game  = getGame((long)gameDTO.getId());
        game.setSecondPlayer(player);
        gameRepository.save(game);

        updateGameStatus(game, GameStatus.IN_PROGRESS.toString());

        return game;
    }

    public List<Game> getPlayerGames(User player){
        return gameRepository.findAll()
                .stream()
                .filter(game -> game.getFirstPlayer().getUserName() == player.getUserName()).collect(Collectors.toList());
    }


    public Game getGame(Long id){
        return gameRepository.findOne(id);
    }
}
