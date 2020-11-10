package com.mycom.boardProject.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
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
}
