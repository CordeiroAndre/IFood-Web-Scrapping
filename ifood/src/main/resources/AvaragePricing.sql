SELECT r1.name as restaurant_name, avg(f1.original_price) AS avg_pricing
FROM restaurant AS r1
INNER JOIN food AS f1 ON f1.restaurant_id = r1.id
GROUP BY r1.name 
ORDER BY avg_pricing asc
