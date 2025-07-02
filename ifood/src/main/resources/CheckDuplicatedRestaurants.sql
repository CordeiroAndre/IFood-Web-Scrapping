SELECT r1.name, count(r1.name)
FROM public.restaurant AS r1, public.restaurant AS r2
WHERE r1.name = r2.name AND r1.id <> r2.id
GROUP BY r1.name;

