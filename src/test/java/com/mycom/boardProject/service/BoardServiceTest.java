package com.mycom.boardProject.service;

import com.mycom.boardProject.domain.Criteria;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class BoardServiceTest {

    Logger logger = (Logger) LoggerFactory.getLogger(BoardServiceTest.class);

    @Autowired
    BoardService boardService;

    @Test
    public void 검색처리() {
        Criteria cri = new Criteria();
        cri.setKeyword("제목");
        cri.setType("T");

        boardService.getList(cri).forEach(board -> logger.info(String.valueOf(board.toString())));

        logger.info("total : " + String.valueOf(boardService.getTotal(cri)));
    }

}