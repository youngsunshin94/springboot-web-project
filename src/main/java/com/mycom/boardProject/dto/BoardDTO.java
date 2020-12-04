package com.mycom.boardProject.dto;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.dto.AttachFileDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;

    private int hit;
    private int replyCnt;

    private List<AttachFileDTO> attachList = new ArrayList<>();

    public BoardDTO(Board board) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.regDate = board.getRegDate();
        this.hit = board.getHit();
        this.replyCnt = board.getReplyCnt();
    }

    public static BoardDTO goToGet(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBno(board.getBno());
        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        boardDTO.setWriter(board.getWriter());

        return boardDTO;
    }

}
