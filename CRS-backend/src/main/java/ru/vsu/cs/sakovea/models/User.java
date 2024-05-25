package ru.vsu.cs.sakovea.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surename")
    private boolean surename;

    @Column(name = "thirdname")
    private String thirdname;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "vk")
    private String vk;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "change_date")
    private Date changeDate;

    @ManyToOne
    @JoinColumn(name = "ref_education_id", referencedColumnName = "id")
    private RefValue refEducation;

    @ManyToOne
    @JoinColumn(name = "ref_course_id", referencedColumnName = "id")
    private RefValue refCourse;

    @ManyToOne
    @JoinColumn(name = "ref_region_id", referencedColumnName = "id")
    private RefValue refRegion;

    @OneToMany(mappedBy = "user")
    private List<Contestant> contestants;

    @OneToMany(mappedBy = "user")
    private List<UserCompPerm> userCompPerms;


}
