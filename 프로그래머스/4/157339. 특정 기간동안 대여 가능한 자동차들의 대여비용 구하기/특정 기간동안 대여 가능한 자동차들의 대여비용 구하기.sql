SELECT *
FROM   (
        SELECT C.CAR_ID,
               C.CAR_TYPE,
               (DAILY_FEE * 30 * (100 - DISCOUNT_RATE) / 100) AS FEE
        FROM   CAR_RENTAL_COMPANY_CAR C
               JOIN (
                        SELECT CAR_TYPE,
                               DISCOUNT_RATE
                        FROM   CAR_RENTAL_COMPANY_DISCOUNT_PLAN
                        WHERE  DURATION_TYPE = '30일 이상'
                    ) P
               ON C.CAR_TYPE = P.CAR_TYPE
        WHERE  C.CAR_TYPE IN ('세단', 'SUV')
               AND CAR_ID NOT IN (
                    SELECT CAR_ID
                    FROM   CAR_RENTAL_COMPANY_RENTAL_HISTORY
                    WHERE  NOT (START_DATE >= DATE '2022-12-01'
                               OR END_DATE < DATE '2022-11-01')
               )
       )
WHERE  FEE >= 500000
       AND FEE <  2000000
ORDER  BY CAR_ID