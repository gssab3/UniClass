# UniClass

UniClass è una piattaforma modulare per la gestione dell'ambiente accademico universitario, progettata per fornire servizi come la visione degli orari delle lezioni, la gestione delle aule, e la gestione degli utenti. Il sistema è progettato per essere facilmente estendibile e personalizzabile, offrendo una gestione centralizzata e una facile integrazione con altri strumenti accademici.

## Funzionalità principali

- **Visione degli orari delle lezioni**: Gli studenti, i docenti e il personale amministrativo possono visualizzare gli orari delle lezioni aggiornati in tempo reale.
- **Gestione delle aule**: Permette di visualizzare la disponibilità delle aule, facilitando la pianificazione delle lezioni e degli eventi.
- **Gestione degli utenti**: Permette l'aggiunta e la gestione degli utenti (studenti, docenti, personale amministrativo) attraverso una piattaforma modulare che consente di personalizzare facilmente i ruoli e i permessi.
- **Sistema modulare**: La piattaforma è progettata con un'architettura modulare che consente di aggiungere nuove funzionalità in modo facile e rapido.

## Architettura

UniClass è costruita con una serie di moduli separati che coprono le diverse funzionalità del sistema. Ogni modulo interagisce con il database per garantire la coerenza e la sincronizzazione dei dati.

### Moduli principali

1. **Gestione Orari**:
    - Permette di visualizzare, aggiungere e aggiornare gli orari delle lezioni.
    - Supporta la visualizzazione per giorno, settimana o mese.

2. **Gestione Aule**:
    - Gestisce la disponibilità delle aule per le lezioni e gli eventi.
    - Permette di verificare la disponibilità in tempo reale.

3. **Gestione Utenti**:
    - Gestisce studenti, docenti e personale amministrativo.
    - Permette la creazione e la gestione di utenti con ruoli specifici.

4. **Interfaccia Web**:
    - Un'interfaccia utente accessibile via browser per la visualizzazione delle informazioni e l'interazione con il sistema.


## Installazione

Per iniziare a utilizzare UniClass, segui questi passaggi:

### Prerequisiti

- jdk: 21
- Server database (in questo caso PostgreSQL)
- Un application server (tomEE 10.0.0)
- Maven per la gestione delle dipendenze