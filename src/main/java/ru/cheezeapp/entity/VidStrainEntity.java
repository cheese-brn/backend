package ru.cheezeapp.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "vid_strain")
public class VidStrainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Long cypher;

    @OneToMany(targetEntity = StrainEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "vid_id", referencedColumnName = "id")
    List<StrainEntity> strains;
}
