package com.mycom.boardProject.controller;

import com.mycom.boardProject.domain.*;
import com.mycom.boardProject.dto.AttachFileDTO;
import com.mycom.boardProject.dto.BoardDTO;
import com.mycom.boardProject.dto.PageDTO;
import com.mycom.boardProject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String getList(Model model, Criteria cri) {
        log.info("list");

        Long total = boardService.getTotal(cri);
        List<Board> list = boardService.getList(cri);
        List<BoardDTO> listDto = list.stream()
                .map(board -> new BoardDTO(board))
                .collect(Collectors.toList());

        model.addAttribute("list", listDto);
        PageDTO pageDTO = new PageDTO(cri, total);
        model.addAttribute("pageMaker", pageDTO);

        return "/board/list";
    }

    @GetMapping("/board/register")
    public String create(Model model) {
        log.info("register");
        model.addAttribute("boardForm", new BoardDTO());
        return "/board/register";
    }

    @PostMapping("/board/register")
    public String create(BoardDTO boardDTO, RedirectAttributes rttr) {

        Board board = new Board(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getWriter());
        if(boardDTO.getAttachList().size() > 0 && boardDTO.getAttachList() != null) {
            log.info("/board/register -> boardForm");
            board.updateAttach(boardDTO.getAttachList());

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
        Board board = boardService.get(bno);
        log.info("board title = " + board.getTitle());
        BoardDTO boardDTO = BoardDTO.goToGet(board);
        log.info("title = " + boardDTO.getTitle());
        model.addAttribute("board", boardDTO);

        return "/board/get";
    }


    @GetMapping("/board/modify")
    public String modify(Model model, @RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri) {
        Board board = boardService.get(bno);
        BoardDTO boardDTO = BoardDTO.goToGet(board);

        model.addAttribute("board", boardDTO);
        return "/board/modify";
    }

    @PreAuthorize("authentication.principal.username == #boardDTO.writer")
    @PostMapping("/board/modify")
    public String modify(BoardDTO boardDTO, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {

//        Board board = new Board(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getWriter());
//        board.updateBnoSetting(boardDTO.getBno());

//        if(boardDTO.getAttachList().size() > 0 && boardDTO.getAttachList() != null) {
//            board.updateAttach(boardDTO.getAttachList());
//        }
        boardService.modify(boardDTO, boardDTO.getBno());

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
