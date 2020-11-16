package com.mycom.boardProject.repository;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.domain.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Long save(Board board) {

        if (board.getBno() == null) {
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

    public void hitUp(Long bno) {
        Board board = em.find(Board.class, bno);
        board.hitUp();
        em.merge(board);
    }

    public List<Board> findSearchAll(Criteria cri) {
        String jpql = "select b from Board b where";
        String keyword = cri.getKeyword();
        String type = cri.getType();
        int skipCount = cri.getSkipCount();
        int amount = cri.getAmount();

        if (type.equals("T")) {
            jpql += " b.title like concat('%',:keyword,'%')";
        } else if (type.equals("C")) {
            jpql += " b.content like concat('%',:keyword,'%')";
        } else {
            jpql += " b.writer like concat('%',:keyword,'%')";
        }

        jpql += " order by b.bno desc";

        TypedQuery<Board> query = em.createQuery(jpql, Board.class)
                .setFirstResult(skipCount)
                .setParameter("keyword", keyword)
                .setMaxResults(amount);

        return query.getResultList();
    }

    public Long getTotalCount(Criteria cri) {
        return em.createQuery("select count(b) from Board b", Long.class)
                .getSingleResult();
    }

    public Long getSearchTotalCount(Criteria cri) {
        String jpql = "select count(b) from Board b where";
        String keyword = cri.getKeyword();
        String type = cri.getType();

        int skipCount = cri.getSkipCount();
        int amount = cri.getAmount();

        if (type.equals("T")) {
            jpql += " b.title like concat('%',:keyword,'%')";
        } else if (type.equals("C")) {
            jpql += " b.content like concat('%',:keyword,'%')";
        } else {
            jpql += " b.writer like concat('%',:keyword,'%')";
        }

        Long result = em.createQuery(jpql, Long.class)
                .setParameter("keyword", keyword)
                .getSingleResult();

        return result;
    }
}
