package org.swyp.weddy.domain.checklist.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ChecklistDdayAssignRequest {
    private String memberId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @JsonProperty("dDay")
    private LocalDate dDay;

    public ChecklistDdayAssignRequest() {
    }

    public ChecklistDdayAssignRequest(String memberId, LocalDate dDay) {
        this.memberId = memberId;
        this.dDay = dDay;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDate getdDay() {
        return dDay;
    }
}
