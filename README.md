# java-filmorate
Template repository for Filmorate project.

![ВB scheme](https://github.com/RinOrlova/filmorate/blob/db_draft/db.png)

**SQL request examples:**

**Film**

Get all films:
<pre>
```sql
SELECT * 
FROM film;
```
</pre>

Get film by id:
<pre>
```sql
SELECT *
FROM film
WHERE id = 23;
```
</pre>

Get top-10 films:
<pre>
```sql
SELECT film.id AS film_id, film.name AS film_name, 
COUNT(film_x_user.user_id) AS views_count
FROM film
LEFT JOIN film_x_user ON film.id = film_x_user.film_id
GROUP BY film.id, film.name
ORDER BY views_count DESC
LIMIT 10;
```
</pre>

**User**

Get all users:
<pre>
```sql
SELECT * 
FROM app_user;
```
</pre>

Get user by id:
<pre>
```sql
SELECT *
FROM app_user
WHERE id = 17;
```
</pre>

Get friends:
<pre>
```sql
SELECT 
FROM ;
```
</pre>

Get common friends:
<pre>
```sql
SELECT 
FROM ;
```
</pre>
