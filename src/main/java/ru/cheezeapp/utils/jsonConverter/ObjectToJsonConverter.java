package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.cheezeapp.entity.*;

import java.text.SimpleDateFormat;
import java.util.*;
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
            strainNode.set("factParams", mapper.readTree(factParamsToJson(strain)));
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(strainNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод конвертации фактических параметров в Json
     *
     * @param strain штамм
     * @return Json строка со списком фактических параметров
     */
    private static String factParamsToJson(StrainEntity strain) {
        MultiValueMap<PropertyEntity, FactParametrEntity> propertyMap = new LinkedMultiValueMap<>();
        for (FactParametrEntity factParametr : strain.getFactParametrs().stream()
                .filter(param -> !param.getProperty().isDeleted())
                .collect(Collectors.toList()))
            propertyMap.add(factParametr.getProperty(), factParametr);
        List<DependencyTableEntity> dependencies = strain.getFactParametrsFunc()
                .stream()
                .map(FactParametrFuncEntity::getDependencyTable)
                .distinct()
                .collect(Collectors.toList());
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
            List<DependencyTableEntity> functions = dependencies.stream()
                    .filter(dependency -> dependency.getProperty().getId().equals(property.getId()))
                            .collect(Collectors.toList());
            dependencies.removeAll(functions);
            ArrayNode functionsNode = mapper.createArrayNode();
            for (DependencyTableEntity function : functions) {
                functionsNode.add(functionsToJson(strain, function));
            }
            propertyNode.set("functions", functionsNode);
            factParamsArrayNode.add(propertyNode);
        }
        for (DependencyTableEntity function : dependencies) {
            ObjectNode propertyNode = mapper.createObjectNode();
            propertyNode.put("id", function.getProperty().getId());
            propertyNode.put("name", function.getProperty().getName());
            propertyNode.put("description", function.getProperty().getDescription());
            propertyNode.set("functions", functionsToJson(strain, function));
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
     * Метод конвертации функции в JSON
     * @param strain штамм
     * @param function функция
     * @return ObjectNode функции
     */
    private static ObjectNode functionsToJson(StrainEntity strain, DependencyTableEntity function) {
        ObjectNode functionNode = mapper.createObjectNode();
        functionNode.put("funcName", function.getFunctionName());
        functionNode.set("firstParam", funcParamsToJson(function, strain, 1));
        functionNode.set("secondParam", funcParamsToJson(function, strain, 2));
        return functionNode;
    }

    /**
     * Метод конвертации фактических параметров функции в JSON
     *
     * @param dependencyTable функция
     * @param strain штамм
     * @param paramNumber номер параметра (1 или 2)
     * @return ObjectNode функции
     */
    private static ObjectNode funcParamsToJson(DependencyTableEntity dependencyTable, StrainEntity strain, int paramNumber) {
        ObjectNode funcParam = mapper.createObjectNode();
        List<FactParametrFuncEntity> factParametrFuncEntities = strain.getFactParametrsFunc()
                .stream().filter(param -> param.getDependencyTable().getId().equals(dependencyTable.getId()))
                .collect(Collectors.toList());
        if (paramNumber == 1) {
            funcParam.put("id", dependencyTable.getFirstSubProperty().getId());
            funcParam.put("name", dependencyTable.getFirstSubProperty().getName());
            funcParam.put("unit", dependencyTable.getFirstSubProperty().getUnit());
            ArrayNode valuesNode = mapper.createArrayNode();
            for (FactParametrFuncEntity param : factParametrFuncEntities)
                valuesNode.add(param.getFirstParametr());
            funcParam.set("values", valuesNode);
        }
        else {
            funcParam.put("id", dependencyTable.getSecondSubProperty().getId());
            funcParam.put("name", dependencyTable.getSecondSubProperty().getName());
            funcParam.put("unit", dependencyTable.getSecondSubProperty().getUnit());
            ArrayNode valuesNode = mapper.createArrayNode();
            for (FactParametrFuncEntity param : factParametrFuncEntities)
                valuesNode.add(param.getSecondParametr());
            funcParam.set("values", valuesNode);
        }
        return funcParam;
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
