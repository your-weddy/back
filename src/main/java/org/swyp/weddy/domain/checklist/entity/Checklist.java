package org.swyp.weddy.domain.checklist.entity;

import org.swyp.weddy.domain.checklist.service.dto.ChecklistDdayAssignDto;
import org.swyp.weddy.domain.checklist.service.dto.ChecklistDto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Checklist {
    private Long id;
    private Long memberId;
    private LocalDateTime dDay;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isDeleted;

    public Checklist() {
    }

    public Checklist(Long id, Long memberId, LocalDateTime dDay, Timestamp createdAt, Timestamp updatedAt, Boolean isDeleted) {
        this.id = id;
        this.memberId = memberId;
        this.dDay = dDay;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public Checklist(Long memberId, LocalDateTime dDay, Timestamp createdAt, Timestamp updatedAt, Boolean isDeleted) {
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

    public static Checklist withNewDday(Checklist checklist, ChecklistDdayAssignDto dto) {
        return new Checklist(
                checklist.getId(),
                Long.valueOf(dto.getMemberId()),
                convertDday(dto.getdDay()),
                checklist.getCreatedAt(),
                new Timestamp(System.currentTimeMillis()),
                Boolean.FALSE
        );
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public LocalDateTime getdDay() {
        return dDay;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    static LocalDateTime convertDday(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.NOON);
    }
}
