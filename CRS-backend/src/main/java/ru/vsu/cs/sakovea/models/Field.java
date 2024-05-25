package ru.vsu.cs.sakovea.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "field")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "select_domain_cid")
    private String selectDomainCid;

    @Column(name = "team_field")
    private boolean teamField;

    @Column(name = "cid")
    private String cid;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "long_name")
    private String longName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "example_value")
    private String exampleValue;

    @Column(name = "max_length")
    private int maxLength;

    @Column(name = "order")
    private int order;

    @Column(name = "optional")
    private boolean optional;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "ref_type_id", referencedColumnName = "id")
    private RefValue refType;
}
