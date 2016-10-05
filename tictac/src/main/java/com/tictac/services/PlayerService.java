package com.tictac.services;

import com.tictac.DTO.PlayerDTO;
import com.tictac.domain.User;
import com.tictac.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by petar on 9/27/2016.
 */

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public User createNewPlayer(PlayerDTO playerDTO){
        User newPlayer = new User();
        newPlayer.setUserName(playerDTO.getUserName());
        newPlayer.setPassword(passwordEncoder.encode(playerDTO.getPassword()));
        newPlayer.setEmail(playerDTO.getEmail());
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    public User getLoggedUser(){
        String principal =SecurityContextHolder.getContext().getAuthentication().getName();
        return playerRepository.findOneByUserName(principal);
    }

    public List<User> listPlayers(){
        List<User> players = (List<User>) playerRepository.findAll();
        return players;
    }
}
