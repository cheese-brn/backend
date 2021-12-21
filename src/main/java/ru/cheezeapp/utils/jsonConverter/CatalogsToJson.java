package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.cheezeapp.entity.*;

import java.util.List;

public class CatalogsToJson {

    static ObjectMapper mapper = new ObjectMapper();

    public static String vidCatalogToJson(List<VidStrainEntity> vids) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (VidStrainEntity vid : vids) {
            ObjectNode vidNode = mapper.createObjectNode();
            vidNode.put("id", vid.getId());
            vidNode.put("name", vid.getName());
            vidNode.put("rodName", vid.getRodStrain().getName());
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

    public static String rodCatalogToJson(List<RodStrainEntity> rods) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (RodStrainEntity rod : rods) {
            ObjectNode rodNode = mapper.createObjectNode();
            rodNode.put("id", rod.getId());
            rodNode.put("name", rod.getName());
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

    public static String strainCatalogToJson(List<StrainEntity> strains) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (StrainEntity strain : strains) {
            ObjectNode vidNode = mapper.createObjectNode();
            vidNode.put("id", strain.getId());
            vidNode.put("name",
                    strain.getVidStrain().getRodStrain().getName() + " " + strain.getVidStrain().getName() +
                            " " + strain.getExemplar() + " " + strain.getModification());
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

    public static String propertyCatalogToJson(List<PropertyEntity> properties) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (PropertyEntity property : properties) {
            ObjectNode propertyNode = mapper.createObjectNode();
            propertyNode.put("id", property.getId());
            propertyNode.put("name", property.getName());
            propertyNode.put("description", property.getDescription());
            propertyNode.put("childrenCount", property.getSubProperties().size());
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

    public static String catalogSubpropertyToJson(List<SubPropertyEntity> subproperties) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (SubPropertyEntity subproperty : subproperties) {
            ObjectNode subpropertyNode = mapper.createObjectNode();
            subpropertyNode.put("id", subproperty.getId());
            subpropertyNode.put("name", subproperty.getName());
            subpropertyNode.put("propertyName", subproperty.getProperty().getName());
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
