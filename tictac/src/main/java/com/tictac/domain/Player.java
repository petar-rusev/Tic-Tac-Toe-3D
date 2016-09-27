package com.tictac.domain;

/**
 * Created by petar on 9/26/2016.
 */

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data

public class Player {

    public Player(){}

    public Player(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

}
