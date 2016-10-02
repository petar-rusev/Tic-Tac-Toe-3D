package com.tictac.domain;

/**
 * Created by petar on 9/30/2016.
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "authorities")
public class Authority implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String authority;

    public Authority (String username, String authority){
        this.setUsername(username);
        this.setAuthority(authority);
    }


}
