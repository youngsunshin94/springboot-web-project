package com.mycom.boardProject.domain;

import javax.persistence.*;

@Entity
public class MemberAuth {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Auth auth;
}
