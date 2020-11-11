package com.mycom.boardProject.controller;

import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.domain.BoardForm;
import com.mycom.boardProject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String getList(Model model) {

        model.addAttribute("list", boardService.getList());
        log.info("list");
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

        Long boardId = boardService.create(board);
        rttr.addFlashAttribute("bno", boardId);

        return "redirect:/board/list";
    }

    @GetMapping("/board/get")
    public String get(Model model, @RequestParam("bno") Long bno) {
        model.addAttribute("board", boardService.get(bno));

        return "/board/get";
    }

    @GetMapping("/board/modify")
    public String modify(Model model, @RequestParam("bno") Long bno) {
        model.addAttribute("board", boardService.get(bno));

        return "/board/modify";
    }

    @PostMapping("/board/modify")
    public String modify(Board board, RedirectAttributes rttr) {
        boardService.modify(board);
        rttr.addFlashAttribute("result", "success");

        return "redirect:/board/list";
    }

    @PostMapping("/board/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        Long removeBno = boardService.remove(bno);
        rttr.addFlashAttribute("bno", removeBno);

        return "redirect:/board/list";
    }
}
