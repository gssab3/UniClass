package it.unisa.uniclass.esami.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Prenotazione {
    @Id @GeneratedValue
    private long id;


}
