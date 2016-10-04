package com.tictac.services;

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
    Map<Long, List<Map<String, TicTacToe>>> activeGames = ExpiringMap.builder().expirationPolicy(ExpiringMap.ExpirationPolicy.ACCESSED).expiration(450, TimeUnit.HOURS).build();
    @Autowired
    private PlayerService playerService;
    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;

    }

    public List<Map<String, TicTacToe>> getPlayerGames(User player){
        return activeGames.get(playerService.getLoggedUser().getId());
    }


    public  TicTacToe getGame(String id){
        List<Map<String, TicTacToe>> games =  activeGames.get(playerService.getLoggedUser().getId());
        TicTacToe  game;
        for(int i =0; i<games.size();i++){
            if(games.get(i).containsKey(id)){
                return games.get(i).get(id);
            }

        }
        return new TicTacToe();
    }

    public String createNewGame(User player, GameDTO gameDTO){
        String uniqueKey = UUID.randomUUID().toString();
        Map game =   new HashMap<String,TicTacToe>();

        List<Map<String, TicTacToe>> userGames=  activeGames.get(player.getId());

        if(userGames==null){
            userGames = new ArrayList<Map<String, TicTacToe>>();
            Map<String, TicTacToe> g = new HashMap<String, TicTacToe>();
            g.put(uniqueKey,new TicTacToe() );
            userGames.add(g);
            activeGames.put(player.getId(), userGames);
        }else{
            Map<String, TicTacToe> g = new HashMap<String, TicTacToe>();
            g.put(uniqueKey,new TicTacToe() );
            userGames.add(g);
        }


        return uniqueKey;
    }

    public int[][][] move(int i, int j, int z,String gameId) {

            TicTacToe game = null;
            GameMove gameMove = new GameMove(i, j, z);
            //Get game by id
            List<Map<String, TicTacToe>> games =  activeGames.get(playerService.getLoggedUser().getId());

            for(int v =0; v<games.size();v++){
                if(games.get(v).containsKey(gameId)){
                    game = games.get(v).get(gameId);
                }
            }

            if(game==null){
                throw  new IllegalArgumentException("The game does not exist");
            }



            game.makeGameMove(gameMove, 2);

            game.minimax(0, 1);

            game.makeGameMove(game.computersGameMove, 1);

            game.resetBoard();
            if(game.getAvailableGameMoves().size()==0){
                //TODO Remove form the Map
            }
            return game.getGameBoard();
    }
}
