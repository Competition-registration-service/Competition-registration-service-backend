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
    private int domainCid;

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

    @OneToMany(mappedBy = "refPage")
    private List<Content> contentPages;

    @OneToMany(mappedBy = "refFormat")
    private List<Content> contentFormats;

    @OneToMany(mappedBy = "refLanguage")
    private List<Content> contentLanguages;

    @OneToMany(mappedBy = "refCompetition")
    private List<Competition> competitions;

    @OneToMany(mappedBy = "refCompCount")
    private List<Competition> compCounts;

    @OneToMany(mappedBy = "refCompAge")
    private List<Competition> compAges;

    @OneToMany(mappedBy = "refFile")
    private List<File> files;

    @OneToMany(mappedBy = "refRole")
    private List<UserCompPerm> roles;

    @OneToMany(mappedBy = "refType")
    private List<Field> fields;

    @OneToMany(mappedBy = "refEducation")
    private List<User> userEducations;

    @OneToMany(mappedBy = "refCourse")
    private List<User> userCourses;

    @OneToMany(mappedBy = "refRegion")
    private List<User> userRegions;
}
