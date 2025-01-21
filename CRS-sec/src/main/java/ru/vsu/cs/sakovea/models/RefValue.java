package ru.vsu.cs.sakovea.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ref_value")
public class RefValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "domain_cid")
    private String domainCid;

    @Column(name = "value_cid")
    private String valueCid;

    @Column(name = "short_value")
    private String shortValue;

    @Column(name = "long_value")
    private String longValue;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "refComp")
    private List<Competition> competitionsTypes;

    @OneToMany(mappedBy = "refCompCount")
    private List<Competition> competitionsCounts;

    @OneToMany(mappedBy = "refCompAge")
    private List<Competition> competitionsAges;

    @OneToMany(mappedBy = "refPage")
    private List<Content> contentPages;

    @OneToMany(mappedBy = "refFormat")
    private List<Content> contentFormats;

    @OneToMany(mappedBy = "refLanguage")
    private List<Content> contentLanguages;

    @OneToMany(mappedBy = "refType")
    private List<Field> fields;

    @OneToMany(mappedBy = "refFile")
    private List<File> files;

    @OneToMany(mappedBy = "refEducation")
    private List<User> userEducations;

    @OneToMany(mappedBy = "refCourse")
    private List<User> userCourses;

    @OneToMany(mappedBy = "refRegion")
    private List<User> userRegions;

    @OneToMany(mappedBy = "refRole")
    private List<UserCompPerm> roles;

}
