package it.unisa.uniclass.utenti.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "coordinatori")
public class Coordinatore extends Docente implements Serializable {
    public Coordinatore() {super(); tipo = Tipo.Coordinatore;}
}
