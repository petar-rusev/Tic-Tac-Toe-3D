package com.tictac.repository;

import com.tictac.domain.Game;
import com.tictac.domain.Player;
import com.tictac.enums.GameStatus;
import com.tictac.enums.GameType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by petar on 9/26/2016.
 */

@Repository
public interface GameRepository extends CrudRepository<Game,Long> {
    List<Game> findByGameTypeAndGameStatus(GameType GameType, GameStatus GameStatus);
    List<Game> findByGameStatus(GameStatus gameStatus);

}