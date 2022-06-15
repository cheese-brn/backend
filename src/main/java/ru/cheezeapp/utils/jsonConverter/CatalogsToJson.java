package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.cheezeapp.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс, содержащий методы конвертирования каталогов в Json
 *
 * @author Nikolay Golovnev
 */
public class CatalogsToJson {

    static ObjectMapper mapper = new ObjectMapper();

    /**
     * Метод конвертации списка видов в Json
     *
     * @param vids список видов
     * @return Json списка видов
     */
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
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод конвертации списка родов в Json
     *
     * @param rods список родов
     * @return Json списка родов
     */
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
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод конвертации списка родов с видами в Json
     *
     * @param rodStrainEntityList список родов
     * @return JSON родов с видами
     * @author Pavel Chupikov
     */
    public static String rodsWithVidsToJson(List<RodStrainEntity> rodStrainEntityList) {
        ArrayNode rodsNode = mapper.createArrayNode();
        for (RodStrainEntity rodStrain : rodStrainEntityList) {
            ObjectNode rodNode = mapper.createObjectNode();
            rodNode.put("id", rodStrain.getId());
            rodNode.put("name", rodStrain.getName());
            ArrayNode vidsNode = mapper.createArrayNode();
            for (VidStrainEntity vidStrain : rodStrain.getVids()) {
                ObjectNode vidNode = mapper.createObjectNode();
                vidNode.put("id", vidStrain.getId());
                vidNode.put("name", vidStrain.getName());
                vidsNode.add(vidNode);
            }
            rodNode.set("vids", vidsNode);
            rodsNode.add(rodNode);
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(rodsNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод конвертации списка штаммов в Json
     *
     * @param strains список штаммов
     * @return Json списка штаммов
     */
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
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод конвертации списка свойств в Json
     *
     * @param properties список свойств
     * @return Json списка свойств
     */
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
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод конвертации списка подсвойств без функций в Json
     *
     * @param subproperties список подсвойств
     * @return Json списка подсвойств
     * @author Pavel Chupikov
     */
    public static String subpropertyCatalogToJson(List<SubPropertyEntity> subproperties) {
        try {
            ObjectNode objectNode = mapper.createObjectNode();
            ArrayNode subpropertiesNode = listSubpropertiesToJson(subproperties);
            objectNode.set("subproperties", subpropertiesNode);
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(objectNode).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод конвертации списка подсвойств с функциями в Json
     *
     * @param property сповйство
     * @return Json списка подсвойств
     * @author Pavel Chupikov
     */
    public static String subpropertyCatalogWithFunctionsToJson(PropertyEntity property) {
        try {
            List<SubPropertyEntity> functionSubprops = new ArrayList<>();
            for (DependencyTableEntity dependency : property.getDependencies())
                functionSubprops.addAll(Arrays.asList(dependency.getFirstSubProperty(), dependency.getSecondSubProperty()));
            List<SubPropertyEntity> defaultSubprops = property.getSubProperties();
            defaultSubprops.removeAll(functionSubprops);
            ObjectNode subpropertiesAndFunctions = mapper.createObjectNode();
            subpropertiesAndFunctions.set("properties", listSubpropertiesToJson(defaultSubprops));
            subpropertiesAndFunctions.set("functions", functionCatalogToJson(property));
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(subpropertiesAndFunctions).replace("\\", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод перевода списка всех подсвойств в json
     *
     * @param subproperties список подсвойств
     * @return объект ArrayNode со списком подсвойств
     * @author Pavel Chupikov
     */
    private static ArrayNode listSubpropertiesToJson(List<SubPropertyEntity> subproperties) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (SubPropertyEntity subproperty : subproperties) {
            ObjectNode subpropertyNode = mapper.createObjectNode();
            subpropertyNode.put("id", subproperty.getId());
            subpropertyNode.put("name", subproperty.getName());
            subpropertyNode.put("propertyName", subproperty.getProperty().getName());
            subpropertyNode.put("datatype", subproperty.getDataType().getName());
            arrayNode.add(subpropertyNode);
        }
        return arrayNode;
    }

    /**
     * Метод перевода функций подсвойства в json
     *
     * @param property свойство
     * @return объект ArrayNode с функциями
     * @author Pavel Chupikov
     */
    private static ArrayNode functionCatalogToJson(PropertyEntity property) {
        ArrayNode functionsNode = mapper.createArrayNode();
        List<DependencyTableEntity> dependencyTableEntities = property.getDependencies();
        for (DependencyTableEntity dependency : dependencyTableEntities) {
            ObjectNode function = mapper.createObjectNode();
            function.set("firstParam", subpropertyToJson(dependency.getFirstSubProperty()));
            function.set("secondParam", subpropertyToJson(dependency.getSecondSubProperty()));
            function.put("name", dependency.getFunctionName());
            functionsNode.add(function);
        }
        return functionsNode;
    }

    /**
     * Метод перевода подсвойства в json
     *
     * @param subPropertyEntity подсвойство
     * @return объект ObjectNode с подсвойством
     * @author Pavel Chupikov
     */
    private static ObjectNode subpropertyToJson(SubPropertyEntity subPropertyEntity) {
        ObjectNode subpropertyNode = mapper.createObjectNode();
        subpropertyNode.put("id", subPropertyEntity.getId());
        subpropertyNode.put("name", subPropertyEntity.getName());
        subpropertyNode.put("datatype", subPropertyEntity.getDataType().getName());
        subpropertyNode.put("unit", subPropertyEntity.getUnit());
        return subpropertyNode;
    }

}
