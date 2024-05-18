package edu.vsu.sakovea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nickname;

    private String name;

    private String surname;

    private String phone;

    private String password;

    private String fullEducationalInstitution;

    private String shortEducationalInstitution;

    @Column(name = "name_of_living_place")
    private String nameOfLivingPlace;

    @ManyToOne
    @JoinColumn(name = "field_value_id", referencedColumnName = "id")
    private FieldValue fieldValue;

    @ManyToOne
    @JoinColumn(name = "dom_education_id", referencedColumnName = "id")
    private Domain domainEducation;

    @ManyToOne
    @JoinColumn(name = "dom_course_id", referencedColumnName = "id")
    private Domain domainCourse;

    @ManyToOne
    @JoinColumn(name = "dom_region_id", referencedColumnName = "id")
    private Domain domainRegion;
}
