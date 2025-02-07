package ru.vsu.cs.sakovea.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competition")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_number_of_team_members")
    private int maxNumOfTeamMem;

    @Column(name = "min_number_of_team_members")
    private int minNumOfTeamMem;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "cid")
    private String cid;

    @Column(name = "competition_content")
    private String competitionContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Competition parent;

    @OneToMany(mappedBy = "parent")
    private List<Competition> competitions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_comp_id")
    private RefValue refComp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_comp_count_id")
    private RefValue refCompCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_comp_age_id")
    private RefValue refCompAge;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Content> contents;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Contestant> contestants;

    @OneToMany(mappedBy = "competition",fetch = FetchType.LAZY)
    private List<Field> fields;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<File> files;

    @OneToMany(mappedBy = "competition",fetch = FetchType.LAZY)
    private List<Team> teams;

    @OneToMany(mappedBy = "competition",fetch = FetchType.LAZY)
    private List<UserCompPerm> roles;
}
