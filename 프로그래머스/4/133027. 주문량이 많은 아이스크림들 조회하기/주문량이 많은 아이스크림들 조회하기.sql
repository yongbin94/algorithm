SELECT
    FLAVOR
FROM
    FIRST_HALF F
ORDER BY
    F.TOTAL_ORDER + (
        SELECT
            SUM(TOTAL_ORDER)
        FROM
            JULY J
        GROUP BY FLAVOR
        HAVING 
            J.FLAVOR = F.FLAVOR
    ) DESC
LIMIT 3;