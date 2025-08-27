SELECT TITLE,
       BOARD_ID,
       REPLY_ID,
       r.WRITER_ID,
       r.CONTENTS,
       TO_CHAR(r.CREATED_DATE, 'YYYY-MM-DD') AS CREATED_DATE
FROM   used_goods_board b
       JOIN used_goods_reply r USING (board_id)
WHERE  b.created_date >= DATE '2022-10-01'
       AND b.created_date < ADD_MONTHS(DATE '2022-10-01', 1)
ORDER  BY r.created_date,
          b.TITLE