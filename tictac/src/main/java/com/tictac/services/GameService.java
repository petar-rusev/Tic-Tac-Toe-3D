package com.tictac.services;

import com.tictac.DTO.CreateMoveResponceDTO;
import com.tictac.DTO.GameDTO;
import com.tictac.domain.User;
import com.tictac.repository.GameRepository;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by petar on 9/27/2016.
 */
@Service
@Transactional
public class GameService {
    private final GameRepository gameRepository;
    Map<Long, List<Map<String, AI>>> activeGames = ExpiringMap.builder().expirationPolicy(ExpiringMap.ExpirationPolicy.ACCESSED).expiration(450, TimeUnit.HOURS).build();
    @Autowired
    private PlayerService playerService;
    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;

    }

    public List<Map<String, AI>> getPlayerGames(User player){
        return activeGames.get(playerService.getLoggedUser().getId());
    }


    public  AI getGame(String id){
        List<Map<String, AI>> games =  activeGames.get(playerService.getLoggedUser().getId());
        AI  game;
        for(int i =0; i<games.size();i++){
            if(games.get(i).containsKey(id)){
                return games.get(i).get(id);
            }

        }
        return new AI();
    }

    public String createNewGame(User player, GameDTO gameDTO){
        String uniqueKey = UUID.randomUUID().toString();
        Map game =   new HashMap<String,AI>();

        List<Map<String, AI>> userGames=  activeGames.get(player.getId());

        if(userGames==null){
            userGames = new ArrayList<Map<String, AI>>();
            Map<String, AI> g = new HashMap<String, AI>();
            g.put(uniqueKey,new AI());
            userGames.add(g);
            activeGames.put(player.getId(), userGames);
        }else{
            Map<String, AI> g = new HashMap<String, AI>();
            g.put(uniqueKey,new AI());
            userGames.add(g);
        }


        return uniqueKey;
    }

    public CreateMoveResponceDTO move(int i, int j, int z,String gameId) {

        AI game = null;
        GameMove gameMove = new GameMove(i, j, z);
        //Get game by id
        List<Map<String, AI>> games =  activeGames.get(playerService.getLoggedUser().getId());

        for(int v =0; v<games.size();v++){
            if(games.get(v).containsKey(gameId)){
                game = games.get(v).get(gameId);
            }
        }


        if(game.getAvailableGameMoves().size()==0){
            CreateMoveResponceDTO moveResponceDTO = new CreateMoveResponceDTO(game.getGameBoard());
            moveResponceDTO.setMessage("The game is finished!");
            return moveResponceDTO;
        }

        if(game==null){
            CreateMoveResponceDTO moveResponceDTO = new CreateMoveResponceDTO();
            moveResponceDTO.setMessage("The game does not exist!");
            return moveResponceDTO;
        }

        if(GameLogic.hasWinner(game.getGameBoard())){
            CreateMoveResponceDTO moveResponceDTO = new CreateMoveResponceDTO(game.getGameBoard());
            moveResponceDTO.setMessage("The game has winner!");
            return moveResponceDTO;
        }

        game.makeGameMove(gameMove, 2);

        game.minimax(0, 1);

        game.makeGameMove(game.computersGameMove, 1);

        game.resetBoard();



        return new CreateMoveResponceDTO(game.getGameBoard());
    }
}
