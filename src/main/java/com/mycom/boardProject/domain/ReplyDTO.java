package com.mycom.boardProject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO {

    private String reply;
    private String replyer;
    private Long bno;

    public ReplyDTO(String reply, String replyer) {
        this.reply = reply;
        this.replyer = replyer;
    }
}
