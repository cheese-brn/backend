package ru.cheezeapp.model;

import ru.cheezeapp.entity.DependencyTable;

import java.util.List;

public class SubPropertyModel {

    Long id;

    String name;

    List<DependencyTable> firstSubproperty;

    List<DependencyTable> secondSubproperty;

}
