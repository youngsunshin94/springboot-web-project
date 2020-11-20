package com.mycom.boardProject.repository;

import com.mycom.boardProject.domain.Attach;
import com.mycom.boardProject.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class AttachRepository {

    private final EntityManager em;

    public void save(Attach attach) {
        em.persist(attach);
    }

    public List<Attach> findAll(Long bno) {
        return em.createQuery("select a from Attach a where a.board.bno = :bno")
                .setParameter("bno", bno)
                .getResultList();
    }

    public void deleteAll(Long bno) {
        log.info("bno : " + bno);
        em.createQuery("delete from Attach a where a.board.bno = :bno")
                .setParameter("bno", bno)
                .executeUpdate();
    }

}
