package com.mycom.boardProject.dto;

import com.mycom.boardProject.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class ReplyPageDTO {

    private List<Reply> list;
    private Long replyCnt;
}
