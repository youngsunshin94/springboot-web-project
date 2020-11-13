package com.mycom.boardProject.repository;

import com.mycom.boardProject.domain.Reply;
import com.mycom.boardProject.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    BoardService boardService;

    @Test
    @Rollback(value = false)
    public void 댓글등록() {

        for (int i = 0; i < 10; i++) {
            Reply reply = new Reply(boardService.get(1L),"댓글" + i,"user0");
            replyRepository.save(reply);
        }
    }

    @Test
    public void 수정하기() {
        Reply replyFind = replyRepository.findOne(1L);
        Reply reply = new Reply(replyFind.getBoard(), "댓글수정","user1");

        replyRepository.save(reply);
        
        replyFind = replyRepository.findOne(1L);
        System.out.println("reply.getReply() = " + reply.getReply());
        System.out.println("reply.getReplyer() = " + reply.getReplyer());
    }

    @Test
    public void 삭제하기() {
        replyRepository.delete(1L);
    }

}