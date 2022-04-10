package com.example.wegetvax.Models;

import java.time.*;

/**
 * Classe che definisce le caratteristiche e le funzionalità di un Vaccinando. Tale classe implemeneta l'interfaccia 'Utente'.
 */
public class Vaccinando extends Utente {

    protected String nome;
    private String cognome;
    private String username;
    private String password;
    private LocalDate data_di_nascita;
    private String citta_natale;
    private String citta;
    private String provincia;
    private String CAP;
    private String codice_fiscale;
    private String tessera_sanitaria;
    private String mail;
    private String numero_telefono;


    //Costruttore
    public Vaccinando(String nome, String cognome, LocalDate data_di_nascita, String citta_natale,
                      String citta, String provincia, String CAP, String codice_fiscale, String tessera_sanitaria,
                      String username, String password, String mail, String numero_telefono) {
        this.nome=nome;
        this.cognome=cognome;
        this.data_di_nascita = data_di_nascita;
        this.citta_natale = citta_natale;
        this.citta = citta;
        this.provincia = provincia;
        this.CAP = CAP;
        this.codice_fiscale = codice_fiscale;
        this.tessera_sanitaria = tessera_sanitaria;
        this.username=username;
        this.password=password;
        this.mail = mail;
        this.numero_telefono=numero_telefono;
    }

    //Metodi Getter
    public LocalDate getData_di_Nascita() {
        return data_di_nascita;
    }

    public String getCitta_Natale() {
        return citta_natale;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCAP() {
        return CAP;
    }

    public String getCodice_Fiscale() {
        return codice_fiscale;
    }

    public String getTessera_Sanitaria() {
        return tessera_sanitaria;
    }

    public String getMail() {
        return mail;
    }

    public String getNumero_telefono(){
        return numero_telefono;
    }


    //Metodi Setter
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setData_di_nascita(LocalDate data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public void setCitta_natale(String citta_natale) {
        this.citta_natale = citta_natale;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }

    public void setTessera_sanitaria(String tessera_sanitaria) {
        this.tessera_sanitaria = tessera_sanitaria;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }


    //Metodi ereditati da Utente
    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getCognome() {
        return this.cognome;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
