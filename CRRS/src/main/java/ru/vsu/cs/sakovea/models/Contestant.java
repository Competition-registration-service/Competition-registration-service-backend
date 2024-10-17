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
@Table(name = "contestant")
public class Contestant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surename")
    private boolean surename;

    @Column(name = "thirdname")
    private String thirdname;

    @Column(name = "nickname")
    private String nickname;

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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @OneToMany(mappedBy = "contestant")
    private List<FieldValue> fieldValues;

    @OneToMany(mappedBy = "creator")
    private List<Team> teams;
}
