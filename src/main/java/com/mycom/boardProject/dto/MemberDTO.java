package com.mycom.boardProject.dto;

import com.mycom.boardProject.domain.MemberEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {

    private Long id;

    @NotBlank(message = "ID를 입력해주세요.")
    private String userId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
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
