package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.orari.model.AnnoDidattico.*;

@Entity
@Table(name = "anni")
@NamedQueries({
        @NamedQuery(name = TROVA_ANNO, query = "SELECT a FROM AnnoDidattico a WHERE a.anno = :anno"),
        @NamedQuery(name = TROVA_ID, query = "SELECT a FROM AnnoDidattico a WHERE a.id = :id"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT a FROM AnnoDidattico a")
})
public class AnnoDidattico implements Serializable {

    public static final String TROVA_ANNO = "AnnoDidattico.trovaAnno";
    public static final String TROVA_ID = "AnnoDidattico.trovaId";
    public static final String TROVA_TUTTI = "AnnoDidattico.trovaTutti";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String anno; //Esempio: "Anno 1", "Anno 2", ecc..

    @ManyToMany(mappedBy = "anniDidattici")
    private List<CorsoLaurea> corsiLaurea = new ArrayList<>();

    public AnnoDidattico(String anno){
        this.anno = anno;
    }

    public AnnoDidattico(){}

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public int getId() {
        return id;
    }

    public List<CorsoLaurea> getCorsiLaurea() {
        return corsiLaurea;
    }

    public void setCorsiLaurea(List<CorsoLaurea> corsiLaurea) {
        this.corsiLaurea = corsiLaurea;
    }

    @Override
    public String toString() {
        return "AnnoDidattico{" +
                "id=" + id +
                ", anno='" + anno + '\'' +
                '}';
    }
}
