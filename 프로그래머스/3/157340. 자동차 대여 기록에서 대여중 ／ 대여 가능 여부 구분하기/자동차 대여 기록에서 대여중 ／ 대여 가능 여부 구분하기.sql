SELECT DISTINCT A.CAR_ID,
                CASE
                    WHEN B.CAR_ID IS NULL THEN '대여 가능'
                    ELSE '대여중'
                END AS AVAILABILITY
FROM   (
            SELECT DISTINCT CAR_ID
            FROM   CAR_RENTAL_COMPANY_RENTAL_HISTORY
       ) A
       LEFT JOIN (
            SELECT CAR_ID
            FROM   CAR_RENTAL_COMPANY_RENTAL_HISTORY
            WHERE  START_DATE <= DATE '2022-10-16'
                   AND END_DATE >= DATE '2022-10-16'
       ) B
       ON A.CAR_ID = B.CAR_ID 
ORDER  BY CAR_ID DESC