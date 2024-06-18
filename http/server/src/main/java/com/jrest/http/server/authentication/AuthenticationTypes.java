package com.jrest.http.server.authentication;

import com.jrest.mvc.model.authentication.Authentication;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилитный класс для получения всех статических экземпляров {@link Authentication}, определённых в классе {@link Authentication}.
 * <p>
 * Этот класс использует рефлексию для нахождения всех статических полей типа {@link Authentication} в классе {@link Authentication},
 * кэширует их и предоставляет метод для получения этих экземпляров.
 * </p>
 */
@UtilityClass
public class AuthenticationTypes {

    private Authentication[] cache;

    /**
     * Возвращает все статические экземпляры {@link Authentication}, определённые в классе {@link Authentication}.
     *
     * @return Массив экземпляров {@link Authentication}.
     */
    public Authentication[] values() {
        if (cache == null) {
            try {
                initializeCache();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access authentication fields", e);
            }
        }
        return cache;
    }

    /**
     * Инициализирует кэш, используя рефлексию для нахождения всех статических полей
     * типа {@link Authentication} в классе {@link Authentication}.
     *
     * @throws IllegalAccessException если доступ к полю невозможен.
     */
    private void initializeCache() throws IllegalAccessException {
        List<Authentication> authenticationList = new ArrayList<>();

        for (Field field : Authentication.class.getDeclaredFields()) {
            if (field.getType() == Authentication.class && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                authenticationList.add((Authentication) field.get(null));
            }
        }
        cache = authenticationList.toArray(new Authentication[0]);
    }
}
