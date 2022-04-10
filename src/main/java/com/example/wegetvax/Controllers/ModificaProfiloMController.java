package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * ModificaProfiloController è la classe che definisce il controller dell'operazione di modifica del profilo per un medico.
 */
public class ModificaProfiloMController {

    @FXML
    private Button Conferma;

    @FXML
    private Label campi_mancanti;

    @FXML
    private Button cancella_profilo;

    @FXML
    private TextField cognome;

    @FXML
    private PasswordField conferma_password;

    @FXML
    private Label corretto;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField password;

    @FXML
    private Label password_message;

    @FXML
    private TextField username;

    /**
     * Metodo che consente l'aggiornamento, dopo la modifica, del profilo all'interno del Db. Quest'utlima operazione è efettuata concretamente dalla procedura 'validazione'.
     * @param event
     */
    @FXML
    void aggiorna(ActionEvent event) {
        if (!nome.getText().isBlank() && !cognome.getText().isBlank() && !username.getText().isBlank() && !password.getText().isBlank() && !conferma_password.getText().isBlank())
            validazione();
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
        Medico m = StrutturaAppoggio.getMedico();
        AbstractFactoryRegistrazione fac = new ConcreteFactoryRegistrazione();
        Utente u = ((ConcreteFactoryRegistrazione) fac).createMedico(m.getCodice_Ordine_dei_Medici(),nome.getText(), cognome.getText(), username.getText(), password.getText());
        Medico x = (Medico)u;
        String updateQuery = "UPDATE OPERATORE_SANITARIO_RESPONSABILE SET NOME='" + x.getNome() + "', COGNOME= '" + x.getCognome() + "', USERNAME = '" + x.getUsername() + "', Password ='" +
                x.getPassword() + "' WHERE Num_Iscrizione_Ordine_Medici = " + m.getCodice_Ordine_dei_Medici();
        System.out.println(updateQuery);
        try {
            if (password.getText().equals(conferma_password.getText())) {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(updateQuery);
                StrutturaAppoggio.setMedico(x);
                password_message.setText("");
                campi_mancanti.setText("");
                corretto.setText("Registrazione effettuata!");
            } else {
                campi_mancanti.setText("");
                password_message.setText("Password non corrispondenti!");
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
     * Metodo che consente, su apposita operazione di click sul bottone 'Indietro', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        App a = new App();
        a.changeScene("MedicoDashboard.fxml");
    }

    /**
     * Metodo che permette la cancellazione del profilo dopo aver schiacciato il bottone apposito.
     * @param event
     * @throws IOException
     */
    @FXML
    void delete(ActionEvent event) throws IOException {
        App a = new App();
        Alert alert = new Alert(Alert.AlertType.WARNING, "seleziona", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Attenzione!");
        alert.setContentText("Sei sicuro di voler cancellare il profilo?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Medico m = StrutturaAppoggio.getMedico();
            Connection connectDB = DatabaseConnection.getInstance();
            String deleteQuery = "DELETE FROM OPERATORE_SANITARIO_RESPONSABILE WHERE Num_Iscrizione_Ordine_Medici = '" + m.getCodice_Ordine_dei_Medici() + "'";
            System.out.println(deleteQuery);
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(deleteQuery);
                a.changeScene("PrimaPagina.fxml");
            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }
        } else if (result.get() == ButtonType.CANCEL)
            alert.close();
    }
}
