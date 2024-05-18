package edu.vsu.sakovea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "field")
public class Field {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean teamField;

    private UUID cid; // такой ли тип здесь или другой?

    @Column(name = "name_short")
    private String shortName;

    @Column(name = "name_long")
    private String longName;

    private String comment;

    private String exampleValue;

    private int maxLenght;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "dom_count_id", referencedColumnName = "id")
    private Domain domainCount;

    @ManyToOne
    @JoinColumn(name = "dom_date_type_id", referencedColumnName = "id")
    private Domain domainDateType;

    @ManyToOne
    @JoinColumn(name = "field_value_id", referencedColumnName = "id")
    private FieldValue fieldValue;

}
