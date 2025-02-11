package org.swyp.weddy.domain.checklist.entity;

import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;

import java.sql.Timestamp;

public class Checklist {
    private Long id;
    private Long memberId;
    private Integer dDay;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isDeleted;

    public Checklist() {
    }

    public Checklist(Long id, Long memberId, Integer dDay, Timestamp createdAt, Timestamp updatedAt, Boolean isDeleted) {
        this.id = id;
        this.memberId = memberId;
        this.dDay = dDay;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public Checklist(Long memberId, Integer dDay, Timestamp createdAt, Timestamp updatedAt, Boolean isDeleted) {
        this.memberId = memberId;
        this.dDay = dDay;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public static Checklist from(ChecklistDto dto) {
        return new Checklist(
                Long.valueOf(dto.getMemberId()),
                null,
                new Timestamp(System.currentTimeMillis()),
                null,
                Boolean.FALSE
        );
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Integer getdDay() {
        return dDay;
    }
}
