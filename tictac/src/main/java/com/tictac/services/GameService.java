package com.tictac.services;

import com.tictac.DTO.CreateMoveResponseDTO;
import com.tictac.DTO.GameDTO;
import com.tictac.domain.User;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class GameService {

    Map<Long, List<Map<String, AI>>> activeGames = ExpiringMap.builder().expirationPolicy(ExpiringMap.ExpirationPolicy.ACCESSED).expiration(450, TimeUnit.HOURS).build();
    @Autowired
    private PlayerService playerService;

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
        AI newGame = new AI();

        if(gameDTO.getTurn().equals("second")){
            newGame.getGameBoard()[1][1][1] = 1;
        }

        if(userGames==null){
            userGames = new ArrayList<Map<String, AI>>();
            Map<String, AI> g = new HashMap<String, AI>();
            g.put(uniqueKey,newGame);
            userGames.add(g);
            activeGames.put(player.getId(), userGames);
        }else{
            Map<String, AI> g = new HashMap<String, AI>();
            g.put(uniqueKey,newGame);
            userGames.add(g);
        }


        return uniqueKey;
    }

    public CreateMoveResponseDTO move(int i, int j, int z,String gameId) {

        AI game = null;
        GameMove gameMove = new GameMove(i, j, z);
        //Get game by id
        List<Map<String, AI>> games =  activeGames.get(playerService.getLoggedUser().getId());

        for(int v =0; v<games.size();v++){
            if(games.get(v).containsKey(gameId)){
                game = games.get(v).get(gameId);
            }
        }
        if(game == null){
            throw new IllegalArgumentException();
        }

        if(GameLogic.checkForWinner(game.getGameBoard()) == 0 && !GameLogic.checkForStalemate(game.getGameBoard())){

            game.makeGameMove(gameMove,2);

            GameMove move = game.makeComputerMove();
            game.makeGameMove(move, 1);
            game.resetPriorities();
        }

        if(GameLogic.checkForStalemate(game.getGameBoard())){
            CreateMoveResponseDTO moveResponceDTO = new CreateMoveResponseDTO(game.getGameBoard());
            moveResponceDTO.setMessage("The game is Draw!");
            return moveResponceDTO;
        }
        if(GameLogic.checkForWinner(game.getGameBoard()) == 2){
            CreateMoveResponseDTO moveResponceDTO = new CreateMoveResponseDTO(game.getGameBoard());
            moveResponceDTO.setMessage("Congratulations you win!");
            return moveResponceDTO;
        }


        if(GameLogic.checkForWinner(game.getGameBoard()) == 1){
            CreateMoveResponseDTO moveResponceDTO = new CreateMoveResponseDTO(game.getGameBoard());
            moveResponceDTO.setMessage("Computer wins!");
            return moveResponceDTO;
        }


        return new CreateMoveResponseDTO(game.getGameBoard());
    }
}
