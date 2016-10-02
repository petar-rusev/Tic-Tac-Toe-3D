package com.tictac.security;

import com.tictac.domain.User;
import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Created by petar on 9/26/2016.
 */
public class ContextUser extends org.springframework.security.core.userdetails.User {

    private final User player;

    public ContextUser(User player) {
        super(player.getUserName(),
                player.getPassword(),
                true,
                true,
                true,
                true,
                ImmutableSet.of(new SimpleGrantedAuthority("create")));

        this.player = player;

    }

    public User getPlayer() {
        return  player;
    }
}
