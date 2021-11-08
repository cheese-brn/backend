package ru.cheezeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "data_type")
public class DataTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @OneToMany(targetEntity = SubPropertyEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "datatype_id", referencedColumnName = "id")
    List<SubPropertyEntity> subProperties;
}
