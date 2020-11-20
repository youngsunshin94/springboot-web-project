package com.mycom.boardProject.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 100, nullable = false)
    private String password;

    @Column
    private String userName;

    @Column
    private String auth;

    @Builder
    public MemberEntity(Long id, String userId, String password, String auth, String userName) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.auth = auth;
        this.userName = userName;
    }
}
