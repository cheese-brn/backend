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
public class RodStrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Long cypher;

    @OneToMany(targetEntity = VidStrain.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "rod_id", referencedColumnName = "id")
    List<VidStrain> vids;
}
