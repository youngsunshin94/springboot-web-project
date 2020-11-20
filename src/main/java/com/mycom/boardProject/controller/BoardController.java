package com.mycom.boardProject.controller;

import com.mycom.boardProject.domain.*;
import com.mycom.boardProject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String getList(Model model, Criteria cri) {
        log.info("list");

        Long total = boardService.getTotal(cri);
        model.addAttribute("list", boardService.getList(cri));
        PageDTO pageDTO = new PageDTO(cri, total);
        model.addAttribute("pageMaker", pageDTO);

        return "/board/list";
    }

    @GetMapping("/board/register")
    public String create(Model model) {
        log.info("register");
        model.addAttribute("boardForm", new BoardForm());
        return "/board/register";
    }

    @PostMapping("/board/register")
    public String create(BoardForm boardForm, RedirectAttributes rttr) {

        Board board = new Board(boardForm.getTitle(), boardForm.getContent(), boardForm.getWriter());
        if(boardForm.getAttachList().size() > 0 && boardForm.getAttachList() != null) {
            log.info("/board/register -> boardForm");
            board.updateAttach(boardForm.getAttachList());

            board.getAttachList().forEach(attach -> {
                log.info("attach : " + attach.getFileName());
            });
        }
        Long boardId = boardService.create(board);
        rttr.addFlashAttribute("bno", boardId);

        return "redirect:/board/list";
    }

    @GetMapping("/board/get")
    public String get(Model model, @RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri) {
        model.addAttribute("board", boardService.get(bno));

        return "/board/get";
    }

    @GetMapping("/board/modify")
    public String modify(Model model, @RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri) {
        model.addAttribute("board", boardService.get(bno));

        return "/board/modify";
    }

    @PostMapping("/board/modify")
    public String modify(BoardForm boardForm, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {

        Board board = new Board(boardForm.getTitle(), boardForm.getContent(), boardForm.getWriter());
        board.updateBnoSetting(boardForm.getBno());

        if(boardForm.getAttachList().size() > 0 && boardForm.getAttachList() != null) {
            board.updateAttach(boardForm.getAttachList());
        }
        boardService.modify(board);

        rttr.addFlashAttribute("result", "success");

        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }

    @PostMapping("/board/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
        boardService.remove(bno);

        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }

    @GetMapping(value = "/board/getAttachList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity<List<AttachFileDTO>> getAttachList(Long bno) {
        return new ResponseEntity<>(boardService.getAttachList(bno), HttpStatus.OK);
    }
}
