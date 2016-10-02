package com.tictac.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Created by petar on 9/27/2016.
 */
@Getter
@Setter
public class PlayerDTO {
    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @Email
    private String email;
}
