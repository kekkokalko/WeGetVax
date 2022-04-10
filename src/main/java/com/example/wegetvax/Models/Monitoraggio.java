package com.example.wegetvax.Models;

import java.time.LocalDate;

/**
 * Classe che definisce le caratteristiche di un generico Monitoraggio di un vaccinando.
 */
public class Monitoraggio {

    private String codice_fiscale;
    private LocalDate data;
    private int temperatura;
    private String malDiGola;
    private String malDiTesta;
    private String doloriMuscolari;
    private String nausea;
    private String situazionePsicologica;
    private String tosse;
    private String respirazione;
    private int frequenzaCardiaca;

    //Costruttore privato. Non sarà lui ad istanziare un nuovo oggetto di tipo Monitorggio.
    private Monitoraggio() {

    }

    /**
     * Classe inner statica (La richiamo senza che istanzio un oggetto del suo tipo). Essendo inner ha l'accesso a tutte le variabili d'istanza della classe esterna.
     * Ha un costruttore che sarà richiamato per istanziare un Monitoraggio.
     */
    public static class MonitoraggioBuilder {
        private String codice_fiscale;
        private LocalDate data;
        private int temperatura;
        private String malDiGola;
        private String malDiTesta;
        private String doloriMuscolari;
        private String nausea;
        private String situazionePsicologica;
        private String tosse;
        private String respirazione;
        private int frequenzaCardiaca;

        /**
         * Costruttore che imposta i valori di default delle SUE variabili d'istanza, non di Monitoragio.
         */
        public MonitoraggioBuilder() {
            this.codice_fiscale = StrutturaAppoggio.getVaccinando().getCodice_Fiscale();
            this.data = null;
            this.temperatura = 0;
            this.malDiGola = "";
            this.malDiTesta = "";
            this.doloriMuscolari = "";
            this.nausea = "";
            this.situazionePsicologica = "";
            this.tosse = "";
            this.respirazione = "";
            this.frequenzaCardiaca = 0;
        }

        /**
         * Metodo che ritrona un Monitoraggio costruendolo sulla base dei valori settati con i metodi Setter.
          * @return
         */
        public Monitoraggio build() {
            Monitoraggio m = new Monitoraggio();
            m.codice_fiscale=this.codice_fiscale;
            m.data=this.data;
            m.temperatura=this.temperatura;
            m.malDiGola=this.malDiGola;
            m.malDiTesta=this.malDiTesta;
            m.doloriMuscolari=this.doloriMuscolari;
            m.nausea=this.nausea;
            m.situazionePsicologica=this.situazionePsicologica;
            m.tosse=this.tosse;
            m.respirazione=this.respirazione;
            m.frequenzaCardiaca=this.frequenzaCardiaca;
            return m;
        }

        // Metodi setter che ritornano una VaccinazioneBuilder
        public MonitoraggioBuilder setData(LocalDate data) {
            this.data = data;
            return this;
        }

        public MonitoraggioBuilder setTemperatura(int temperatura) {
            this.temperatura = temperatura;
            return this;
        }

        public MonitoraggioBuilder setMalDiGola(String malDiGola) {
            this.malDiGola = malDiGola;
            return this;
        }

        public MonitoraggioBuilder setMalDiTesta(String malDiTesta) {
            this.malDiTesta = malDiTesta;
            return this;
        }

        public MonitoraggioBuilder setDoloriMuscolari(String doloriMuscolari) {
            this.doloriMuscolari = doloriMuscolari;
            return this;
        }

        public MonitoraggioBuilder setNausea(String nausea) {
            this.nausea = nausea;
            return this;
        }

        public MonitoraggioBuilder setSituazionePsicologica(String situazionePsicologica) {
            this.situazionePsicologica = situazionePsicologica;
            return this;
        }

        public MonitoraggioBuilder setTosse(String tosse) {
            this.tosse = tosse;
            return this;
        }

        public MonitoraggioBuilder setRespirazione(String respirazione) {
            this.respirazione = respirazione;
            return this;
        }

        public MonitoraggioBuilder setFrequenzaCardiaca(int frequenzaCardiaca) {
            this.frequenzaCardiaca = frequenzaCardiaca;
            return this;
        }
    }

    // Metodi setter che ritornano le caratteristiche di un Monitoraggio, dopo averla completamente creata.
    public String getCodice_fiscale() {return codice_fiscale;}

    public LocalDate getData() {
        return data;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public String getMalDiGola() {
        return malDiGola;
    }

    public String getMalDiTesta() {
        return malDiTesta;
    }

    public String getDoloriMuscolari() {
        return doloriMuscolari;
    }

    public String getNausea() {
        return nausea;
    }

    public String getSituazionePsicologica() {
        return situazionePsicologica;
    }

    public String getTosse() {
        return tosse;
    }

    public String getRespirazione() {
        return respirazione;
    }

    public int getFrequenzaCardiaca() {
        return frequenzaCardiaca;
    }
}
