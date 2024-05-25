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
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "cid")
    private String cid;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Competition parent;

    @ManyToOne
    @JoinColumn(name = "ref_competition_id", referencedColumnName = "id")
    private RefValue refCompetition;

    @ManyToOne
    @JoinColumn(name = "ref_comp_count_id", referencedColumnName = "id")
    private RefValue refCompCount;

    @ManyToOne
    @JoinColumn(name = "ref_comp_age_id", referencedColumnName = "id")
    private RefValue refCompAge;

    @OneToMany(mappedBy = "competition")
    private List<Content> contents;

    @OneToMany(mappedBy = "competition")
    private List<File> files;

    @OneToMany(mappedBy = "competition")
    private List<Team> teams;

    @OneToMany(mappedBy = "competition")
    private List<UserCompPerm> userCompPerms;

    @OneToMany(mappedBy = "competition")
    private List<Field> fields;

    @OneToMany(mappedBy = "competition")
    private List<Contestant> contestants;

    @OneToMany(mappedBy = "parent")
    private List<Competition> competitions;
}
