SELECT f1.name, r1.name, count(*)
FROM food as f1
LEFT JOIN restaurant as r1 ON  f1.restaurant_id = r1.id
GROUP BY f1.name, r1.name
HAVING COUNT(*) > 1;
