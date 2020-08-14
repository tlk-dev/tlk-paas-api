package com.tlk.api.jpa.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_jpa")
public class UserJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String password;

    @Builder
    public UserJpa(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Builder
    public UserJpa(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

}
