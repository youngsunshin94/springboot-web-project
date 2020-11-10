package com.mycom.boardProject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Persistent_logins {

    @Id @Column(length = 64)
    private String series;

    @Column(length = 100)
    private String userName;

    @Column(length = 64)
    private String token;

    private Timestamp last_used;
}
