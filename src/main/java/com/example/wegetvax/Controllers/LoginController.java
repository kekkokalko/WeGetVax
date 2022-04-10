package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.Medico;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Vaccinando;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * LoginController è la classe che definisce il controller dell'operazione di Login. Permette l'accesso, dopo riconoscimento, dell'utente in WeGetVax.
 */
public class LoginController {

    @FXML
    private Button accedi;

    @FXML
    private TextField codice_ordine_dei_medici;

    @FXML
    private Label error_message_login1;

    @FXML
    private Label error_message_login2;

    @FXML
    private Button indietro;

    @FXML
    private PasswordField passwordM;

    @FXML
    private PasswordField passwordV;

    @FXML
    private RadioButton ruolo1;

    @FXML
    private RadioButton ruolo2;

    @FXML
    protected TextField username;

    private ToggleGroup button_group;

    /**
     * Metodo che consente, su apposita operazione di click sul bottone 'Indietro', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        App a = new App();
        a.changeScene("PrimaPagina.fxml");
    }

    /**
     * Metodo che effettua l'operazione di login. Richiama i due metodi 'validateLogin1' e 'validateLogin2' a seconda se si vuole accedere come Vaccinando o come Medico.
     * @param event
     */
    @FXML
    void login(ActionEvent event) {
        if (!username.getText().isBlank() && !passwordV.getText().isBlank())
            validateLogin1();
        else if(ruolo1.isSelected()) {
            error_message_login1.setText("Inserisci il tuo Username e Password corretti!");
            error_message_login2.setText("");
        }
        if(!codice_ordine_dei_medici.getText().isBlank() && !passwordM.getText().isBlank())
            validatelogin2();
        else if(ruolo2.isSelected()) {
            error_message_login2.setText("Inserisci il tuo Codice personale e la Password corretti!");
            error_message_login1.setText("");
        }
    }

    /**
     * Metodo che disattiva i campi riservati all'accesso di un medico quando è selezionata la voce 'Vaccinando'.
     * @param event
     */
    @FXML
    void seleziona1(ActionEvent event) {
        button_group= new ToggleGroup();
        this.ruolo1.setToggleGroup(button_group);
        this.ruolo2.setToggleGroup(button_group);

        if (ruolo1.isSelected()) {
            codice_ordine_dei_medici.setDisable(true);
            passwordM.setDisable(true);
            username.setDisable(false);
            passwordV.setDisable(false);
        }

    }

    /**
     * Metodo che disattiva i campi riservati all'accesso di un vaccinando quando è selezionata la voce 'Medico'.
     * @param event
     */
    @FXML
    void seleziona2(ActionEvent event) {
        if(ruolo2.isSelected()) {
            username.setDisable(true);
            passwordV.setDisable(true);
            codice_ordine_dei_medici.setDisable(false);
            passwordM.setDisable(false);
        }
    }

    /**
     * Metodo che riconosce un Vaccinando facendo una select all'interno del Db.
     */
    public void validateLogin1() {
        App a = new App();
        Connection connectDB = DatabaseConnection.getInstance();
        String query = "SELECT count(*) FROM VACCINANDO WHERE username = '" + username.getText() + "' AND password = '" + passwordV.getText() + "'";
        System.out.println(query);
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    query = "SELECT * FROM VACCINANDO WHERE username = '" + username.getText() + "' AND password = '" + passwordV.getText() + "'";
                    statement = connectDB.createStatement();
                    queryResult = statement.executeQuery(query);
                    if(queryResult.next()) {
                        Vaccinando v = new Vaccinando(null, null, null, null, null, null, null, null, null, null, null, null, null);
                        v.setTessera_sanitaria(queryResult.getString(1));
                        v.setCodice_fiscale(queryResult.getString(2));
                        v.setNome(queryResult.getString(3));
                        v.setCognome(queryResult.getString(4));
                        v.setData_di_nascita(queryResult.getDate(5).toLocalDate());
                        v.setCitta_natale(queryResult.getString(6));
                        v.setCitta(queryResult.getString(7));
                        v.setProvincia(queryResult.getString(8));
                        v.setCAP(queryResult.getString(9));
                        v.setMail(queryResult.getString(10));
                        v.setUsername(queryResult.getString(11));
                        v.setPassword(queryResult.getString(12));
                        v.setNumero_telefono(queryResult.getString(13));
                        StrutturaAppoggio.setVaccinando(v);
                        a.changeScene("VaccinandoDashboard.fxml");
                    }
                }
                else
                    error_message_login1.setText("Utente sconosciuto!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * Metodo che riconosce un Vaccinando facendo una select all'interno del Db.
     */
    public void validatelogin2() {
        App a = new App();
        Connection connectDB = DatabaseConnection.getInstance();
        String query = "SELECT count(*) FROM operatore_sanitario_responsabile WHERE num_iscrizione_ordine_medici = '" + codice_ordine_dei_medici.getText() + "' AND password = '" + passwordM.getText() + "'";
        System.out.println(query);
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    query = "SELECT * FROM OPERATORE_SANITARIO_RESPONSABILE WHERE num_iscrizione_ordine_medici = '" + codice_ordine_dei_medici.getText() + "' AND password = '" + passwordM.getText() + "'";
                    statement = connectDB.createStatement();
                    queryResult = statement.executeQuery(query);
                    if(queryResult.next()) {
                        Medico m = new Medico(null, null, null, null, null);
                        m.setCodice_ordine_dei_medici(queryResult.getString(1));
                        m.setNome(queryResult.getString(2));
                        m.setCognome(queryResult.getString(3));
                        m.setUsername(queryResult.getString(4));
                        m.setPassword(queryResult.getString(5));
                        StrutturaAppoggio.setMedico(m);
                        a.changeScene("MedicoDashboard.fxml");
                    }
                }
                else
                    error_message_login1.setText("Utente sconosciuto!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
