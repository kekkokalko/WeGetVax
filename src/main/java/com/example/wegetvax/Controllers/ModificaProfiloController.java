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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * ModificaProfiloController è la classe che definisce il controller dell'operazione di modifica del profilo per un vaccinando.
 */
public class ModificaProfiloController{

    @FXML
    private TextField CAP;

    @FXML
    private Button Conferma;

    @FXML
    private Label campi_mancanti;

    @FXML
    private TextField citta;

    @FXML
    private TextField citta_natale;

    @FXML
    private TextField cognome;

    @FXML
    private PasswordField conferma_password;

    @FXML
    private DatePicker data_di_nascita;

    @FXML
    private TextField mail;

    @FXML
    private Label mail_scorretta;

    @FXML
    private TextField nome;

    @FXML
    private TextField numero_di_telefono;

    @FXML
    private PasswordField password;

    @FXML
    private Label password_message;

    @FXML
    private TextField provincia;

    @FXML
    private TextField username;

    @FXML
    private Label data_scorretta;

    @FXML
    private Label corretto;

    @FXML
    private Button cancella_profilo;

    /**
     * Metodo che consente l'aggiornamento, dopo la modifica, del profilo all'interno del Db. quest'utlima operazione è efettuata concretamente dalla procedura 'validazione'.
     * @param event
     */
    @FXML
    void aggiorna(ActionEvent event) {
        if (!nome.getText().isBlank() && !cognome.getText().isBlank() && !data_di_nascita.getValue().toString().isBlank() && !citta_natale.getText().isBlank()
                && !citta.getText().isBlank() && !provincia.getText().isBlank() && !mail.getText().isBlank() && !numero_di_telefono.getText().isBlank() &&
                !username.getText().isBlank() && !password.getText().isBlank() && !conferma_password.getText().isBlank()) {
            LocalDate today = LocalDate.now();
            LocalDate date = data_di_nascita.getValue();
            if (date.isAfter(today))
                data_scorretta.setText("Inserisci una data precedente alla data attuale!");
            else
                validazione();
        }
        else {
            campi_mancanti.setText("Attenzione! Compilare tutti i campi!");
            password_message.setText("");
            corretto.setText("");
        }
    }

    /**
     * Procedura che conferma le modifiche fatte al profilo all'interno del Db.
     */
    public void validazione() {
        App a = new App();
        Connection connectDB = DatabaseConnection.getInstance();
        Vaccinando v = StrutturaAppoggio.getVaccinando();
        AbstractFactoryRegistrazione fac = new ConcreteFactoryRegistrazione();
        Utente u = ((ConcreteFactoryRegistrazione) fac).createVaccinando(nome.getText(), cognome.getText(), data_di_nascita.getValue(), citta_natale.getText(), citta.getText(), provincia.getText(),
                CAP.getText(), v.getCodice_Fiscale(), v.getTessera_Sanitaria(), username.getText(), password.getText(), mail.getText(),
               numero_di_telefono.getText());
        Vaccinando x = (Vaccinando) u;
        String updateFields = "UPDATE VACCINANDO SET NOME='" + x.getNome() + "', COGNOME='" + x.getCognome() + "', Data_Nascita = TO_DATE('" + x.getData_di_Nascita() + "', 'YYYY-MM-DD'), Citta_Natale = '" +
                x.getCitta_Natale() + "', Citta = '" + x.getCitta() + "', Provincia ='" + x.getProvincia() + "', CAP = '" + x.getCAP() + "', E_MAIL='" + x.getMail() + "', USERNAME='" + x.getUsername() + "', PASSWORD='" + x.getPassword() + "', NUMERO_TELEFONO='" + x.getNumero_telefono() + "' " +
                "WHERE NUM_TESSERA_SANITARIA = '" + v.getTessera_Sanitaria() + "' AND CODICE_FISCALE = '" + v.getCodice_Fiscale() + "'";
        try {
            String espressione = "^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-.]?[0-9a-z])*.[a-z]{2,4}$";
            if (mail.getText().matches(espressione)) {
                mail_scorretta.setText("");
                if (password.getText().equals(conferma_password.getText())) {
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateFields);
                    StrutturaAppoggio.setVaccinando(x);
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
            campi_mancanti.setText("Errore di registrazione!");
            password_message.setText("");
            corretto.setText("");
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * Metodo che permette la cancellazione del profilo dopo aver schiacciato il bottone apposito.
     * @param event
     * @throws IOException
     */
    @FXML
    void delete(ActionEvent event) throws IOException{
        App a = new App();
        Alert alert = new Alert(Alert.AlertType.WARNING,"seleziona",ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Attenzione!");
        alert.setContentText("Sei sicuro di voler cancellare il profilo?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            Vaccinando v = StrutturaAppoggio.getVaccinando();
            Connection connectDB = DatabaseConnection.getInstance();
            String deleteQuery = "DELETE FROM VACCINANDO WHERE NUM_TESSERA_SANITARIA = '" + v.getTessera_Sanitaria() + "'";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteQuery);
                a.changeScene("PrimaPagina.fxml");
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                e.getCause();
            }
        }
        else if(result.get() == ButtonType.CANCEL)
            alert.close();
    }

    /**
     * Metodo che consente, su apposita operazione di click sul bottone 'Indietro', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        App a = new App();
        a.changeScene("VaccinandoDashboard.fxml");
    }
}