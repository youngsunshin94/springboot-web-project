package com.mycom.boardProject.repository;

import com.mycom.boardProject.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Long save(Board board) {

        if(board.getBno() == null) {
            em.persist(board);
        } else {
            em.merge(board);
        }
        return board.getBno();
    }

    public Board findOne(Long bno) {
        return em.find(Board.class, bno);
    }

    public Long delete(Long bno) {
        Board board = em.find(Board.class, bno);
        em.remove(board);

        return bno;
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }
}
