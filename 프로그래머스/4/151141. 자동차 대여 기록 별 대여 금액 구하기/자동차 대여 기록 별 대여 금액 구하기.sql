SELECT HISTORY_ID,
       DAILY_FEE * (100 - NVL(DISCOUNT_RATE, 0)) / 100 * DAYS AS FEE
FROM   (
        SELECT HISTORY_ID,
               CAR_ID,
               END_DATE - START_DATE + 1 AS DAYS,
               CASE
                    WHEN (END_DATE - START_DATE + 1) >= 90 THEN '90일 이상'
                    WHEN (END_DATE - START_DATE + 1) >= 30 THEN '30일 이상'
                    WHEN (END_DATE - START_DATE + 1) >= 7  THEN '7일 이상'
               END AS DURATION_TYPE
        FROM   CAR_RENTAL_COMPANY_RENTAL_HISTORY
       ) H
       JOIN CAR_RENTAL_COMPANY_CAR C
         ON H.CAR_ID = C.CAR_ID
       LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P
         ON H.DURATION_TYPE = P.DURATION_TYPE
            AND C.CAR_TYPE  = P.CAR_TYPE
WHERE  C.CAR_TYPE = '트럭'
ORDER  BY FEE DESC,
          HISTORY_ID DESC