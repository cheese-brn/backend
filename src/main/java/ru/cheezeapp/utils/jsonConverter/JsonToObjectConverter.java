package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    /**
     * Метод конвертации JSON строки в список {@link FactParametrEntity}
     *
     * @param json   JSON строка
     * @param strain штамм, которому соответствуют фактические параметры
     * @return Список фиктических параметров
     */
    private List<FactParametrEntity> jsonToFactParams(String json, StrainEntity strain) {
        List<FactParametrEntity> factParams = new ArrayList<>();
        try {
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
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return factParams;
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
                    .id(jsonNodes.get("id").longValue())
                    .name(jsonNodes.get("name").textValue())
                    .description(jsonNodes.get("description").textValue())
                    .propertyType(jsonNodes.get("isFunc").booleanValue())
                    .build();
            property.setSubProperties(jsonToSubProperties(jsonNodes.get("subProps").textValue(), property));
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
        List<SubPropertyEntity> subPropertyEntities = new ArrayList<>();
        try {
            JsonNode subPropertiesJson = mapper.readTree(json);
            for (JsonNode subProperty : subPropertiesJson) {
                SubPropertyEntity subPropertyEntity = SubPropertyEntity.builder()
                        .id(subProperty.get("id").longValue())
                        .name(subProperty.get("name").textValue())
                        .property(property)
                        .build();
                DataTypeEntity dataType = dataTypeRepository.findById(subProperty.get("datatype").longValue())
                        .orElse(dataTypeRepository.getById(1L));
                subPropertyEntity.setDataType(dataType);
                subPropertyEntities.add(subPropertyEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subPropertyEntities;
    }

}

