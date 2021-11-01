package ru.cheezeapp.model;

import ru.cheezeapp.entity.FactParametr;
import ru.cheezeapp.entity.FactParametrFunc;

import java.util.List;

public class StrainModel {

    Long id;

    String exemplar;

    String modification;

    String obtainingMethod;

    String origin;

    List<FactParametr> factParametrs;

    List<FactParametrFunc> factParametrsFunc;

    String annotation;

}
