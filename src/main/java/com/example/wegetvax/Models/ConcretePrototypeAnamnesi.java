package com.example.wegetvax.Models;

import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * La classe 'ConcretePrototypeAnamnesi' implementa il metodo di clonazione. Possiede anche tutte le caratteristiche di un'anamnesi generica.
 */
public class ConcretePrototypeAnamnesi implements PrototypeAnamnesi{

    private int numero_protocollo;
    private String codice_fiscale;
    private LocalDate data_ora_controllo;
    private String luogo;
    private String esito;
    private String nome_medico;

    //Costruttore
    public ConcretePrototypeAnamnesi(int numero_protocollo,String codice_fiscale, LocalDate data_ora_controllo, String luogo, String esito, String nome_medico) {
        this.numero_protocollo = numero_protocollo;
        this.codice_fiscale=codice_fiscale;
        this.data_ora_controllo = data_ora_controllo;
        this.luogo = luogo;
        this.esito = esito;
        this.nome_medico = nome_medico;
    }

    //Metodi getter e setter
    public int getNumero_protocollo() {
        return this.numero_protocollo;
    }

    public void setNumero_protocollo(int numero_protocollo) {
        this.numero_protocollo = numero_protocollo;
    }

    public String getCodice_fiscale() {
        return this.codice_fiscale;
    }

    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }

    public LocalDate getData_ora_controllo() {
        return this.data_ora_controllo;
    }

    public void setData_ora_controllo(LocalDate data_ora_controllo) {
        this.data_ora_controllo = data_ora_controllo;
    }

    public String getLuogo() {
        return this.luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public String getNome_medico() {
        return this.nome_medico;
    }

    public void setNome_medico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    /**
     * Metodi di clonazione ereditato da 'PrototypeAnamnesi'. Copia i dati dell'oggetto originale nel clone. Avuto il clone si possono richiamare i metodi setter
     * per differenziarlo dall'oggetto originale.
     * @return
     */
    @Override
    public ConcretePrototypeAnamnesi clone() {
            return new ConcretePrototypeAnamnesi(numero_protocollo, codice_fiscale, data_ora_controllo, luogo, esito, nome_medico);
    }
}
