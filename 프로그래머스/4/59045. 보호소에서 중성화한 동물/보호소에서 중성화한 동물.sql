SELECT I.ANIMAL_ID,
       I.ANIMAL_TYPE,
       I.NAME
FROM   ANIMAL_INS I
       JOIN ANIMAL_OUTS O 
         ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE  sex_upon_intake != sex_upon_outcome
ORDER  BY I.ANIMAL_ID