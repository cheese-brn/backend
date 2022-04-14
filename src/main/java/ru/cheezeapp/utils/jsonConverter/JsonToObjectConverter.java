package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.tools.javac.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.*;
import ru.cheezeapp.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JsonToObjectConverter {

    @Autowired
    RodStrainRepository rodStrainRepository;

    @Autowired
    VidStrainRepository vidStrainRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    SubPropertyRepository subPropertyRepository;

    @Autowired
    FactParametrRepository factParametrRepository;

    @Autowired
    FactParametrFuncRepository factParametrFuncRepository;

    @Autowired
    DependencyTableRepository dependencyTableRepository;

    @Autowired
    DataTypeRepository dataTypeRepository;

    static ObjectMapper mapper = new ObjectMapper();

    /**
     * Метод конвертации JSON строки в {@link StrainEntity}
     *
     * @param json JSON строка
     * @return Сущность штамма
     */
    public StrainEntity jsonToStrain(String json) {
        try {
            ObjectNode jsonNodes = mapper.readValue(json, ObjectNode.class);
            Optional<VidStrainEntity> vidStrain = vidStrainRepository.findById(jsonNodes.get("vidId").longValue());
            if (vidStrain.isPresent()) {
                StrainEntity strain = StrainEntity.builder()
                        .id(jsonNodes.get("id").longValue())
                        .annotation(jsonNodes.path("annotation").textValue())
                        .exemplar(jsonNodes.path("exemplar").textValue())
                        .modification(jsonNodes.path("modification").textValue())
                        .obtainingMethod(jsonNodes.path("obtainingMethod").textValue())
                        .origin(jsonNodes.path("origin").textValue())
                        .lost(jsonNodes.path("isLost").booleanValue())
                        .vidStrain(vidStrain.get())
                        .build();
                strain.setFactParametrs(jsonToFactParams(jsonNodes.path("factParams").toString(),
                        strain));
                strain.setFactParametrsFunc(jsonToFactParamsFunc(jsonNodes.path("factParams").toString(),
                        strain));
                return strain;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Метод конвертации JSON строки в список {@link FactParametrEntity}
     *
     * @param json   json JSON строка
     * @param strain штамм
     * @return список фактических параметров
     */
    public List<FactParametrEntity> jsonToFactParams(String json, StrainEntity strain) {
        try {
            List<FactParametrEntity> factParams = new ArrayList<>();
            JsonNode factParamsJson = mapper.readTree(json);
            for (JsonNode property : factParamsJson)
                for (JsonNode subProp : property.path("subProps")) {
                    Optional<PropertyEntity> propertyEntity = propertyRepository.findById(property.path("id").longValue());
                    Optional<SubPropertyEntity> subPropertyEntity = subPropertyRepository
                            .findById(subProp.path("id").longValue());
                    if (!propertyEntity.isPresent() || !subPropertyEntity.isPresent())
                        continue;
                    factParams.add(FactParametrEntity.builder()
                            .value(subProp.path("value").textValue())
                            .strain(strain)
                            .property(propertyEntity.get())
                            .subProperty(subPropertyEntity.get())
                            .build());
                }
            return factParams;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FactParametrFuncEntity> jsonToFactParamsFunc(String json, StrainEntity strain) {
        try {
            List<FactParametrFuncEntity> factParametrFuncEntities = new ArrayList<>();
            JsonNode factParamsJson = mapper.readTree(json);
            for (JsonNode property : factParamsJson) {
                if (property.has("functions"))
                    for (JsonNode function : property.path("functions")) {
                        Optional<PropertyEntity> propertyEntity = propertyRepository
                                .findById(property.path("id").longValue());
                        if (!propertyEntity.isPresent())
                            continue;
                        JsonNode firstParamNode = function.get("firstParam");
                        JsonNode secondParamNode = function.get("secondParam");
                        Optional<SubPropertyEntity> firstParam = subPropertyRepository
                                .findById(firstParamNode.get("id").longValue());
                        Optional<SubPropertyEntity> secondParam = subPropertyRepository
                                .findById(secondParamNode.get("id").longValue());
                        if (!firstParam.isPresent() || !secondParam.isPresent())
                            continue;
                        Optional<DependencyTableEntity> dependency = dependencyTableRepository
                                .findByFirstSubPropertyAndSecondSubProperty(firstParam.get(), secondParam.get());
                        if (!dependency.isPresent())
                            continue;
                        List<String> firstParamValues = getFunctionValuesFromParameter(firstParamNode);
                        List<String> secondParamValues = getFunctionValuesFromParameter(secondParamNode);
                        for (int i = 0; i < firstParamValues.size(); i++)
                            factParametrFuncEntities.add(FactParametrFuncEntity.builder()
                                    .firstParametr(firstParamValues.get(i))
                                    .secondParametr(secondParamValues.get(i))
                                    .dependencyTable(dependency.get())
                                    .strain(strain)
                                    .build());
                    }
            }
            return factParametrFuncEntities;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> getFunctionValuesFromParameter(JsonNode parameter) {
        List<String> values = new ArrayList<>();
        JsonNode valueNode = parameter.get("values");
        for (JsonNode value : valueNode)
            values.add(value.asText());
        return values;
    }

    /**
     * Метод конвертации JSON строки в {@link RodStrainEntity}
     *
     * @param json JSON строка
     * @return Сущность рода
     */
    public RodStrainEntity jsonToRod(String json) {
        try {
            ObjectNode jsonNodes = mapper.readValue(json, ObjectNode.class);
            return RodStrainEntity.builder()
                    .id(jsonNodes.path("rodId").longValue())
                    .name(jsonNodes.path("name").textValue())
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Метод конвертации JSON строки в {@link VidStrainEntity}
     *
     * @param json JSON вида
     * @return Сущность вида
     */
    public VidStrainEntity jsonToVid(String json) {
        try {
            ObjectNode jsonNodes = mapper.readValue(json, ObjectNode.class);
            Optional<RodStrainEntity> rodStrain = rodStrainRepository.findById(jsonNodes.path("rodId").longValue());
            if (rodStrain.isPresent()) {
                return VidStrainEntity.builder()
                        .id(jsonNodes.path("vidId").longValue())
                        .name(jsonNodes.path("name").textValue())
                        .rodStrain(rodStrain.get())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод конвертации JSON строки в {@link PropertyEntity}
     *
     * @param json JSON строка
     * @return Сущность свойства
     */
    public PropertyEntity jsonToProperty(String json) {
        try {
            ObjectNode jsonNodes = mapper.readValue(json, ObjectNode.class);
            PropertyEntity property = PropertyEntity.builder()
                    .id(jsonNodes.path("id").longValue())
                    .name(jsonNodes.path("name").textValue())
                    .description(jsonNodes.path("description").textValue())
                    .build();
            property.setSubProperties(jsonToSubProperties(jsonNodes.path("subProps").toString(), property));
            if (jsonNodes.has("functions")) {
                List<Pair<List<SubPropertyEntity>, String>> functions = jsonToFunction(json, property);
                for (Pair<List<SubPropertyEntity>, String> function : functions)
                    property.getSubProperties().addAll(function.fst);
                property.setPropertyType(true);
            } else
                property.setPropertyType(false);
            List<FactParametrEntity> factParametrEntityList = new ArrayList<>();
            for (SubPropertyEntity subPropertyEntity : property.getSubProperties())
                factParametrEntityList.addAll(subPropertyEntity.getFactParametrs());
            property.setFactParametrs(factParametrEntityList);
            property.setDependencies(new ArrayList<>());
            return property;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод конвертации JSON строки в список {@link SubPropertyEntity}
     *
     * @param json     JSON строка
     * @param property свойство, которому соответствуют подсвойства
     * @return список подсвойств
     */
    private List<SubPropertyEntity> jsonToSubProperties(String json, PropertyEntity property) {
        try {
            List<SubPropertyEntity> subPropertyEntities = new ArrayList<>();
            JsonNode subPropertiesJson = mapper.readTree(json);
            for (JsonNode subProperty : subPropertiesJson) {
                SubPropertyEntity subPropertyEntity = jsonNodeToSubProperty(subProperty, property);
                subPropertyEntities.add(subPropertyEntity);
            }
            return subPropertyEntities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод конвертации JSON строки в список подсвойств функции
     *
     * @param json     JSON строка
     * @param property свойство, которому соответствуют подсвойства
     * @return список функциональных подсвойств
     */
    public List<Pair<List<SubPropertyEntity>, String>> jsonToFunction(String json, PropertyEntity property) {
        try {
            JsonNode propertyNode = mapper.readTree(json);
            if (propertyNode.path("functions") == null)
                return null;
            List<Pair<List<SubPropertyEntity>, String>> functions = new ArrayList<>();
            JsonNode functionsNode = propertyNode.path("functions");
            for (JsonNode functionNode : functionsNode) {
                Pair<List<SubPropertyEntity>, String> function = new Pair<>(new ArrayList<>(),
                        functionNode.path("name").textValue());
                function.fst.add(jsonNodeToSubProperty(functionNode.path("firstParam"), property));
                function.fst.add(jsonNodeToSubProperty(functionNode.path("secondParam"), property));
                functions.add(function);
            }
            return functions;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SubPropertyEntity jsonNodeToSubProperty(JsonNode subProperty, PropertyEntity property) {
        Long id = subProperty.path("id").longValue();
        SubPropertyEntity subPropertyEntity = SubPropertyEntity.builder()
                .id(id)
                .name(subProperty.path("name").textValue())
                .property(property)
                .unit(subProperty.path("unit").textValue())
                .factParametrs(factParametrRepository.findFactParametrEntitiesBySubPropertyId(id))
                .build();
        subPropertyEntity.setCypher(subPropertyEntity.hashCode());
        DataTypeEntity dataType = dataTypeRepository.findById(subProperty.path("datatype").longValue())
                .orElse(dataTypeRepository.getById(1L));
        subPropertyEntity.setDataType(dataType);
        return subPropertyEntity;
    }

}

