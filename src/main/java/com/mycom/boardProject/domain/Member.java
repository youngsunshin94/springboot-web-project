package com.mycom.boardProject.domain;

import lombok.Cleanup;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @Column(length = 50)
    private String userId;

    @Column(length = 100)
    private String userPw;

    @Column(length = 100)
    private String userName;

    @Enumerated(EnumType.STRING)
    private Auth auth;
}
