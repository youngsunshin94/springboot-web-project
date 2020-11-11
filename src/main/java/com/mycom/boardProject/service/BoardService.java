package com.mycom.boardProject.service;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardService {

    public final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long create(Board board) {
        return boardRepository.save(board);
    }

    public Board get(Long bno) {
        return boardRepository.findOne(bno);
    }

    @Transactional
    public Long remove(Long bno) {
        return boardRepository.delete(bno);
    }

    public List<Board> getList() {
        return boardRepository.findAll();
    }

    @Transactional
    public void modify(Board board) {
        boardRepository.save(board);
    }
}
