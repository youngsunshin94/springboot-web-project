package com.mycom.boardProject.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class BoardForm {

    private String title;
    private String content;
    private String writer;
    private String regDate;

    private int hit;
    private int replyCnt;

//    public static BoardForm createBoardForm(Board board) {
//
//        BoardForm boardForm = new BoardForm();
//        boardForm.title = board.getTitle();
//        boardForm.content = board.getContent();
//        boardForm.writer = board.getWriter();
//        boardForm.hit = board.getHit();
//        boardForm.replyCnt = board.getReplyCnt();
//        boardForm.regDate = board.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        return boardForm;
//    }
}
