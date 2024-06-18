package com.jrest.mvc.model.authentication;

/**
 * Функциональный интерфейс для аутентификации HTTP-запросов.
 * <p>
 * Данный интерфейс используется для реализации различных способов аутентификации.
 * Метод {@code authenticate} принимает объект {@link UnapprovedRequest} и выполняет
 * проверку аутентификации, устанавливая результат аутентификации в объекте {@code UnapprovedRequest}.
 * </p>
 */
@FunctionalInterface
public interface HttpAuthenticator {

    /**
     * Аутентифицирует HTTP-запрос.
     * <p>
     * Этот метод должен проверить учетные данные в запросе и установить
     * результат аутентификации.
     * </p>
     *
     * @param request объект {@link UnapprovedRequest}, содержащий информацию о запросе и учетных данных.
     * @return {@link ApprovalResult}, содержащий информацию о статусе и результате аутентификации.
     */
    ApprovalResult authenticate(UnapprovedRequest request);
}
