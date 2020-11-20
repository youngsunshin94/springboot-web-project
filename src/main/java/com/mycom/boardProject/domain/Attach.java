package com.mycom.boardProject.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Attach {

    @Id
    private String uuid;

    @Column(length = 200)
    private String uploadPath;

    @Column(length = 100)
    private String fileName;

    private boolean fileType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "bno")
    private Board board;

    public Attach() {
    }

    public Attach(String uuid, String uploadPath, String fileName, boolean fileType) {
        this.uuid = uuid;
        this.uploadPath = uploadPath;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public void insertBoard(Board b) {
        this.board = b;
    }
}
