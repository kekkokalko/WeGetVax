package com.example.wegetvax.Models;

import java.time.LocalDate;

/**
 * La classe 'ConcretePrototypePrenotazione' implementa il metodo di clonazione. Possiede anche tutte le caratteristiche di una prenotazione generica.
 */
public class ConcretePrototypePrenotazione implements PrototypePrenotazione{

    private int identificativo;
    private String codice_fiscale;
    private LocalDate data_ora_prevista;
    private String luogo;
    private String citta;
    private String via;
    private int civico;
    private String provincia;
    private String asl;
    private int distretto;

    //Costruttore
    public ConcretePrototypePrenotazione(int identificativo, String codice_fiscale, LocalDate data_ora_prevista, String luogo, String citta, String via, int civico, String provincia, String asl, int distretto) {
        this.identificativo = identificativo;
        this.codice_fiscale = codice_fiscale;
        this.data_ora_prevista = data_ora_prevista;
        this.luogo = luogo;
        this.citta = citta;
        this.via = via;
        this.civico = civico;
        this.provincia = provincia;
        this.asl = asl;
        this.distretto = distretto;
    }

    //Metodi getter e setter
    public int getIdentificativo() {
        return identificativo;
    }

    public void setIdentificativo(int identificativo) {
        this.identificativo = identificativo;
    }

    public String getCodice_fiscale() {
        return codice_fiscale;
    }

    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }

    public LocalDate getData_ora_prevista() {
        return data_ora_prevista;
    }

    public void setData_ora_prevista(LocalDate data_ora_prevista) {
        this.data_ora_prevista = data_ora_prevista;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getAsl() {
        return asl;
    }

    public void setAsl(String asl) {
        this.asl = asl;
    }

    public int getDistretto() {
        return distretto;
    }

    public void setDistretto(int distretto) {
        this.distretto = distretto;
    }

    /**
     * Metodi di clonazione ereditato da 'PrototypePrenotazione'. Copia i dati dell'oggetto originale nel clone. Avuto il clone si possono richiamare i metodi setter
     * per differenziarlo dall'oggetto originale.
     * @return
     */
    @Override
    public ConcretePrototypePrenotazione clone() {
        return new ConcretePrototypePrenotazione(identificativo,codice_fiscale, data_ora_prevista, luogo, citta, via, civico, provincia, asl, distretto);
    }
}
