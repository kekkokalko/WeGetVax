package com.example.wegetvax.Models;

import java.time.LocalDate;
import com.example.wegetvax.Models.*;


/**
 * Classe che estente 'AbstractFactoryRegistrazione' e che da' un'implementazione ai metodi ereditati.
 */
public class ConcreteFactoryRegistrazione extends AbstractFactoryRegistrazione{

    /**
     * Metodo che ritorna un Utente ma, nel preciso, è un vaccinando. Questo fa si che non ci sia necessità da parte dei client di specificare i nomi
     * delle classi concrete all'interno del proprio codice. I parametri sono tutte le caratteristiche di un generico vaccinando.
     * @param nome
     * @param cognome
     * @param data_di_nascita
     * @param citta_natale
     * @param citta
     * @param provincia
     * @param CAP
     * @param codice_fiscale
     * @param tessera_sanitaria
     * @param username
     * @param password
     * @param mail
     * @param numero_telefono
     * @return Un utente Vaccinando.
     */
    @Override
    public Utente createVaccinando(String nome, String cognome, LocalDate data_di_nascita, String citta_natale,
                                       String citta, String provincia, String CAP, String codice_fiscale, String tessera_sanitaria,
                                       String username, String password, String mail,String numero_telefono) {
        return new Vaccinando (nome, cognome, data_di_nascita, citta_natale, citta, provincia, CAP, codice_fiscale, tessera_sanitaria,
                username, password, mail, numero_telefono);
    }

     /**
     * Metodo che ritorna un Utente ma, nel preciso, è un medico. Questo fa si che non ci sia necessità da parte dei client di specificare i nomi
     * delle classi concrete all'interno del proprio codice. I parametri sono tutte le caratteristiche di un generico medico.
     * @param nome
     * @param cognome
     * @param username
     * @param password
     * @param codice_ordine_dei_medici
     * @return Un utente medico.
     */
    @Override
    public Utente createMedico(String nome, String cognome, String username, String password, String codice_ordine_dei_medici) {
        return new Medico(nome, cognome, username, password, codice_ordine_dei_medici);
    }
}
