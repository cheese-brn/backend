package ru.cheezeapp.utils;

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
        strainNode.put("id", strain.getVidStrain().getId());
        strainNode.put("rod", strain.getVidStrain().getRodStrain().getName());
        strainNode.put("vid", strain.getVidStrain().getName());
        strainNode.put("author", "Placeholder");
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy',' kk:mm");
        strainNode.put("date", formatForDateNow.format(dateNow));
        strainNode.put("annotation", strain.getAnnotation());
        strainNode.put("exemplar", strain.getExemplar());
        strainNode.put("modification", strain.getModification());
        strainNode.put("obtainingMethod", strain.getObtainingMethod());
        strainNode.put("origin", strain.getOrigin());
        MultiValueMap<PropertyEntity, FactParametrEntity> propertyMap = new LinkedMultiValueMap<>();
        for (FactParametrEntity factParametr : strain.getFactParametrs())
            propertyMap.add(factParametr.getProperty(), factParametr);
        ArrayNode factParams = mapper.createArrayNode();
        for(PropertyEntity property : propertyMap.keySet()) {
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
            factParams.add(propertyNode);
        }
        strainNode.set("factParams", factParams);
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(strainNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String vidListToJson(List<VidStrainEntity> vids) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (VidStrainEntity vid : vids) {
            ObjectNode vidNode = mapper.createObjectNode();
            vidNode.put("name", vid.getName());
            vidNode.put("id", vid.getId());
            vidNode.put("childrenCount", vid.getStrains().size());
            arrayNode.add(vidNode);
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(arrayNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }

    }

    public static String rodListToJson(List<RodStrainEntity> rods) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (RodStrainEntity rod : rods) {
            ObjectNode rodNode = mapper.createObjectNode();
            rodNode.put("name", rod.getName());
            rodNode.put("id", rod.getId());
            rodNode.put("childrenCount", rod.getVids().size());
            arrayNode.add(rodNode);
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(arrayNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String strainListToJson(List<StrainEntity> strains) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (StrainEntity strain : strains) {
            ObjectNode vidNode = mapper.createObjectNode();
            vidNode.put("name",
                    strain.getVidStrain().getRodStrain().getName() + " " + strain.getVidStrain().getName() +
                            " " + strain.getExemplar() + " " + strain.getModification());
            vidNode.put("id", strain.getId());
            arrayNode.add(vidNode);
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(arrayNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String propertyListToJson(List<PropertyEntity> properties) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (PropertyEntity property : properties) {
            ObjectNode propertyNode = mapper.createObjectNode();
            propertyNode.put("name", property.getName());
            propertyNode.put("description", property.getDescription());
            propertyNode.put("id", property.getId());
            arrayNode.add(propertyNode);
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(arrayNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String subpropertyListToJson(List<SubPropertyEntity> subproperties) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (SubPropertyEntity subproperty : subproperties) {
            ObjectNode subpropertyNode = mapper.createObjectNode();
            subpropertyNode.put("name", subproperty.getName());
            subpropertyNode.put("id", subproperty.getId());
            subpropertyNode.put("datatype", subproperty.getDataType().getName());
            arrayNode.add(subpropertyNode);
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(arrayNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }
}
