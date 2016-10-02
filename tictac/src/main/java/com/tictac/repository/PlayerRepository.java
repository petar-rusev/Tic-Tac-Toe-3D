package com.tictac.repository;

import com.tictac.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by petar on 9/26/2016.
 */

@Repository
public interface PlayerRepository extends CrudRepository<User, Long> {
    User findOneByUserName(String userName);
    User findOneByUserNameOrEmail(String userName, String email);
}
