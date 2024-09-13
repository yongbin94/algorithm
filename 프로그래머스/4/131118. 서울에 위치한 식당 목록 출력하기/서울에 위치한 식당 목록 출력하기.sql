SELECT
    *
FROM (
    SELECT
        REST_ID,
        REST_NAME,
        FOOD_TYPE,
        FAVORITES,
        ADDRESS,
        (
            SELECT 
                ROUND(AVG(R.REVIEW_SCORE), 2)
            FROM
                REST_REVIEW AS R
            GROUP BY
                R.REST_ID
            HAVING
                R.REST_ID = I.REST_ID
        ) AS SCORE
    FROM
        REST_INFO AS I
    WHERE
        SUBSTRING(I.ADDRESS, 1, 2) = '서울'
    ORDER BY
        SCORE DESC,
        FAVORITES DESC
) AS REST
WHERE
    SCORE IS NOT NULL
