package org.swyp.weddy.domain.checklist.web.response;

import org.swyp.weddy.domain.checklist.entity.Checklist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChecklistResponse {
    private final Long id;
    private final String memberId;
    private final Long dDay;

    public ChecklistResponse(Long id, String memberId, Long dDay) {
        this.id = id;
        this.memberId = memberId;
        this.dDay = dDay;
    }

    public static ChecklistResponse from(Checklist checklist) {
        LocalDate weddingDate = ChecklistResponse.weddingDate(checklist);
        return new ChecklistResponse(
                checklist.getId(),
                String.valueOf(checklist.getMemberId()),
                ChecklistResponse.daysBeforeWedding(weddingDate, LocalDate.now())
        );
    }

    public static LocalDate weddingDate(Checklist checklist) {
        LocalDateTime weddingDateTime = checklist.getdDay();
        return LocalDate.of(
                weddingDateTime.getYear(),
                weddingDateTime.getMonthValue(),
                weddingDateTime.getDayOfMonth()
        );
    }

    public static Long daysBeforeWedding(LocalDate weddingDate, LocalDate baseDate) {
        if (baseDate == null) {
            baseDate = LocalDate.now();
        }

        return ChronoUnit.DAYS.between(baseDate, weddingDate);
    }

    public Long getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public Long getdDay() {
        return dDay;
    }
}
