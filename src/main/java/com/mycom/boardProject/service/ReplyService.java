package com.mycom.boardProject.service;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.domain.Criteria;
import com.mycom.boardProject.domain.Reply;
import com.mycom.boardProject.dto.ReplyPageDTO;
import com.mycom.boardProject.repository.BoardRepository;
import com.mycom.boardProject.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void saveReply(Reply reply) {

        Board board = boardRepository.findOne(reply.getBoard().getBno());
        board.updateReplyCnt(1);
        boardRepository.save(board);

        replyRepository.save(reply);
    }

    public Reply findOne(Long rno) {
        return replyRepository.findOne(rno);
    }

    @Transactional
    public void removeReply(Long rno) {
        Reply reply = replyRepository.findOne(rno);
        Board board = boardRepository.findOne(reply.getBoard().getBno());
        board.updateReplyCnt(-1);
        replyRepository.delete(rno);
    }

    public ReplyPageDTO findAll(Long bno, Criteria cri) {

        if (cri.getPageNum() == -1) {
            int page = (int) (Math.ceil(replyRepository.getTotalReply(bno) / 10.0));

            cri.setPageNum(page);
        }
        return new ReplyPageDTO(replyRepository.findAll(bno, cri), replyRepository.getTotalReply(bno));
    }
}
