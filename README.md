[Схема диаграммы](Schema.png)

### Java-filmorate - это приложение, которое умеет обрабатывать и хранить данные о пользователях и их любимых фильмах.

### Выбрать что-нибудь для просмотра за ужином больше не составит труда.

# Примеры запросов:

## Для пользователей:

### INSERT INTO users (email,login,name,birthday)

VALUES (?, ?, ?, ?)

### UPDATE users

SET email = ?,
login = ?,
name = ?,
birthday = ?
WHERE user_id = ?

### SELECT *

FROM users
WHERE user_id = ?SELECT *
FROM users
WHERE user_id = ?

## Для фильмов:

### INSERT INTO films (name,description,release_date,duration_in_minutes,mpa_rating_id)

VALUES (?, ?, ?, ?, ?);

### UPDATE

films
SET name = ?,
description = ?,
release_date = ?,
duration_in_minutes = ?,
mpa_rating_id = ?
WHERE film_id = ?;