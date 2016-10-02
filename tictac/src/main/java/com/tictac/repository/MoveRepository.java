package com.tictac.repository;

import com.tictac.domain.Game;
import com.tictac.domain.Move;
import com.tictac.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by petar on 9/26/2016.
 */

@Repository
public interface MoveRepository extends CrudRepository<Move,Long> {
    List<Move> findByGame(Game game);
    List<Move> findByGameAndPlayer(Game game, User player);
    int countByGameAndPlayer(Game game, User player);
}
