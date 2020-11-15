package com.mycom.boardProject.service;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.domain.Criteria;
import com.mycom.boardProject.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
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

    public List<Board> getList(Criteria cri) {

        if(cri.getKeyword() == null || cri.getKeyword().length()  == 0) {
            return boardRepository.findAll(cri);
        } else {
            return boardRepository.findSearchAll(cri);
        }
    }

    @Transactional
    public void modify(Board board) {
        boardRepository.save(board);
    }

    public Long getTotal(Criteria cri) {

        if(cri.getKeyword() == null || cri.getKeyword().length()  == 0) {
            return boardRepository.getTotalCount(cri);
        } else {
            return boardRepository.getSearchTotalCount(cri);
        }
    }
}
