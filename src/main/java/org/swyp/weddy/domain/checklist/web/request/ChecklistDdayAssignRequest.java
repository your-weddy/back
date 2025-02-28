package org.swyp.weddy.domain.checklist.web.request;

import java.time.LocalDate;

public class ChecklistDdayAssignRequest {
    private LocalDate dDay;

    public ChecklistDdayAssignRequest() {
    }

    public ChecklistDdayAssignRequest(LocalDate dDay) {
        this.dDay = dDay;
    }

    public LocalDate getdDay() {
        return dDay;
    }
}
