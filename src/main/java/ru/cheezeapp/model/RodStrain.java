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
public class RodStrain {
    Long id;
    String name;
    Long cypher;
    List<VidStrain> vidStrains;
}
