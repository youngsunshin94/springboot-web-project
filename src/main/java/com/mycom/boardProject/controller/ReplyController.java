package com.mycom.boardProject.controller;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.domain.Reply;
import com.mycom.boardProject.domain.ReplyDTO;
import com.mycom.boardProject.service.BoardService;
import com.mycom.boardProject.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyService replyService;
    private final BoardService boardService;

    @PostMapping(value = "/new", consumes = "application/json",
            produces = "application/text; charset=UTF-8")
    public ResponseEntity<String> create(@RequestBody ReplyDTO replyDTO) {
        log.info("ReplyDTO : " + replyDTO);
        Board board = boardService.get(replyDTO.getBno());
        Reply reply = new Reply(board, replyDTO.getReply(), replyDTO.getReplyer());
        replyService.saveReply(reply);

        return new ResponseEntity<>("댓글이 등록되었습니다.", HttpStatus.OK);
    }

    @GetMapping(value = "/pages/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reply>> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page) {


        return new ResponseEntity<>(replyService.findAll(bno), HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Reply> get(@PathVariable("rno") Long rno) {
        Reply reply = replyService.findOne(rno);

        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @PutMapping(value = "/{rno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
        Reply reply = replyService.findOne(rno);

        reply.updateReply(replyDTO.getReply());
        replyService.saveReply(reply);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        replyService.removeReply(rno);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }
}
