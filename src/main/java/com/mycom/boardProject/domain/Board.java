package com.mycom.boardProject.domain;

import com.mycom.boardProject.dto.AttachFileDTO;
import com.mycom.boardProject.dto.BoardDTO;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 50)
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @Column(columnDefinition = "integer default 0")
    private int hit;

    @Column(columnDefinition = "integer default 0")
    private int replyCnt;

    @OneToMany(mappedBy = "board")
    private List<Attach> attachList = new ArrayList<>();

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

    public void updateBnoSetting(Long bno) {
        this.bno = bno;
    }



    public void modifyDate() {
        this.updateDate = LocalDateTime.now();
    }

    public void hitUp() {
        this.hit += 1;
    }

    public void updateReplyCnt(int amount) {
        this.replyCnt += amount;
    }

    public void updateAttach(List<AttachFileDTO> list) {

        for (AttachFileDTO attachFileDTO : list) {
            Attach attach = new Attach(attachFileDTO.getUuid(), attachFileDTO.getUploadPath(),
                    attachFileDTO.getFileName(), attachFileDTO.isImage());

            this.attachList.add(attach);
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", hit=" + hit +
                ", replyCnt=" + replyCnt +
                ", attachList=" + attachList +
                '}';
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }
}
