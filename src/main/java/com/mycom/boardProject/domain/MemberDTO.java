package com.mycom.boardProject.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String userId;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String auth;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(id)
                .userId(userId)
                .password(password)
                .auth(auth)
                .userName(userName)
                .build();
    }

    @Builder
    public MemberDTO(Long id, String userId, String password, String auth, String userName) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.auth = auth;
        this.userName = userName;
    }
}
