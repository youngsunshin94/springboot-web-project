package com.mycom.boardProject.dto;

import com.mycom.boardProject.domain.Criteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDTO {

    private int startPage, endPage;
    private boolean prev, next;

    private Criteria cri;
    private Long total;

    public PageDTO(Criteria cri, Long total) {
        this.cri = cri;
        this.total = total;

        this.endPage = (int)(Math.ceil(cri.getPageNum() / 5.0)) * 5;
        this.startPage = this.endPage - 4;

        int realEnd = (int)(Math.ceil((total * 1.0)/ cri.getAmount()));

        if(this.endPage > realEnd) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage != 1;
        this.next = this.endPage < realEnd;
    }
}
