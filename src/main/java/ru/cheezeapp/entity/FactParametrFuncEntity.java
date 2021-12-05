package ru.cheezeapp.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Сущность фактического параметра функции
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "fact_param_func")
public class FactParametrFuncEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Первый параметр
     */
    @Column(name = "first_parametr")
    String firstParametr;

    /**
     * Второй параметр
     */
    @Column(name = "second_parametr")
    String secondParametr;

    /**
     * Третий параметр
     */
    @Column(name = "third_parametr")
    String thirdParametr;

    /**
     * Внешний ключ на сущность {@link StrainEntity}
     */
    @ManyToOne
    @JoinColumn(name = "strain_id", nullable = false)
    StrainEntity strain;

    /**
     * Внешний ключ на сущность {@link DependencyTableEntity}
     */
    @ManyToOne
    @JoinColumn(name = "dependency_table_id", nullable = false)
    DependencyTableEntity dependencyTable;

}
