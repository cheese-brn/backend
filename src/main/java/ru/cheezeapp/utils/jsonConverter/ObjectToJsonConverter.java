package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.cheezeapp.entity.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        strainNode.put("id", strain.getId());
        strainNode.put("rod", strain.getVidStrain().getRodStrain().getName());
        strainNode.put("rodId", strain.getVidStrain().getRodStrain().getId());
        strainNode.put("vid", strain.getVidStrain().getName());
        strainNode.put("vidId", strain.getVidStrain().getId());
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

    public static String rodToJson(RodStrainEntity rod){
        ObjectNode rodNode = mapper.createObjectNode();
        rodNode.put("id", rod.getId());
        rodNode.put("name", rod.getName());
        rodNode.put("childrenCount", rod.getVids().size());
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(rodNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String vidToJson(VidStrainEntity vid) {
        ObjectNode vidNode = mapper.createObjectNode();
        vidNode.put("id", vid.getId());
        vidNode.put("name", vid.getName());
        vidNode.put("rodName", vid.getRodStrain().getName());
        vidNode.put("childrenCount", vid.getStrains().size());
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(vidNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String propertyToJson(PropertyEntity property) {
        ObjectNode propertyNode = mapper.createObjectNode();
        propertyNode.put("id", property.getId());
        propertyNode.put("name", property.getName());
        propertyNode.put("description", property.getDescription());
        propertyNode.put("childrenCount", property.getSubProperties().size());
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(propertyNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String subpropertyToJson(SubPropertyEntity subproperty) {
        ObjectNode subpropertyNode = mapper.createObjectNode();
        subpropertyNode.put("id", subproperty.getId());
        subpropertyNode.put("name", subproperty.getName());
        subpropertyNode.put("propertyName", subproperty.getProperty().getName());
        subpropertyNode.put("datatype", subproperty.getDataType().getName());
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(subpropertyNode).replace("\\", "");
        }
        catch (Exception e) {
            return null;
        }
    }
}
