package ru.cheezeapp.model;

import ru.cheezeapp.entity.FactParametr;
import ru.cheezeapp.entity.SubProperty;

import java.util.List;

public class PropertyModel {

    Long id;

    String name;

    Long cypher;

    Boolean propertyType;

    String description;

    List<FactParametr> factParametrs;

    List<SubProperty> subProperties;

}
