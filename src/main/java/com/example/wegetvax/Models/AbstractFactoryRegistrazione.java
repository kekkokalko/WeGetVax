package com.example.wegetvax.Models;

import java.time.LocalDate;

/**
 * Classe astratta che permette di creare una famiglia di oggetti connessi o dipendenti ma dievrsi tra di loro: Vaccinandi e Medici. Questo mediante la macro operazione
 * di registrazione dell'utente in piattaforma. Ha 2 metodi pubblici e astratti che ritornano un Utente.
 */
public abstract class AbstractFactoryRegistrazione {

    public abstract Utente createVaccinando(String nome, String cognome, LocalDate data_di_nascita, String citta_natale,
                                                String citta, String provincia, String CAP, String codice_fiscale, String tessera_sanitaria,
                                                String username, String password, String mail, String numero_telefono);
    public abstract Utente createMedico(String nome, String cognome, String username, String password, String codice_ordine_dei_medici);
}