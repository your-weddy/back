package org.swyp.weddy.domain.checklist.entity;

import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;

import java.sql.Timestamp;
import java.util.Date;

public class Checklist {
    private Integer id;
    private String memberId;
    private Date dDay;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isDeleted;

    public Checklist() {
    }

    public Checklist(Integer id, String memberId, Date dDay, Timestamp createdAt, Timestamp updatedAt, Boolean isDeleted) {
        this.id = id;
        this.memberId = memberId;
        this.dDay = dDay;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public Checklist(String memberId, Date dDay, Timestamp createdAt, Timestamp updatedAt, Boolean isDeleted) {
        this.memberId = memberId;
        this.dDay = dDay;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public static Checklist from(ChecklistDto dto) {
        return new Checklist(
                dto.getMemberId(),
                null,
                new Timestamp(System.currentTimeMillis()),
                null,
                Boolean.FALSE
        );
    }

    public Integer getId() {
        return id;
    }
}
