package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

import static it.unisa.uniclass.orari.model.AnnoDidattico.TROVA_ANNI_CORSO;

@Entity
@Table(name = "anni")
@NamedQueries({
        @NamedQuery(name = TROVA_ANNI_CORSO, query = "SELECT a FROM AnnoDidattico a WHERE a.corsoLaurea.nome = :nome")
})
public class AnnoDidattico {

    public static final String TROVA_ANNI_CORSO = "AnnoDidattico.trovaAnniCorso";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String anno; //Esempio: "Anno 1", "Anno 2", ecc..

    @ManyToOne
    @JoinColumn(name = "corso_laurea_id", nullable = false)
    private CorsoLaurea corsoLaurea;

    public AnnoDidattico(String anno, CorsoLaurea corsoLaurea){
        this.anno = anno;
        this.corsoLaurea = corsoLaurea;
    }

    public AnnoDidattico(){

    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public int getId() {
        return id;
    }

    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }
}
