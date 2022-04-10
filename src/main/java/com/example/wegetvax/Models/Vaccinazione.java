package com.example.wegetvax.Models;

import java.time.LocalDate;

/**
 * Classe che definisce le caratteristiche di unq genericq Vaccinazione di un vaccinando.
 */
public class Vaccinazione {

    private String nomeVaccinando;
    private String cognomeVaccinando;
    private String codiceFiscale;
    private LocalDate dataDiNascita;
    private int numeroDose;
    private String nomeFarmaco;
    private String numeroLotto;
    private LocalDate dataScadenza;
    private String nomeMedico;
    private LocalDate dataSomministrazione;
    private String braccioInoculatore;
    private String nomeLuogo;

    //Costruttore privato. Non sarà lui ad istanziare un nuovo oggetto di tipo Vaccinazione.
    private Vaccinazione() {

    }

    /**
     * Classe inner statica (La richiamo senza che istanzio un oggetto del suo tipo). Essendo inner ha l'accesso a tutte le variabili d'istanza della classe esterna.
     * Ha un costruttore che sarà richiamato per istanziare una Vaccinazione.
     */
    public static class VaccinazioneBuilder {
        private String nomeVaccinando;
        private String cognomeVaccinando;
        private String codiceFiscale;
        private LocalDate dataDiNascita;
        private int numeroDose;
        private String nomeFarmaco;
        private String numeroLotto;
        private LocalDate dataScadenza;
        private String nomeMedico;
        private LocalDate dataSomministrazione;
        private String braccioInoculatore;
        private String nomeLuogo;

        /**
         * Costruttore che imposta i valori di default delle SUE variabili d'istanza, non di Vaccinazione.
         */
        public VaccinazioneBuilder() {
            this.nomeVaccinando=StrutturaAppoggio.getVaccinando().getNome();
            this.cognomeVaccinando=StrutturaAppoggio.getVaccinando().getCognome();
            this.codiceFiscale=StrutturaAppoggio.getVaccinando().getCodice_Fiscale();
            this.dataDiNascita=StrutturaAppoggio.getVaccinando().getData_di_Nascita();
            this.numeroDose=0;
            this.nomeFarmaco="";
            this.numeroLotto="";
            this.dataScadenza=null;
            this.nomeMedico=StrutturaAppoggio.getMedico().getNome() + " " + StrutturaAppoggio.getMedico().getCognome();
            this.dataSomministrazione=null;
            this.braccioInoculatore="";
            this.nomeLuogo="";
        }

        /**
         * Metodo che ritrona una VAccinazione costruendola sulla base dei valori settati con i metodi Setter.
         * @return
         */
        public Vaccinazione build() {
            Vaccinazione v = new Vaccinazione();
            v.nomeVaccinando=this.nomeVaccinando;
            v.cognomeVaccinando=this.cognomeVaccinando;
            v.codiceFiscale=this.codiceFiscale;
            v.dataDiNascita=this.dataDiNascita;
            v.numeroDose=this.numeroDose;
            v.nomeFarmaco=this.nomeFarmaco;
            v.numeroLotto=this.numeroLotto;
            v.dataScadenza=this.dataScadenza;
            v.nomeMedico=this.nomeMedico;
            v.dataSomministrazione=this.dataSomministrazione;
            v.braccioInoculatore=this.braccioInoculatore;
            v.nomeLuogo=this.nomeLuogo;
            return v;
        }

        // Metodi setter che ritornano una VaccinazioneBuilder
        public VaccinazioneBuilder setNumeroDose(int numeroDose) {
            this.numeroDose = numeroDose;
            return this;
        }

        public VaccinazioneBuilder setNomeFarmaco(String nomeFarmaco) {
            this.nomeFarmaco = nomeFarmaco;
            return this;
        }

        public VaccinazioneBuilder setNumeroLotto(String numeroLotto) {
            this.numeroLotto = numeroLotto;
            return this;

        }

        public VaccinazioneBuilder setDataScadenza(LocalDate dataScadenza) {
            this.dataScadenza = dataScadenza;
            return this;
        }

        public VaccinazioneBuilder setDataSomministrazione(LocalDate dataSomministrazione) {
            this.dataSomministrazione = dataSomministrazione;
            return this;
        }

        public VaccinazioneBuilder setBraccioInoculatore(String braccioInoculatore) {
            this.braccioInoculatore = braccioInoculatore;
            return this;
        }

        public VaccinazioneBuilder setNomeLuogo(String nomeLuogo) {
            this.nomeLuogo = nomeLuogo;
            return this;
        }
    }

    // Metodi setter che ritornano le caratteristiche di una Vaccinazione, dopo averla completamente creata.
    public String getNomeVaccinando() {
        return nomeVaccinando;
    }

    public String getCognomeVaccinando() {
        return cognomeVaccinando;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public int getNumeroDose() {
        return numeroDose;
    }

    public String getNomeFarmaco() {
        return nomeFarmaco;
    }

    public String getNumeroLotto() {
        return numeroLotto;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public LocalDate getDataSomministrazione() {
        return dataSomministrazione;
    }

    public String getBraccioInoculatore() {
        return braccioInoculatore;
    }

    public String getNomeLuogo() {
        return nomeLuogo;
    }
}
