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
@Table(name = "dependency_table")
public class DependencyTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(targetEntity = FactParametrFuncEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "dependency_table_id", referencedColumnName = "id")
    List<FactParametrFuncEntity> factParametrsFunc;
}
