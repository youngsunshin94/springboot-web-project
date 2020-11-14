package com.mycom.boardProject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Criteria {

    private int pageNum;
    private int amount;

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public Criteria() {
        this(1,10);
    }

    public int getSkipCount() {
        return (pageNum - 1) * this.amount;
    }

}
