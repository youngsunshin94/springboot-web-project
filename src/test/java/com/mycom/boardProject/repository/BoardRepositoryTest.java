package com.mycom.boardProject.repository;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.domain.Criteria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void 게시글작성() {
        Board board = new Board("게시글","내용","user0");
        Long saveId = boardRepository.save(board);

        Assertions.assertThat(saveId).isEqualTo(board.getBno());
    }

    @Test
    public void 게시글조회() {
        Board board = new Board("게시글","내용","user0");
        Long saveId = boardRepository.save(board);
        Board findBoard = boardRepository.findOne(saveId);

        Assertions.assertThat(board).isEqualTo(findBoard);
    }

    @Test
    public void 게시글수정() {
        Board board = new Board("게시글","내용","user0");
        Long saveId = boardRepository.save(board);
        Board findBoard = boardRepository.findOne(saveId);

        findBoard = new Board("게시물1", findBoard.getContent(), findBoard.getWriter());
        boardRepository.save(findBoard);

        Assertions.assertThat(board.getTitle()).isNotEqualTo(findBoard.getTitle());
    }

//    @Test
//    public void 게시글삭제() {
//        Board board = new Board("게시글","내용","user0");
//        Long saveId = boardRepository.save(board);
//
//        Long deleteId = boardRepository.delete(saveId);
//
//        Assertions.assertThat(saveId).isEqualTo(deleteId);
//    }

}