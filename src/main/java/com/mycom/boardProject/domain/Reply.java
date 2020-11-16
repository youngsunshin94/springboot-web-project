package com.mycom.boardProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "bno")
    private Board board;

    @Column(length = 1000)
    private String reply;

    @Column(length = 50)
    private String replyer;

    private LocalDateTime replyDate;
    private LocalDateTime updateDate;

    public Reply() {
    }

    public Reply(Board board, String reply, String replyer) {
        this.board = board;
        this.reply = reply;
        this.replyer = replyer;
        this.replyDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public void updateReply(String reply) {
        this.reply = reply;
        this.updateDate = LocalDateTime.now();
    }
}
