DELETE FROM food
WHERE id NOT IN (
    SELECT MIN(id)
    FROM food
    GROUP BY name, restaurant_id
);