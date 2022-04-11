package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.*;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.service.DependencyTableService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    DependencyTableService dependencyTableService;

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
                strain.setFactParametrsFunc(new ArrayList<>());
                return strain;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

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
            if (jsonNodes.has("function")) {
                List<SubPropertyEntity> params = jsonToSubProperties(jsonNodes.path("function").toString(), property);
                for (SubPropertyEntity param : Objects.requireNonNull(params))
                    property.getSubProperties().add(param);
                property.setPropertyType(true);
            } else
                property.setPropertyType(false);
            List<FactParametrEntity> factParametrEntityList = new ArrayList<>();
            for (SubPropertyEntity subPropertyEntity : property.getSubProperties())
                factParametrEntityList.addAll(subPropertyEntity.getFactParametrs());
            property.setFactParametrs(factParametrEntityList);
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
    public List<SubPropertyEntity> jsonToFunction(String json, PropertyEntity property) {
        try {
            List<SubPropertyEntity> functionParams = new ArrayList<>();
            JsonNode function = mapper.readTree(json);
            if (function.path("function") == null)
                return null;
            List<SubPropertyEntity> params = jsonToSubProperties(function.path("function").toString(), property);
            for (SubPropertyEntity parameter : Objects.requireNonNull(params)) {
                functionParams.add(subPropertyRepository.findByCypher(parameter.getCypher()));
            }
            return functionParams;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}

