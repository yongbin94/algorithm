SELECT CATEGORY,
       SUM(SALES) AS TOTAL_SALES
FROM   BOOK B
       JOIN (
            SELECT BOOK_ID,
                   SUM(SALES) AS SALES
            FROM   BOOK_SALES
            WHERE SALES_DATE >= DATE '2022-01-01'
                   AND SALES_DATE < DATE '2022-02-01'
            GROUP  BY BOOK_ID
            ) S
       ON B.BOOK_ID = S.BOOK_ID
GROUP  BY CATEGORY
ORDER  BY CATEGORY