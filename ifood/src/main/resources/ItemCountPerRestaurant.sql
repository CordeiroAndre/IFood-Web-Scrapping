SELECT r1
FROM restaurant AS r1 
LEFT JOIN food AS f1
ON r1.id = f1.restaurant_id
GROUP BY r1
HAVING count(f1.name) = 0