package com.mycom.boardProject.domain;

import lombok.Getter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Reply {

    @Id @GeneratedValue
    private Long rno;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "bno")
    private Board board;

    @Column(length = 1000)
    private String reply;

    @Column(length = 50)
    private String replyer;

    private LocalDateTime replyDate;
    private LocalDateTime updateDate;
}
