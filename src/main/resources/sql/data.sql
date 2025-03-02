INSERT INTO `small_category_item_assignee` (`name`)
VALUES ('나');
INSERT INTO `small_category_item_assignee` (`name`)
VALUES ('짝궁');

INSERT INTO `small_category_item_status` (`name`)
VALUES ('시작전');
INSERT INTO `small_category_item_status` (`name`)
VALUES ('진행중');
INSERT INTO `small_category_item_status` (`name`)
VALUES ('완료');

-- 1. member 테이블에 테스트 데이터 삽입
INSERT INTO member (email, name, profile_image_url, oauth_id, is_deleted)
VALUES ('test@example.com', '테스트 사용자', NULL, 'oauth_1234', 0);

INSERT INTO member (email, name, profile_image_url, oauth_id, is_deleted)
VALUES ('test2@example.com', 'test2', NULL, 'oauth_1234', 0);

-- 2. checklist 테이블에 테스트 데이터 삽입
INSERT INTO checklist (member_id, d_day, created_at, updated_at, is_deleted)
VALUES (1, '2030-03-01 12:00:00', NOW(), NOW(), 0);

-- 3. large_category_item 및 small_category_item 데이터 삽입

-- "결혼 준비 시작"
INSERT INTO large_category_item (checklist_id, title, created_at, updated_at, is_deleted)
VALUES (1, '결혼 준비 시작', NOW(), NOW(), 0);
SET @large_id1 = LAST_INSERT_ID();

INSERT INTO small_category_item (large_category_item_id, title, created_at, updated_at, is_deleted)
VALUES
    (@large_id1, '결혼식 컨셉 나눔', NOW(), NOW(), 0),
    (@large_id1, '결혼 비용 논의', NOW(), NOW(), 0),
    (@large_id1, '양가 부모님 첫인사', NOW(), NOW(), 0),
    (@large_id1, '상견례 장소 예약', NOW(), NOW(), 0),
    (@large_id1, '상견례 선물 준비', NOW(), NOW(), 0),
    (@large_id1, '상견례 진행', NOW(), NOW(), 0);

-- "웨딩홀"
INSERT INTO large_category_item (checklist_id, title, created_at, updated_at, is_deleted)
VALUES (1, '웨딩홀', NOW(), NOW(), 0);
SET @large_id2 = LAST_INSERT_ID();

INSERT INTO small_category_item (large_category_item_id, title, created_at, updated_at, is_deleted)
VALUES
    (@large_id2, '예상 하객인원 계산', NOW(), NOW(), 0),
    (@large_id2, '웨딩홀 리스트업', NOW(), NOW(), 0),
    (@large_id2, '웨딩플래너 상담 및 계약 (결혼박람회 방문 및 상담)', NOW(), NOW(), 0),
    (@large_id2, '웨딩홀 투어 및 계약', NOW(), NOW(), 0);

-- "스드메"
INSERT INTO large_category_item (checklist_id, title, created_at, updated_at, is_deleted)
VALUES (1, '스드메', NOW(), NOW(), 0);
SET @large_id3 = LAST_INSERT_ID();

INSERT INTO small_category_item (large_category_item_id, title, created_at, updated_at, is_deleted)
VALUES
    (@large_id3, '스튜디오 / 스냅 예약', NOW(), NOW(), 0),
    (@large_id3, '드레스투어 일정 예약', NOW(), NOW(), 0),
    (@large_id3, '스튜디오 드레스 선택', NOW(), NOW(), 0),
    (@large_id3, '본식 드레스 선택', NOW(), NOW(), 0),
    (@large_id3, '2부 드레스 대여', NOW(), NOW(), 0),
    (@large_id3, '예복 투어 일정 예약', NOW(), NOW(), 0),
    (@large_id3, '스튜디오/본식 예복 선택', NOW(), NOW(), 0),
    (@large_id3, '헤어&메이크업 예약', NOW(), NOW(), 0),
    (@large_id3, '부케 업체 선정 및 예약', NOW(), NOW(), 0),
    (@large_id3, '스튜디오 /스냅 촬영', NOW(), NOW(), 0),
    (@large_id3, '사진 셀렉', NOW(), NOW(), 0);

-- "예물/예단"
INSERT INTO large_category_item (checklist_id, title, created_at, updated_at, is_deleted)
VALUES (1, '예물/예단', NOW(), NOW(), 0);
SET @large_id4 = LAST_INSERT_ID();

INSERT INTO small_category_item (large_category_item_id, title, created_at, updated_at, is_deleted)
VALUES
    (@large_id4, '웨딩 반지 구매', NOW(), NOW(), 0),
    (@large_id4, '예물 맞추기', NOW(), NOW(), 0);

-- "예복/한복"
INSERT INTO large_category_item (checklist_id, title, created_at, updated_at, is_deleted)
VALUES (1, '예복/한복', NOW(), NOW(), 0);
SET @large_id5 = LAST_INSERT_ID();

INSERT INTO small_category_item (large_category_item_id, title, created_at, updated_at, is_deleted)
VALUES
    (@large_id5, '혼주 한복 대여 or 구매', NOW(), NOW(), 0),
    (@large_id5, '혼주 양복 구매', NOW(), NOW(), 0),
    (@large_id5, '혼주 메이크업 업체 예약', NOW(), NOW(), 0);

-- "본식"
INSERT INTO large_category_item (checklist_id, title, created_at, updated_at, is_deleted)
VALUES (1, '본식', NOW(), NOW(), 0);
SET @large_id6 = LAST_INSERT_ID();

INSERT INTO small_category_item (large_category_item_id, title, created_at, updated_at, is_deleted)
VALUES
    (@large_id6, '하객 명단 최종 확인', NOW(), NOW(), 0),
    (@large_id6, '청첩장 제작', NOW(), NOW(), 0),
    (@large_id6, '모바일 청첩장 제작', NOW(), NOW(), 0),
    (@large_id6, '청첩장 모임', NOW(), NOW(), 0),
    (@large_id6, '아이폰 스냅 업체 예약', NOW(), NOW(), 0),
    (@large_id6, 'DVD 촬영 업체 예약', NOW(), NOW(), 0),
    (@large_id6, '사진 촬영 업체 예약', NOW(), NOW(), 0),
    (@large_id6, '식순 & BGM 선정', NOW(), NOW(), 0),
    (@large_id6, '식전 영상 제작', NOW(), NOW(), 0),
    (@large_id6, '주례/사회자 섭외', NOW(), NOW(), 0),
    (@large_id6, '축가/축사 섭외', NOW(), NOW(), 0),
    (@large_id6, '축의대 담당자 선정', NOW(), NOW(), 0),
    (@large_id6, '본식 헬퍼 섭외', NOW(), NOW(), 0),
    (@large_id6, '부케 업체 선정 및 예약', NOW(), NOW(), 0),
    (@large_id6, '답례품 선정', NOW(), NOW(), 0),
    (@large_id6, '당일 준비사항 점검', NOW(), NOW(), 0),
    (@large_id6, '결혼식 당일 준비물 챙기기', NOW(), NOW(), 0),
    (@large_id6, '축의금 정리', NOW(), NOW(), 0),
    (@large_id6, '하객 감사인사 전하기', NOW(), NOW(), 0),
    (@large_id6, '사회자 답례비 전달', NOW(), NOW(), 0),
    (@large_id6, '축가자 답례비 전달', NOW(), NOW(), 0);

-- "신혼여행"
INSERT INTO large_category_item (checklist_id, title, created_at, updated_at, is_deleted)
VALUES (1, '신혼여행', NOW(), NOW(), 0);
SET @large_id7 = LAST_INSERT_ID();

INSERT INTO small_category_item (large_category_item_id, title, created_at, updated_at, is_deleted)
VALUES
    (@large_id7, '신혼여행지 선정', NOW(), NOW(), 0),
    (@large_id7, '항공권 예매', NOW(), NOW(), 0),
    (@large_id7, '신혼여행 짐싸기', NOW(), NOW(), 0),
    (@large_id7, '여행 일정 계획', NOW(), NOW(), 0);

-- "신혼집"
INSERT INTO large_category_item (checklist_id, title, created_at, updated_at, is_deleted)
VALUES (1, '신혼집', NOW(), NOW(), 0);
SET @large_id8 = LAST_INSERT_ID();

INSERT INTO small_category_item (large_category_item_id, title, created_at, updated_at, is_deleted)
VALUES
    (@large_id8, '신혼집 계약', NOW(), NOW(), 0),
    (@large_id8, '가전/가구 구매', NOW(), NOW(), 0),
    (@large_id8, '침구/생활용품 구매', NOW(), NOW(), 0),
    (@large_id8, '주방용품 구매', NOW(), NOW(), 0),
    (@large_id8, '신혼집 입주', NOW(), NOW(), 0);

