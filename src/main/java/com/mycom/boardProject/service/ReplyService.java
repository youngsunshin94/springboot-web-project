package com.mycom.boardProject.service;

import com.mycom.boardProject.domain.Criteria;
import com.mycom.boardProject.domain.Reply;
import com.mycom.boardProject.domain.ReplyPageDTO;
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

    public ReplyPageDTO findAll(Long bno, Criteria cri) {

        if(cri.getPageNum() == -1) {
            int page = (int)(Math.ceil(replyRepository.getTotalReply(bno) / 10.0));

            cri.setPageNum(page);
        }
        return new ReplyPageDTO(replyRepository.findAll(bno, cri), replyRepository.getTotalReply(bno));
    }
}
