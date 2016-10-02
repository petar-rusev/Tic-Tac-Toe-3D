package com.tictac.services;

/**
 * Created by petar on 9/30/2016.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tictac.domain.Authority;
import com.tictac.domain.User;
import com.tictac.DTO.PlayerDTO;
import com.tictac.repository.PlayerRepository;

@Service
public class AuthenticationService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private StandardPasswordEncoder standardPasswordEncoder;

    public String registerUser(PlayerDTO playerDTO,BindingResult result){
        if(!playerDTO.getPassword().equals(playerDTO.getConfirmPassword())){
            result.rejectValue("email",null);

            return "views/userAuthentication/registration";
        }

        User player = playerRepository.findOneByUserNameOrEmail(playerDTO.getUserName(),playerDTO.getEmail());
        if(player == null){
            playerDTO.setPassword(standardPasswordEncoder.encode(playerDTO.getPassword()));
            player = new User(playerDTO.getUserName(),playerDTO.getEmail(),playerDTO.getPassword());
            Authority auth = new Authority(playerDTO.getUserName(),"ROLE_USER");
            player.setAuthority(auth);
            playerRepository.save(player);

            return "redirect:/login";
        }else{
            result.rejectValue("email",null);

            return "views/userAuthentication/registration";
        }

    }
}
