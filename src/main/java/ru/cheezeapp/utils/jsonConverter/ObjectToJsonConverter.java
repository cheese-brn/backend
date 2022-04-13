package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.cheezeapp.entity.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс, содержащий методы конвертирования объектов в Json
 */
public class ObjectToJsonConverter {

    static ObjectMapper mapper = new ObjectMapper();

    /**
     * Конвертация штамма в Json
     *
     * @param strain Модель штамма
     * @return Json штамма
     */
    public static String strainToJson(StrainEntity strain) {
        ObjectNode strainNode = mapper.createObjectNode();
        strainNode.put("id", strain.getId());
        strainNode.put("rod", strain.getVidStrain().getRodStrain().getName());
        strainNode.put("rodId", strain.getVidStrain().getRodStrain().getId());
        strainNode.put("vid", strain.getVidStrain().getName());
        strainNode.put("vidId", strain.getVidStrain().getId());
        strainNode.put("author", "Placeholder");
        strainNode.put("isLost", strain.isLost());
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy',' kk:mm");
        strainNode.put("date", formatForDateNow.format(dateNow));
        strainNode.put("annotation", strain.getAnnotation());
        strainNode.put("exemplar", strain.getExemplar());
        strainNode.put("modification", strain.getModification());
        strainNode.put("obtainingMethod", strain.getObtainingMethod());
        strainNode.put("origin", strain.getOrigin());
        try {
            strainNode.set("factParams", mapper.readTree(factParamsToJson(strain.getFactParametrs())));
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(strainNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод конвертации фактических параметров в Json
     *
     * @param factParams список фактических параметров
     * @return Json строка со списком фактических параметров
     */
    private static String factParamsToJson(List<FactParametrEntity> factParams) {
        MultiValueMap<PropertyEntity, FactParametrEntity> propertyMap = new LinkedMultiValueMap<>();
        for (FactParametrEntity factParametr : factParams.stream()
                .filter(param -> !param.getProperty().isDeleted())
                .collect(Collectors.toList()))
            propertyMap.add(factParametr.getProperty(), factParametr);
        ArrayNode factParamsArrayNode = mapper.createArrayNode();
        for (PropertyEntity property : propertyMap.keySet()) {
            ObjectNode propertyNode = mapper.createObjectNode();
            ArrayNode factParamNodes = mapper.createArrayNode();
            propertyNode.put("id", property.getId());
            propertyNode.put("name", property.getName());
            propertyNode.put("description", property.getDescription());
            for (FactParametrEntity factParametr : Objects.requireNonNull(propertyMap.get(property))) {
                ObjectNode factParamNode = mapper.createObjectNode();
                factParamNode.put("id", factParametr.getSubProperty().getId());
                factParamNode.put("name", factParametr.getSubProperty().getName());
                factParamNode.put("value", factParametr.getValue());
                factParamNodes.add(factParamNode);
            }
            propertyNode.set("subProps", factParamNodes);
            factParamsArrayNode.add(propertyNode);
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(factParamsArrayNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Конвертация рода в Json
     *
     * @param rod Модель рода
     * @return Json рода
     */
    public static String rodToJson(RodStrainEntity rod) {
        ObjectNode rodNode = mapper.createObjectNode();
        rodNode.put("id", rod.getId());
        rodNode.put("name", rod.getName());
        rodNode.put("childrenCount", rod.getVids().size());
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(rodNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Конвертация вида в Json
     *
     * @param vid Модель вида
     * @return Json вида
     */
    public static String vidToJson(VidStrainEntity vid) {
        ObjectNode vidNode = mapper.createObjectNode();
        vidNode.put("id", vid.getId());
        vidNode.put("name", vid.getName());
        vidNode.put("rodId", vid.getRodStrain().getId());
        vidNode.put("childrenCount", vid.getStrains().size());
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(vidNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Конвертация свойства в Json
     *
     * @param property Модель свойства
     * @return Json свойства
     */
    public static String propertyToJson(PropertyEntity property) {
        ObjectNode propertyNode = mapper.createObjectNode();
        propertyNode.put("id", property.getId());
        propertyNode.put("name", property.getName());
        propertyNode.put("description", property.getDescription());
        propertyNode.put("childrenCount", property.getSubProperties().size());
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(propertyNode)
                    .replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Конвертация подсвойства в Json
     *
     * @param subproperty Модель подсвойства
     * @return Json подсвойства
     */
    public static String subpropertyToJson(SubPropertyEntity subproperty) {
        ObjectNode subpropertyNode = mapper.createObjectNode();
        subpropertyNode.put("id", subproperty.getId());
        subpropertyNode.put("name", subproperty.getName());
        subpropertyNode.put("propertyName", subproperty.getProperty().getName());
        subpropertyNode.put("datatype", subproperty.getDataType().getName());
        subpropertyNode.put("unit", subproperty.getUnit());
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(subpropertyNode)
                    .replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

}
