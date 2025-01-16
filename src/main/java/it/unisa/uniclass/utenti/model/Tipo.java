package it.unisa.uniclass.utenti.model;

/**
 * Enumera i tipi di utenti che possono essere presenti nel sistema UniClass.
 * <p>
 * I tipi disponibili sono:
 * <ul>
 *   <li>{@link #Studente} - Rappresenta uno studente universitario.</li>
 *   <li>{@link #Docente} - Rappresenta un docente universitario.</li>
 *   <li>{@link #Coordinatore} - Rappresenta il coordinatore di un corso o dipartimento.</li>
 *   <li>{@link #PersonaleTA} - Rappresenta il personale tecnico-amministrativo.</li>
 * </ul>
 * </p>
 */
public enum Tipo {
    /**
     * Rappresenta uno studente universitario.
     */
    Studente,
    /**
     * Rappresenta un docente universitario.
     */
    Docente,
    /**
     * Rappresenta il coordinatore di un corso o dipartimento.
     */
    Coordinatore,
    /**
     * Rappresenta il personale tecnico-amministrativo.
     */
    PersonaleTA
}
