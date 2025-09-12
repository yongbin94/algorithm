SELECT *
FROM   (
        SELECT CART_ID
        FROM   CART_PRODUCTS
        WHERE  NAME = 'Milk'
       ) JOIN (
        SELECT CART_ID
        FROM   CART_PRODUCTS
        WHERE  NAME = 'Yogurt'
       ) USING(CART_ID)
ORDER  BY CART_ID