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
@Table(name = "subproperty")
public class SubProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @OneToMany(targetEntity = DependencyTable.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "first_subproperty_id", referencedColumnName = "id")
    List<DependencyTable> firstSubproperty;

    @OneToMany(targetEntity = DependencyTable.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "second_subproperty_id", referencedColumnName = "id")
    List<DependencyTable> secondSubproperty;
}
