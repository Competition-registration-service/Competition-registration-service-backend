package edu.vsu.sakovea.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

public class Content {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "dom_page_id", referencedColumnName = "id")
    private Domain domainPage;

    @ManyToOne
    @JoinColumn(name = "dom_format_id", referencedColumnName = "id")
    private Domain domainFormat;

    @ManyToOne
    @JoinColumn(name = "dom_language_id", referencedColumnName = "id")
    private Domain domainLanguage;


}
