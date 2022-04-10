package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * RegistrazioneController è la classe che definisce il controller che gestisce l'operazione di registrazione di un utente.
 */
public class RegistrazioneController {

    @FXML
    private TextField CAP;

    @FXML
    private Label campi_mancanti;

    @FXML
    private TextField citta;

    @FXML
    private TextField citta_natale;

    @FXML
    private TextField codice_fiscale;

    @FXML
    private TextField codice_ordine_dei_medici;

    @FXML
    private TextField cognome;

    @FXML
    private TextField cognome1;

    @FXML
    private PasswordField conferma_password;

    @FXML
    private PasswordField conferma_password1;

    @FXML
    private Label corretto;

    @FXML
    private DatePicker data_di_nascita;

    @FXML
    private Label data_errata;

    @FXML
    private Label error_message_ruolo;

    @FXML
    private TextField mail;

    @FXML
    private Label mail_scorretta;

    @FXML
    private TextField nome;

    @FXML
    private TextField nome1;

    @FXML
    private TextField numero_di_telefono;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private Label password_message;

    @FXML
    private Label password_message1;

    @FXML
    private TextField provincia;

    @FXML
    private RadioButton ruolo1;

    @FXML
    private RadioButton ruolo2;

    @FXML
    private TextField tessera_sanitaria;

    @FXML
    private TextField username;

    @FXML
    private TextField username1;

    private ToggleGroup button_group;

    public RegistrazioneController()
    {
    }

    /**
     * Metodo che permette di disattivare i campi non richiesti per la registrazione di un vaccinando.
     * @param event
     * @throws IOException
     */
    public void selezionaRuolo1(ActionEvent event) throws IOException {

        button_group= new ToggleGroup();
        this.ruolo1.setToggleGroup(button_group);
        this.ruolo2.setToggleGroup(button_group);

        if(ruolo1.isSelected()) {
            nome1.setDisable(true);
            cognome1.setDisable(true);
            codice_ordine_dei_medici.setDisable(true);
            username1.setDisable(true);
            password1.setDisable(true);
            conferma_password1.setDisable(true);
            nome.setDisable(false);
            cognome.setDisable(false);
            data_di_nascita.setDisable(false);
            citta.setDisable(false);
            citta_natale.setDisable(false);
            provincia.setDisable(false);
            CAP.setDisable(false);
            codice_fiscale.setDisable(false);
            tessera_sanitaria.setDisable(false);
            mail.setDisable(false);
            numero_di_telefono.setDisable(false);
            username.setDisable(false);
            password.setDisable(false);
            conferma_password.setDisable(false);
        }
    }

    /**
     * Metodo che permette di disattivare i campi non richiesti per la registrazione di un medico.
     * @param event
     * @throws IOException
     */
    public void selezionaRuolo2(ActionEvent event) throws IOException {
        if(ruolo2.isSelected()){
            nome1.setDisable(false);
            cognome1.setDisable(false);
            codice_ordine_dei_medici.setDisable(false);
            username1.setDisable(false);
            password1.setDisable(false);
            conferma_password1.setDisable(false);
            nome.setDisable(true);
            cognome.setDisable(true);
            data_di_nascita.setDisable(true);
            citta.setDisable(true);
            citta_natale.setDisable(true);
            provincia.setDisable(true);
            CAP.setDisable(true);
            codice_fiscale.setDisable(true);
            tessera_sanitaria.setDisable(true);
            mail.setDisable(true);
            numero_di_telefono.setDisable(true);
            username.setDisable(true);
            password.setDisable(true);
            conferma_password.setDisable(true);
        }
    }

    /**
     * Metodo che permette di controllare che tutti i campi richiesti per effettuare la vaccinazione siano stati compilati. Dopodichè si richiama il metodo 'registrazione'.
     * @param event
     * @throws IOException
     */
    public void registrati(ActionEvent event) throws IOException {
        if (!ruolo1.isSelected() && !ruolo2.isSelected())
            error_message_ruolo.setText("Selezionare un ruolo!");
        else if(ruolo1.isSelected()) {
            error_message_ruolo.setText("");
            if (!nome.getText().isBlank() && !cognome.getText().isBlank() && !data_di_nascita.getValue().toString().isBlank() && !citta_natale.getText().isBlank()
                    && !citta.getText().isBlank() && !provincia.getText().isBlank() && !CAP.getText().isBlank() && !codice_fiscale.getText().isBlank() && !tessera_sanitaria.getText().isBlank()
                    && !mail.getText().isBlank() && !numero_di_telefono.getText().isBlank() &&!username.getText().isBlank() && !password.getText().isBlank() &&
                    !conferma_password.getText().isBlank()) {
                LocalDate today = LocalDate.now();
                LocalDate date = data_di_nascita.getValue();
                if(date.isAfter(today))
                    data_errata.setText("Inserisci una data precedente alla data attuale!");
                else
                    registrazione();
            }
            else {
                campi_mancanti.setText("Attenzione! Compilare tutti i campi!");
                password_message.setText("");
                corretto.setText("");
            }
        }
        else if(ruolo2.isSelected())
        {
            error_message_ruolo.setText("");
            if (!nome1.getText().isBlank() && !cognome1.getText().isBlank() && !username1.getText().isBlank() && !password1.getText().isBlank()
                    && !conferma_password1.getText().isBlank())
                registrazione();
            else {
                campi_mancanti.setText("Attenzione! Compilare tutti i campi!");
                password_message.setText("");
                corretto.setText("");
            }
        }
    }

    /**
     * Metodo che permette l'effettiva registrazione dell'utente all'interno del Db effettuando una query di inserimento.
     * @throws IOException
     */
    public void registrazione() throws IOException {
        App a = new App();
        Connection connectDB = DatabaseConnection.getInstance();
        if(ruolo1.isSelected()) {
            campi_mancanti.setText("");
            password_message.setText("");
            corretto.setText("");
            AbstractFactoryRegistrazione fac = new ConcreteFactoryRegistrazione();
            Utente u = ((ConcreteFactoryRegistrazione) fac).createVaccinando(nome.getText(), cognome.getText(), data_di_nascita.getValue(), citta_natale.getText(), citta.getText(), provincia.getText(),
                    CAP.getText(), codice_fiscale.getText(), tessera_sanitaria.getText(), username.getText(), password.getText(), mail.getText(),numero_di_telefono.getText());
            Vaccinando v = (Vaccinando) u;
            String insertFields = "INSERT INTO VACCINANDO (NUM_TESSERA_SANITARIA, CODICE_FISCALE, NOME, COGNOME, DATA_NASCITA, CITTA_NATALE, CITTA, PROVINCIA, CAP, E_MAIL, USERNAME, PASSWORD, NUMERO_TELEFONO) VALUES ('";
            String insertValues = v.getTessera_Sanitaria() + "','" + v.getCodice_Fiscale() + "','" + v.getNome() + "','" + v.getCognome() + "', TO_DATE('" + v.getData_di_Nascita() + "', 'YYYY-MM-DD'), '" +
                    v.getCitta_Natale() + "','" + v.getCitta() + "','" + v.getProvincia() + "','" + v.getCAP() + "','" + v.getMail() + "','" + v.getUsername() + "','" + v.getPassword() + "','" + v.getNumero_telefono() + "')";
            String insertToRegister = insertFields + insertValues;
            System.out.println(insertToRegister);
            try {
                String espressione = "^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-.]?[0-9a-z])*.[a-z]{2,4}$";
                if (mail.getText().matches(espressione)) {
                    mail_scorretta.setText("");
                    if (password.getText().equals(conferma_password.getText())) {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(insertToRegister);
                        password_message.setText("");
                        campi_mancanti.setText("");
                        corretto.setText("Registrazione effettuata!");
                    } else {
                        campi_mancanti.setText("");
                        password_message.setText("Password non corrispondenti!");
                        corretto.setText("");
                    }
                } else {
                    mail_scorretta.setText("Email non valida!");
                    campi_mancanti.setText("");
                    corretto.setText("");
                }
            } catch (Exception e) {
                campi_mancanti.setText("Tessera Sanitaria e/o Codice Fiscale già registrati!");
                password_message.setText("");
                corretto.setText("");
                e.printStackTrace();
                e.getCause();
            }
        }
        else if(ruolo2.isSelected()) {
            campi_mancanti.setText("");
            password_message1.setText("");
            corretto.setText("");
            AbstractFactoryRegistrazione fac = new ConcreteFactoryRegistrazione();
            Utente u = ((ConcreteFactoryRegistrazione) fac).createMedico(nome1.getText(), cognome1.getText(), username1.getText(), password1.getText(), codice_ordine_dei_medici.getText());
            Medico m = (Medico) u;
            String insertFields = "INSERT INTO OPERATORE_SANITARIO_RESPONSABILE (NUM_ISCRIZIONE_ORDINE_MEDICI, NOME, COGNOME, USERNAME, PASSWORD) VALUES  ('";
            String insertValues = m.getCodice_Ordine_dei_Medici() + "','" + m.getNome() + "','" + m.getCognome() + "','" + m.getUsername() + "','" + m.getPassword() + "')";
            String insertToRegister = insertFields + insertValues;
            System.out.println(insertToRegister);

        try {
            if (password.getText().equals(conferma_password.getText())) {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                password_message1.setText("");
                campi_mancanti.setText("");
                corretto.setText("Registrazione effettuata!");
            } else {
                campi_mancanti.setText("");
                password_message1.setText("Password non corrispondenti!");
                corretto.setText("");
            }
        }
        catch (Exception e) {
            campi_mancanti.setText("Codice Ordine dei Medici già registrato!");
            password_message1.setText("");
            corretto.setText("");
            e.printStackTrace();
            e.getCause();
        }
        }
    }


    /**
     * Metodo che consente, su apposita operazione di click sul bottone 'Indietro', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    public void back(ActionEvent event) throws IOException {
        App a = new App();
        a.changeScene("PrimaPagina.fxml");
    }
}
