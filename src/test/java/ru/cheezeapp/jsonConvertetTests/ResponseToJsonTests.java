package ru.cheezeapp.jsonConvertetTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cheezeapp.utils.jsonConverter.ResponseToJsonConverter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для конвертера сообщений от сервера на клиент в JSON строку
 *
 * @author Pavel Chupikov
 */
public class ResponseToJsonTests {

    @Test
    @DisplayName("Тест преобразования ответа на запрос добавления нового штамма в JSON")
    void strainCreationResponseToJsonTest() {
        Long strainTestId = 1L;
        String result = "{" + System.lineSeparator() +
                "  \"message\" : \"Штамм был успешно добавлен\"," + System.lineSeparator() +
                "  \"data\" : " + strainTestId + System.lineSeparator() +
                "}";
        String methodResult = ResponseToJsonConverter.strainCreationResponseToJson(strainTestId);
        assertEquals(result, methodResult);
    }

    @Test
    @DisplayName("Тест преобразования ответа на запрос в JSON")
    void responseToJsonTest() {
        String testMessage = "Test message";
        String result = "{" + System.lineSeparator() +
                "  \"message\" : \"" + testMessage + "\"" + System.lineSeparator() +
                "}";
        String methodResult = ResponseToJsonConverter.responseToJson(testMessage);
        assertEquals(result, methodResult);
    }

}
