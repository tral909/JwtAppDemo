# JwtAppDemo

Добавить тестового пользователя, который может логиниться, то есть
получить токен по имени пользователя username (password - test)
```
INSERT INTO users VALUES
(1, 'tony', 'tony@email.ru', 'tony', 'montana', '$2a$04$r9rQ13FJevYTFl10KVyTDOBIi5UuVb8tTfcevI.B/Qw0lVksesere', '2020-07-14T10:56:51', '2020-07-14T10:56:51', 'ACTIVE');
```

Добавить роли
```
INSERT INTO roles VALUES
(1, 'ROLE_USER', '2020-07-14T10:56:51', '2020-07-14T10:56:51', 'ACTIVE'),
(2, 'ROLE_ADMIN', '2020-07-14T10:56:51', '2020-07-14T10:56:51', 'ACTIVE');
```

Добавить роли пользователя и админа для tony
```
INSERT INTO user_roles VALUES (1, 1), (1, 2);
```

Получить токен можно отправив запрос `POST http://loaclhost:8085/api/v1/auth/login` c телом `{"username": "tony", "password": "test"}`

Запросить информацию по пользователю `GET http://localhost:8085/api/v1/admin/users/1` c хедером `Authentication: Bearer_{jwtToken}`

Добавить просто пользователя (не админа)
```
INSERT INTO users VALUES
(2, 'testuser', 'user@email.ru', 'simpleuser', 'test', '$2a$04$r9rQ13FJevYTFl10KVyTDOBIi5UuVb8tTfcevI.B/Qw0lVksesere', '2020-07-15T10:00:00', '2020-07-15T10:00:00', 'ACTIVE');

INSERT INTO user_roles VALUES (2, 1);
```

testuser после получения токена `api/v1/auth/login` может смотреть пользователей через `/api/v1/users/{id}` и не может через `api/v1/admin/users/{id}` из-за настроек в `SecurityConfig`