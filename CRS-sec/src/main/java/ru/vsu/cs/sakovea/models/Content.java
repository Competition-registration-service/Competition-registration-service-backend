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
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "ref_page_id", referencedColumnName = "id")
    private RefValue refPage;

    @ManyToOne
    @JoinColumn(name = "ref_format_id", referencedColumnName = "id")
    private RefValue refFormat;

    @ManyToOne
    @JoinColumn(name = "ref_language_id", referencedColumnName = "id")
    private RefValue refLanguage;
}
