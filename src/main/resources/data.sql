INSERT INTO region (region_id, region_name)
SELECT 1, '서울특별시' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 1);
INSERT INTO region (region_id, region_name)
SELECT 2, '부산광역시' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 2);
INSERT INTO region (region_id, region_name)
SELECT 3, '대구광역시' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 3);
INSERT INTO region (region_id, region_name)
SELECT 4, '인천광역시' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 4);
INSERT INTO region (region_id, region_name)
SELECT 5, '광주광역시' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 5);
INSERT INTO region (region_id, region_name)
SELECT 6, '대전광역시' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 6);
INSERT INTO region (region_id, region_name)
SELECT 7, '울산광역시' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 7);
INSERT INTO region (region_id, region_name)
SELECT 8, '세종특별자치시' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 8);
INSERT INTO region (region_id, region_name)
SELECT 9, '경기도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 9);
INSERT INTO region (region_id, region_name)
SELECT 10, '강원도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 10);
INSERT INTO region (region_id, region_name)
SELECT 11, '충청북도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 11);
INSERT INTO region (region_id, region_name)
SELECT 12, '충청남도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 12);
INSERT INTO region (region_id, region_name)
SELECT 13, '전라북도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 13);
INSERT INTO region (region_id, region_name)
SELECT 14, '전라남도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 14);
INSERT INTO region (region_id, region_name)
SELECT 15, '경상북도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 15);
INSERT INTO region (region_id, region_name)
SELECT 16, '경상남도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 16);
INSERT INTO region (region_id, region_name)
SELECT 17, '제주특별자치도' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM region WHERE region_id = 17);