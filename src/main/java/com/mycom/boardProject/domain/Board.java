package com.mycom.boardProject.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Board {

    @Id @GeneratedValue
    private Long bno;

    @Column(length = 200)
    private String title;
    private String content;

    @Column(length = 50)
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private int hit;
    private int replyCnt;

    public Board() {
    }

    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
