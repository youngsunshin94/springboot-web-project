package com.mycom.boardProject.service;

import com.mycom.boardProject.domain.Attach;
import com.mycom.boardProject.dto.AttachFileDTO;
import com.mycom.boardProject.domain.Board;
import com.mycom.boardProject.domain.Criteria;
import com.mycom.boardProject.dto.BoardDTO;
import com.mycom.boardProject.repository.AttachRepository;
import com.mycom.boardProject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final AttachRepository attachRepository;

    @Transactional
    public Long create(Board board) {
        Long result = boardRepository.save(board);

        if(board.getAttachList().size() == 0 || board.getAttachList() == null) {
            return result;
        }

        board.getAttachList().forEach(attach ->{
            Board findBoard = boardRepository.findOne(result);
            attach.insertBoard(findBoard);
            attachRepository.save(attach);
        });

        return result;
    }

    @Transactional
    public Board get(Long bno) {
        boardRepository.hitUp(bno);
        return boardRepository.findOne(bno);
    }

    @Transactional
    public void remove(Long bno) {
        attachRepository.deleteAll(bno);
        boardRepository.delete(bno);
    }

    public List<Board> getList(Criteria cri) {

        if(cri.getKeyword() == null || cri.getKeyword().length()  == 0) {
            return boardRepository.findAll(cri);
        } else {
            return boardRepository.findSearchAll(cri);
        }
    }

    @Transactional
    public void modify(BoardDTO boardDTO, Long bno) {

        Board board = boardRepository.findOne(bno);
        attachRepository.deleteAll(bno);


        if (board.getAttachList().size() != 0 || board.getAttachList() != null) {
            board.updateAttach(boardDTO.getAttachList());
            board.getAttachList().forEach(attach -> {
                attach.insertBoard(board);
                attachRepository.save(attach);
            });
        }

        board.updateBoard(boardDTO.getTitle(), boardDTO.getContent());
    }

    public Long getTotal(Criteria cri) {

        if(cri.getKeyword() == null || cri.getKeyword().length()  == 0) {
            return boardRepository.getTotalCount(cri);
        } else {
            return boardRepository.getSearchTotalCount(cri);
        }
    }

    public List<AttachFileDTO> getAttachList(Long bno) {

        List<Attach> all = attachRepository.findAll(bno);
        List<AttachFileDTO> attachList = new ArrayList<>();

        for (Attach attach : all) {
            AttachFileDTO attachFileDTO = new AttachFileDTO(attach.getUploadPath(), attach.getFileName(),
                    attach.getUuid(), attach.isFileType());
            attachList.add(attachFileDTO);
        }

        return attachList;
    }
}
