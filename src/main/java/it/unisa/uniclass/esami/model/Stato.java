package it.unisa.uniclass.esami.model;

/**
 * Enum rappresentante lo stato di un'entità, con due valori possibili:
 * <ul>
 *  <li>{@link #APERTO}: Indica che l'entità è aperta. </li>
 *  <li>{@link #CHIUSO}: Indica che l'entità è chiusa. </li>
 * </ul>
 * Questa enumerazione può essere utilizzata per rappresentare lo stato di esami, sessioni o altre entità.
 * */
public enum Stato {
    /**
     * Indica che l'entità è aperta
     * */
    APERTO,
    /**
     * Indica che l'entità è chiusa
     * */
    CHIUSO
}
