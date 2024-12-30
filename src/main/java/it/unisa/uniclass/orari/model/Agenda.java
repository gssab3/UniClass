package it.unisa.uniclass.orari.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Agenda {
    @Id @GeneratedValue
    private int id;
}
