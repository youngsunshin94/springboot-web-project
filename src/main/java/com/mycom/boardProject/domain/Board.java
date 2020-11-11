package com.mycom.boardProject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 200)
    private String title;
    private String content;

    @Column(length = 50)
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @Column(columnDefinition = "integer default 0")
    private int hit;

    @Column(columnDefinition = "integer default 0")
    private int replyCnt;

    public Board() {
    }

    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.hit = 0;
        this.replyCnt = 0;
    }

    public void modifyDate() {
        this.updateDate = LocalDateTime.now();
    }
}
