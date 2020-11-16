package com.mycom.boardProject.service;

import com.mycom.boardProject.domain.Reply;
import com.mycom.boardProject.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional
    public void saveReply(Reply reply) {
        replyRepository.save(reply);
    }

    public Reply findOne(Long rno) {
        return replyRepository.findOne(rno);
    }

    @Transactional
    public void removeReply(Long rno) {
        replyRepository.delete(rno);
    }

    public List<Reply> findAll(Long bno) {
        return replyRepository.findAll(bno);
    }
}
