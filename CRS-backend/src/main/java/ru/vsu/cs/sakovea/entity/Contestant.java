package edu.vsu.sakovea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contestant")
public class Contestant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private String phone;

    private String email;

    @Column(name = "mentor_name")
    private String mentorName;

    @Column(name = "mentor_phone")
    private String mentorPhone;

    @Column(name = "mentor_email")
    private String mentorEmail;
}
