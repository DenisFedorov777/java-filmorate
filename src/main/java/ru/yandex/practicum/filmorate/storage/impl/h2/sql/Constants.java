package ru.yandex.practicum.filmorate.storage.impl.h2.sql;

public class Constants {

  /** SQL для MPA репозитория. */
  public static final String SELECT_MPA_ALL = "SELECT * FROM MPA";

  public static final String SELECT_MPA_BY_FILM_ID =
      "SELECT m.id, m.name FROM mpa m INNER JOIN films f ON f.mpa_id = m.id WHERE f.id = ?";

  public static final String SELECT_MPA_BY_ID = "SELECT * FROM MPA WHERE ID = ?";

  /** SQL для Genre репозитория */
  public static final String SELECT_GENRE_ALL = "SELECT * FROM GENRES";

  public static final String SELECT_GENRE_BY_ID = "SELECT * FROM GENRES WHERE ID = ?";

  /** SQL для USER репозитория. */
  public static final String SELECT_USER_ALL = "SELECT * FROM users";

  public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

  public static final String INSERT_USER =
      "INSERT INTO users (email, login, name, birthday) VALUES ( ?, ?, ?, ? )";

  public static final String UPDATE_USER_BY_ID =
      "UPDATE users SET email = ?, login = ?, name = ?, birthday = ? WHERE id = ?";

  public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

  /** SQL для Like репозитория. */
  public static final String INSERT_LIKE_BY_FILM_ID_AND_USER_ID =
      "INSERT INTO likes ( film_id, user_id ) VALUES ( ?, ? )";

  public static final String DELETE_LIKE_BY_FILM_ID_AND_USER_ID =
      "DELETE FROM likes WHERE film_id =? AND user_id =?";

  public static final String SELECT_LIKE_COUNT_BY_FILM_ID =
      "SELECT count(user_id) FROM likes WHERE film_id = ?";

  public static final String SELECT_FILMS_USERS_BY_FILM_ID =
      "SELECT l.user_id FROM films f JOIN FILMS_USERS l ON f.id = l.film_id WHERE f.id = ?";

  /** SQL для FILM репозитория */
  public static final String SELECT_ALL_FILMS = "SELECT * FROM films";

  public static final String SELECT_FILMS_BY_FILM_ID =
      "SELECT f.id, f.name, f.description, f.release_date, f.duration, f.mpa_id FROM films f WHERE ID = ?";

  public static final String DELETE_FILM_BY_ID = "DELETE FROM film WHERE id = ?";

  public static final String INSERT_FILM =
      "INSERT INTO films (name, description, release_date, duration, mpa_id) VALUES ( ?, ?, ?, ?, ?)";
  public static final String UPDATE_FILM_BY_ID =
      "UPDATE films SET name = ?, description = ?, release_date = ?, duration = ?, mpa_id = ? WHERE id = ?";

  /** SQL для FILM_GENRE репозитория */
  public static final String SELECT_GENRES_BY_FILM_ID =
      "SELECT g.id, g.name FROM genres g INNER JOIN films_genres fg ON g.id = fg.genre_id WHERE fg.film_id = ?";

  public static final String DELETE_FILMS_GENRES_BY_FILM_ID =
      "DELETE FROM films_genres WHERE film_id = ?";
  public static final String INSERT_FILMS_GENRES =
      "INSERT INTO films_genres (film_id, genre_id) VALUES (?, ?)";

  /** SQL для FRIENDSHIPS репозитория */
  public static final String SELECT_FRIENDSHIPS_COMMON_FRIENDS =
      "SELECT * FROM users us "
          + "JOIN friendships AS fr1 ON us.id = fr1.friend_id "
          + "JOIN friendships AS fr2 ON us.id = fr2.friend_id "
          + "WHERE fr1.user_id = ? AND fr2.user_id = ?";

  public static final String SELECT_FRIENDSHIPS_FRIENDS_BY_USER_ID =
      "SELECT u.* FROM users u RIGHT JOIN friendships f ON u.id = f.friend_id WHERE f.user_id = ?";

  public static final String DELETE_FRIENDSHIPS_BY_USER_ID =
      "DELETE FROM friendships WHERE (user_id = ? AND friend_id = ?)";

  public static final String SELECT_FRIENDSHIPS_FRIEND_BY_USER_ID =
      "SELECT u.* FROM users u RIGHT JOIN friendships f ON u.id = f.friend_id WHERE f.user_id = ? AND f.friend_id = ?";

  public static final String INSERT_FRIENDSHIPS =
      "INSERT INTO friendships (user_id, friend_id, approved) VALUES (?, ?, ?)";
}
