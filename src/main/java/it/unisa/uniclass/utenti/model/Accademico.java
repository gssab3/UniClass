package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Accademico extends Utente {

    @Id
    protected String matricola;
    protected LocalDate iscrizione;
    @OneToOne
    @JoinColumn(name = "corso_laurea_id")
    protected CorsoLaurea corsoLaurea;

    public Accademico() {}

    public LocalDate getIscrizione() {
        return iscrizione;
    }

    public void setIscrizione(LocalDate iscrizione) {
        this.iscrizione = iscrizione;
    }

    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}
