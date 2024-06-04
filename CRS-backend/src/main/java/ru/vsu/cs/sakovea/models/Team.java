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
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "access_code")
    private String  accessCode;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "change_date")
    private Date changeDate;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Contestant creator;

    @OneToMany(mappedBy = "team")
    private List<Contestant> contestants;

    @OneToMany(mappedBy = "team")
    private List<FieldValue> fieldValues;
}
