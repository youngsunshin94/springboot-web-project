package com.mycom.boardProject.repository;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.domain.Criteria;
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
            board.modifyDate();
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

    public List<Board> findAll(Criteria cri) {
        int skipCount = cri.getSkipCount();
        int amount = cri.getAmount();
        return em.createQuery("select b from Board b order by b.bno desc", Board.class)
                .setFirstResult(skipCount)
                .setMaxResults(amount)
                .getResultList();
    }

    public Long getTotalCount(Criteria cri) {
        return em.createQuery("select count(b) from Board b", Long.class)
                .getSingleResult();
    }
}
