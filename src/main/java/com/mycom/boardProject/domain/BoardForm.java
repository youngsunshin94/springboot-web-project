package com.mycom.boardProject.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardForm {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private String regDate;

    private int hit;
    private int replyCnt;

    private List<AttachFileDTO> attachList = new ArrayList<>();

}
