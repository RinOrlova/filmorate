# java-filmorate
Template repository for Filmorate project.

![ВB scheme](https://github.com/RinOrlova/filmorate/blob/db_draft/film_db.png)

**SQL request examples:**

**Film**

Get all films:

```SQL
SELECT * 
FROM film;
```


Get film by id:

```sql
SELECT *
FROM film
WHERE id = 23;
```

Get top-10 films:
```sql
SELECT film.id AS film_id, film.name AS film_name, 
COUNT(film_x_user.user_id) AS views_count
FROM film
LEFT JOIN film_x_user ON film.id = film_x_user.film_id
GROUP BY film.id, film.name
ORDER BY views_count DESC
LIMIT 10;
```

**User**

Get all users:

```sql
SELECT * 
FROM app_user;
```

Get user by id:

```sql
SELECT *
FROM app_user
WHERE id = 17;
```


Get friends:

```sql
 SELECT DISTINCT u2.id, u2.name
    FROM "app_user" u1
    JOIN "user_x_connection" uc1 ON u1.id = uc1.user_id
    JOIN "connection" c ON uc1.connection_id = c.id AND c.status = TRUE
    JOIN "user_x_connection" uc2 ON c.id = uc2.connection_id AND uc1.user_id <> uc2.user_id
    JOIN "app_user" u2 ON uc2.user_id = u2.id
    WHERE u1.id = 1;
```


