package com.example.wegetvax.Models;

/**
 * Classe che definisce le caratteristiche e le funzionalità di un Medico. Tale classe implemeneta l'interfaccia 'Utente'.
 */
public class Medico extends Utente{

    protected String nome;
    protected String cognome;

    protected String username;
    protected String password;

    private String codice_ordine_dei_medici;

    //Costruttore
    public Medico(String nome, String cognome, String username, String password, String codice_ordine_dei_medici) {
        this.nome=nome;
        this.cognome=cognome;
        this.username=username;
        this.password=password;
        this.codice_ordine_dei_medici = codice_ordine_dei_medici;
    }

    //Metodi Getter
    public String getCodice_Ordine_dei_Medici() {
        return codice_ordine_dei_medici;
    }

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

    public void setCodice_ordine_dei_medici(String codice_ordine_dei_medici) {
        this.codice_ordine_dei_medici = codice_ordine_dei_medici;
    }
}
