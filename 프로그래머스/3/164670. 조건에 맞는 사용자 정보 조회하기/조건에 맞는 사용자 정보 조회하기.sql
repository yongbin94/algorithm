SELECT USER_ID,
       NICKNAME,
       CITY || ' ' || STREET_ADDRESS1 
       || NVL2(STREET_ADDRESS2, ' ' || STREET_ADDRESS2, '') AS 전체주소,
       SUBSTR(TLNO, 1, 3)
       || '-' || SUBSTR(TLNO, 4, 4)
       || '-' || SUBSTR(TLNO, 8, 4) AS 전화번호
FROM   USED_GOODS_USER
       JOIN (
             SELECT WRITER_ID
             FROM   USED_GOODS_BOARD
             GROUP  BY WRITER_ID
             HAVING COUNT(*) >= 3
       ) ON USER_ID = WRITER_ID
ORDER  BY USER_ID DESC