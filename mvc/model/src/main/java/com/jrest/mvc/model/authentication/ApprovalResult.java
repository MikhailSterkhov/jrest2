package com.jrest.mvc.model.authentication;

import com.jrest.mvc.model.Content;
import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.model.ResponseCode;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.Optional;

/**
 * Результат аутентификации, который указывает на статус одобрения, отказа или пропуска.
 * <p>
 * Этот класс предоставляет удобные методы для создания объектов {@code ApprovalResult}
 * с различными статусами аутентификации, такими как одобрено, отказано или пропущено.
 * Он также содержит методы для проверки текущего статуса и формирования HTTP-ответа
 * в случае отказа.
 * </p>
 */
@Builder
@ToString
@EqualsAndHashCode
public class ApprovalResult {

    /**
     * Перечисление, представляющее возможные статусы аутентификации.
     */
    public enum Status {
        APPROVED, FORBIDDEN, SKIPPED
    }

    /**
     * Создает экземпляр {@code ApprovalResult} со статусом "одобрено".
     *
     * @return экземпляр {@code ApprovalResult} со статусом "одобрено".
     */
    public static ApprovalResult approve() {
        return ApprovalResult.builder()
                .status(Status.APPROVED)
                .build();
    }

    /**
     * Создает экземпляр {@code ApprovalResult} со статусом "отказано".
     *
     * @return экземпляр {@code ApprovalResult} со статусом "отказано".
     */
    public static ApprovalResult forbidden() {
        return ApprovalResult.builder()
                .status(Status.FORBIDDEN)
                .build();
    }

    /**
     * Создает экземпляр {@code ApprovalResult} со статусом "пропущено".
     *
     * @return экземпляр {@code ApprovalResult} со статусом "пропущено".
     */
    public static ApprovalResult skip() {
        return ApprovalResult.builder()
                .status(Status.SKIPPED)
                .build();
    }

    /**
     * Создает экземпляр {@code ApprovalResult} на основе логического значения.
     * Если {@code isSuccess} равно {@code true}, возвращает одобренный результат,
     * если {@code isSuccess} равно {@code false}, возвращает отказанный результат,
     * в противном случае возвращает пропущенный результат.
     *
     * @param isSuccess логическое значение, представляющее успешность операции.
     * @return соответствующий {@code ApprovalResult} в зависимости от значения {@code isSuccess}.
     */
    public static ApprovalResult bool(Boolean isSuccess) {
        return isSuccess != null ? (isSuccess ? approve() : forbidden()) : skip();
    }

    private final Status status;

    @Getter
    private Headers headers;
    @Getter
    private Content content;

    /**
     * Проверяет, является ли статус этого {@code ApprovalResult} одобренным.
     *
     * @return {@code true}, если статус одобренный, иначе {@code false}.
     */
    public boolean isApproved() {
        return Objects.equals(status, Status.APPROVED);
    }

    /**
     * Проверяет, является ли статус этого {@code ApprovalResult} отказанным.
     *
     * @return {@code true}, если статус отказанный, иначе {@code false}.
     */
    public boolean isForbidden() {
        return Objects.equals(status, Status.FORBIDDEN);
    }

    /**
     * Проверяет, является ли статус этого {@code ApprovalResult} пропущенным.
     *
     * @return {@code true}, если статус пропущенный, иначе {@code false}.
     */
    public boolean isSkipped() {
        return Objects.equals(status, Status.SKIPPED);
    }

    /**
     * Возвращает HTTP-ответ с кодом "403 Forbidden", если статус этого {@code ApprovalResult}
     * является отказанным, иначе возвращает {@code null}.
     *
     * @return HTTP-ответ с кодом "403 Forbidden" или {@code null}.
     */
    public HttpResponse getForbiddenResponse() {
        if (!isForbidden()) {
            return null;
        }
        return HttpResponse.of(ResponseCode.FORBIDDEN)
                .toBuilder()
                .headers(Optional.ofNullable(headers)
                        .orElseGet(Headers::newHeaders))
                .content(content)
                .build();
    }

    /**
     * Устанавливает заголовки для этого {@code ApprovalResult}.
     *
     * @param headers заголовки для установки.
     * @return текущий экземпляр {@code ApprovalResult} с установленными заголовками.
     */
    public ApprovalResult withHeaders(Headers headers) {
        this.headers = headers;
        return this;
    }

    /**
     * Устанавливает содержимое для этого {@code ApprovalResult}.
     *
     * @param content содержимое для установки.
     * @return текущий экземпляр {@code ApprovalResult} с установленным содержимым.
     */
    public ApprovalResult withContent(Content content) {
        this.content = content;
        return this;
    }
}
