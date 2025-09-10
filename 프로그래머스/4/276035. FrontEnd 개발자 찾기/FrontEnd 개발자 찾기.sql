SELECT ID,
       EMAIL,
       FIRST_NAME,
       LAST_NAME
FROM   DEVELOPERS
WHERE  SKILL_CODE & (
            SELECT SUM(CODE)
            FROM   SKILLCODES
            GROUP  BY CATEGORY
            HAVING CATEGORY = 'Front End'
       ) != 0
ORDER  BY ID