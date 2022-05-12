package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Класс преобразования ответов на запросы в Json
 *
 * @author Pavel Chupikov
 */
public class ResponseToJsonConverter {

    static ObjectMapper mapper = new ObjectMapper();

    /**
     * Метод преобразования ответа на запрос добавления нового штамма в JSON
     *
     * @param newStrainId id добавленного штамма
     * @return JSON ответа
     */
    public static String strainCreationResponseToJson(Long newStrainId) {
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("message", "Штамм был успешно добавлен");
        if (newStrainId != null)
            responseNode.put("data", newStrainId);
        else responseNode.put("data", "");
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(responseNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод преобразования ответа на запрос в JSON
     *
     * @param message ответ на запрос
     * @return JSON ответа
     */
    public static String responseToJson(String message) {
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("message", message);
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(responseNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

}
