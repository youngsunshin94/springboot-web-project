package com.mycom.boardProject.repository;

import com.mycom.boardProject.domain.Criteria;
import com.mycom.boardProject.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;

    public void save(Reply reply) {
        if (reply.getRno() == null) {
            em.persist(reply);
        } else {
            em.merge(reply);
        }
    }

    public Reply findOne(Long rno) {
        return em.find(Reply.class, rno);
    }

    public void delete(Long rno) {
        Reply replyFind = em.find(Reply.class, rno);
        em.remove(replyFind);
    }

    public List<Reply> findAll(Long bno, Criteria cri) {
        int skipCount = cri.getSkipCount();
        int amount = cri.getAmount();
        return em.createQuery("select r from Reply r where r.board.bno = :bno order by r.rno asc")
                .setParameter("bno", bno)
                .setFirstResult(skipCount)
                .setMaxResults(amount)
                .getResultList();
    }

    public Long getTotalReply(Long bno) {
        return em.createQuery("select count(r) from Reply r", Long.class)
                .getSingleResult();
    }
}
