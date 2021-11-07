package ru.cheezeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Strain {
    Long id;
    String exemplar;
    String modification;
    String obtainingMethod;
    String origin;
    List<Property> properties;
    String annotation;
}
