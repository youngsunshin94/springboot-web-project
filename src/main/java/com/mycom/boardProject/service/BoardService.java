package com.mycom.boardProject.service;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    public final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Long create(Board board) {
        return boardRepository.save(board);
    }

    public Board get(Long bno) {
        return boardRepository.findOne(bno);
    }

    public Long remove(Long bno) {
        return boardRepository.delete(bno);
    }

    public List<Board> getList() {
        return boardRepository.findAll();
    }

}
