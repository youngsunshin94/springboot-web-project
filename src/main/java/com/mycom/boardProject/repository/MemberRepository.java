package com.mycom.boardProject.repository;

import com.mycom.boardProject.domain.MemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {

    private final EntityManager em;

    public MemberEntity save(MemberEntity memberEntity) {

        em.persist(memberEntity);
        return memberEntity;
    }

    public MemberEntity findByUserId(String userId) {
        return (MemberEntity) em.createQuery("select m from MemberEntity m where m.userId = :userId")
                .setParameter("userId", userId).getSingleResult();
    }
}
