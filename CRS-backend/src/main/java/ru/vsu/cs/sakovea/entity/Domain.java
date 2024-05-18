package edu.vsu.sakovea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "domains")
public class Domain {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String domain;

    @Column(name = "value_cid")
    private String valueCID;

    @Column(name = "value_short")
    private String shortValue;

    @Column(name = "value_long")
    private String longValue;

    private String comment;
}
