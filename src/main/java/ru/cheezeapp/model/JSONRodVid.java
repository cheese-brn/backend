package ru.cheezeapp.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JSONRodVid {
    private Long id;
    private String name;
    private int childrenCount;
}
