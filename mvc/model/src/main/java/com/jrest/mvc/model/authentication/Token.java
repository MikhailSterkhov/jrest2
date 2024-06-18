package com.jrest.mvc.model.authentication;

import lombok.*;

/**
 * Класс, представляющий токен аутентификации.
 * <p>
 * Этот класс используется для хранения и обработки токенов аутентификации,
 * таких как простые строковые токены или комбинации имени пользователя и пароля.
 * </p>
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Token {

    /**
     * Вложенный класс, представляющий комбинацию имени пользователя и пароля.
     * <p>
     * Этот класс используется для хранения учетных данных в виде пары
     * "имя пользователя - пароль" и предоставляет удобный метод для создания таких пар.
     * </p>
     */
    @Getter
    @EqualsAndHashCode
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UsernameAndPassword {

        /**
         * Создает экземпляр {@code UsernameAndPassword} с заданными именем пользователя и паролем.
         *
         * @param username имя пользователя
         * @param password пароль
         * @return новый экземпляр {@code UsernameAndPassword}
         */
        public static UsernameAndPassword of(String username, String password) {
            return new UsernameAndPassword(username, password);
        }

        private final String username;
        private final String password;

        /**
         * Возвращает строковое представление учетных данных в формате "username:password".
         *
         * @return строковое представление учетных данных
         */
        @Override
        public String toString() {
            return String.format("%s:%s", username, password);
        }
    }

    /**
     * Создает экземпляр {@code Token} с заданным токеном.
     *
     * @param token строковый токен аутентификации
     * @return новый экземпляр {@code Token}
     */
    public static Token of(String token) {
        return new Token(token);
    }

    /**
     * Создает экземпляр {@code Token} с заданной комбинацией имени пользователя и пароля.
     *
     * @param token объект {@code UsernameAndPassword}, представляющий учетные данные
     * @return новый экземпляр {@code Token}
     */
    public static Token basic(UsernameAndPassword token) {
        return of(token.toString());
    }

    private final String value;

    /**
     * Извлекает учетные данные из токена в виде объекта {@code UsernameAndPassword}.
     * <p>
     * Этот метод предполагает, что токен имеет формат "username:password".
     * </p>
     *
     * @return объект {@code UsernameAndPassword}, представляющий учетные данные
     */
    public UsernameAndPassword readBasicCredentials() {
        if (value.isEmpty() || !value.contains(":")) {
            return null;
        }
        int delimiter = value.indexOf(':');
        String username = value.substring(0, delimiter);
        String password = value.substring(delimiter + 1);
        return UsernameAndPassword.of(username, password);
    }
}
