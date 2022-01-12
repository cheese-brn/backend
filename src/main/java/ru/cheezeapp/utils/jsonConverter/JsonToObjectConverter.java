package ru.cheezeapp.utils.jsonConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.*;
import ru.cheezeapp.entity.FactParametrEntity;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

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

    static ObjectMapper mapper = new ObjectMapper();

    /**
     * Метод конвертации JSON строки в {@link StrainEntity}
     *
     * @param json JSON строка
     * @return Сущность штамма
     */
    public StrainEntity JsonToStrain(String json) {
        try {
            ObjectNode jsonNodes = mapper.readValue(json, ObjectNode.class);
            Optional<VidStrainEntity> vidStrain = vidStrainRepository.findById(jsonNodes.get("vidId").longValue());
            if (vidStrain.isPresent()) {
                StrainEntity newStrain = StrainEntity.builder()
                        .id(jsonNodes.get("id").longValue())
                        .annotation(jsonNodes.path("annotation").textValue())
                        .exemplar(jsonNodes.path("exemplar").textValue())
                        .modification(jsonNodes.path("modification").textValue())
                        .obtainingMethod(jsonNodes.path("obtainingMethod").textValue())
                        .origin(jsonNodes.path("origin").textValue())
                        .vidStrain(vidStrain.get())
                        .build();
                newStrain.setFactParametrs(this.JsonToFactParams(jsonNodes.path("factParams").toString(),
                        newStrain));
                newStrain.setFactParametrsFunc(new ArrayList<>());
                return newStrain;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Метод конвертации JSON строки в список {@link FactParametrEntity}
     *
     * @param json JSON строка
     * @return Список фиктических параметров
     */
    public List<FactParametrEntity> JsonToFactParams(String json, StrainEntity strain) {
        List<FactParametrEntity> factParams = new ArrayList<>();
        try {
            JsonNode factParamsJson = mapper.readTree(json);
            for (JsonNode property : factParamsJson)
                for (JsonNode subProp : property.path("subProps")) {
                    factParams.add(FactParametrEntity.builder()
                            .value(subProp.path("value").textValue())
                            .strain(strain)
                            .property(propertyRepository.findById(property.path("id").longValue()).get())
                            .subProperty(subPropertyRepository.findById(subProp.path("id").longValue()).get())
                            .build());
                }
            return factParams;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
