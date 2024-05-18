package edu.vsu.sakovea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.config.ConfigTreeConfigDataLoader;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "competition")
public class Competition {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "max_number_of_team_members")
    private int maxNumberOfTeamMembers;

    @Column(name = "min_number_of_team_members")
    private int minNumberOfTeamMembers;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    private UUID cid;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "dom_competition_id", referencedColumnName = "id")
    private Domain domainCompetition;

    @ManyToOne
    @JoinColumn(name = "dom_comp_count_id", referencedColumnName = "id")
    private Domain domainCompetitionCount;

    @ManyToOne
    @JoinColumn(name = "dom_comp_age_id", referencedColumnName = "id")
    private Domain domainCompetitionAge;
}
