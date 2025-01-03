package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

@Entity
@Table(name = "anni")
public class AnnoDidattico {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "corso")
}
