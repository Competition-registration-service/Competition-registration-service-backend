package edu.vsu.sakovea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "name_of_educational_institution")
    private String nameOfEducationalInstitution;

    @Column(name = "access_code")
    private int accessCode;

    @Column(name = "name_of_living_place")
    private String nameOfLivingPlace;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "dom_region_id", referencedColumnName = "id")
    private Domain domainRegion;
}
