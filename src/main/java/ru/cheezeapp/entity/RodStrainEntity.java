package ru.cheezeapp.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "rod_strain")
public class RodStrainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Long cypher;

    @OneToMany(targetEntity = VidStrainEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "rod_id", referencedColumnName = "id")
    List<VidStrainEntity> vids;
}
