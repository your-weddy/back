package org.swyp.weddy.domain.checklist.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.swyp.weddy.domain.checklist.service.dto.LargeCatItemDeleteDto;

import java.sql.Timestamp;

class LargeCatItemTest {

    @DisplayName("대분류 항목 삭제 시 updatedAt 필드값도 최신화한다")
    @Test
    public void mutate_updatedAt_when_delete_item() {

        LargeCatItem afterUpdate = new LargeCatItem(
                1L,
                "init",
                Timestamp.valueOf("1970-01-01 12:00:00"),
                Timestamp.valueOf("1970-01-01 12:00:00"),
                Boolean.FALSE
        );
        LargeCatItemDeleteDto deleteDto = new LargeCatItemDeleteDto(1L, 1L);
        LargeCatItem afterDelete = LargeCatItem.ofDelete(afterUpdate, deleteDto);
        Assertions.assertThat(afterDelete.getUpdatedAt()).isNotEqualTo(afterUpdate.getUpdatedAt());
    }
}
