package com.jrest.mvc.model.util;

import com.jrest.mvc.model.HttpRestException;
import lombok.experimental.UtilityClass;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Утилитарный класс для выполнения различных операций хеширования и кодирования.
 * <p>
 * Этот класс содержит методы для кодирования и декодирования строк в Base64,
 * а также методы для хеширования строк с использованием алгоритмов SHA-256 и SHA-512.
 * </p>
 */
@UtilityClass
public class HashingUtil {

    /**
     * Кодирует строку в формате Base64 с заданной кодировкой.
     *
     * @param string строка для кодирования
     * @param charset кодировка для преобразования строки в байты
     * @return закодированная строка в формате Base64
     */
    public String encodeBase64(String string, Charset charset) {
        return Base64.getEncoder().encodeToString(string.getBytes(charset));
    }

    /**
     * Декодирует строку из формата Base64 с заданной кодировкой.
     *
     * @param string строка в формате Base64 для декодирования
     * @param charset кодировка для преобразования байтов в строку
     * @return декодированная строка
     */
    public String decodeBase64(String string, Charset charset) {
        return new String(Base64.getDecoder().decode(string.getBytes()), charset);
    }

    /**
     * Кодирует строку в формате Base64 с использованием кодировки UTF-8.
     *
     * @param string строка для кодирования
     * @return закодированная строка в формате Base64
     */
    public String encodeBase64(String string) {
        return encodeBase64(string, StandardCharsets.UTF_8);
    }

    /**
     * Декодирует строку из формата Base64 с использованием кодировки UTF-8.
     *
     * @param string строка в формате Base64 для декодирования
     * @return декодированная строка
     */
    public String decodeBase64(String string) {
        return decodeBase64(string, StandardCharsets.UTF_8);
    }

    /**
     * Хеширует строку с использованием алгоритма SHA-256 и заданной кодировкой.
     *
     * @param string строка для хеширования
     * @param charset кодировка для преобразования строки в байты
     * @return хеш строки в виде шестнадцатеричной строки
     */
    public String encodeSha256(String string, Charset charset) {
        return hash("SHA-256", string, charset);
    }

    /**
     * Хеширует строку с использованием алгоритма SHA-256 и кодировки UTF-8.
     *
     * @param string строка для хеширования
     * @return хеш строки в виде шестнадцатеричной строки
     */
    public String encodeSha256(String string) {
        return encodeSha256(string, StandardCharsets.UTF_8);
    }

    /**
     * Хеширует строку с использованием алгоритма SHA-512 и заданной кодировкой.
     *
     * @param string строка для хеширования
     * @param charset кодировка для преобразования строки в байты
     * @return хеш строки в виде шестнадцатеричной строки
     */
    public String encodeSha512(String string, Charset charset) {
        return hash("SHA-512", string, charset);
    }

    /**
     * Хеширует строку с использованием алгоритма SHA-512 и кодировки UTF-8.
     *
     * @param string строка для хеширования
     * @return хеш строки в виде шестнадцатеричной строки
     */
    public String encodeSha512(String string) {
        return encodeSha512(string, StandardCharsets.UTF_8);
    }

    /**
     * Выполняет хеширование строки с использованием указанного алгоритма и кодировки.
     * <p>
     * Этот метод принимает алгоритм хеширования, строку для хеширования и кодировку,
     * и возвращает результат хеширования в виде шестнадцатеричной строки.
     * </p>
     *
     * @param algorithm алгоритм хеширования (например, "SHA-256", "SHA-512")
     * @param string строка для хеширования
     * @param charset кодировка, используемая для преобразования строки в байты
     * @return хеш строки в виде шестнадцатеричной строки
     */
    private String hash(String algorithm, String string, Charset charset) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(string.getBytes(charset));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1)
                    hexString.append('0');

                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception exception) {
            throw new HttpRestException(exception);
        }
    }
}
