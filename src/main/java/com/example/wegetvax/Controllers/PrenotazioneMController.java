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
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * PrenotazioneMController è la classe che definisce il controller dell'operazione di creazione di una prenotazione effettuate da un medico nei confronti di un vaccinando.
 */
public class PrenotazioneMController implements Initializable {

    @FXML
    private TextField asl_di_appartenenza;

    @FXML
    private TextField citta;

    @FXML
    private TextField civico;

    @FXML
    private DatePicker data;

    @FXML
    private TextField distretto;

    @FXML
    private Button indietro;

    @FXML
    private Button inserisci;

    @FXML
    private Button logOut;

    @FXML
    private TextField luogo;

    @FXML
    private Label messaggio;

    @FXML
    private Label messaggio1;

    @FXML
    private TextField provincia;

    @FXML
    private Label user;

    @FXML
    private TextField via;

    //Primo oggetto istanziato da Prototype per prenotazione
    ConcretePrototypePrenotazione prova = new ConcretePrototypePrenotazione(0,null,null,null,null,null,0, null, null,0);


    /**
     * Metodo che consente, su apposita operazione di click sul bottone 'Indietro', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        App a = new App();
        a.changeScene("SchedaClinica.fxml");
    }

    /**
     * Metodo che permette la creazione di una nuova prenotazione. Controlla che i campi appositi siano stati tutti compilati, dopodichè richiama il metodo 'convalida'.
     */
    @FXML
    void conferma(ActionEvent event) {
        if (!data.getValue().toString().isBlank() && !luogo.getText().isBlank() && !citta.getText().isBlank() && !via.getText().isBlank() && !civico.getText().isBlank()
            && !provincia.getText().isBlank() && !asl_di_appartenenza.getText().isBlank() && !distretto.getText().isBlank())
            convalida();
        else {
            messaggio.setText("Attenzione! Ci sono alcuni campi vuoti!");
            messaggio1.setText("");
        }

    }

    /**
     * Metodo che, dopo aver definito una nuova prenotazione mediante la compilazione dei campi, l'aggiunge all'interno del Db.
     */
    public void convalida() {
        Vaccinando v = StrutturaAppoggio.getVaccinando();
        Random rand = new Random();
        Connection connectDB = DatabaseConnection.getInstance();
        ConcretePrototypePrenotazione prenotazione = (ConcretePrototypePrenotazione) prova.clone();
        prenotazione.setIdentificativo(rand.nextInt(1000));
        prenotazione.setCodice_fiscale(v.getCodice_Fiscale());
        prenotazione.setData_ora_prevista(data.getValue());
        prenotazione.setLuogo(luogo.getText());
        prenotazione.setCitta(citta.getText());
        prenotazione.setVia(via.getText());
        prenotazione.setCivico(Integer.parseInt(civico.getText()));
        prenotazione.setProvincia(provincia.getText());
        prenotazione.setAsl(asl_di_appartenenza.getText());
        prenotazione.setDistretto(Integer.parseInt(distretto.getText()));
        String q1 = "INSERT INTO PRENOTAZIONE (IDENTIFICATIVO, CODICE_FISCALE, DATA_ORA_PREVISTE, LUOGO, CITTA, VIA, CIVICO, PROVINCIA, ASL_DI_APPARTENENZA, DISTRETTO) VALUES ('";
        String q2 = prenotazione.getIdentificativo() + "','" + prenotazione.getCodice_fiscale() + "', TO_DATE('" + prenotazione.getData_ora_prevista() + "', 'YYYY-MM-DD'), '" + prenotazione.getLuogo() + "','" + prenotazione.getCitta() + "','" + prenotazione.getVia() +
                 "','" + prenotazione.getCivico() + "','" + prenotazione.getProvincia() + "','" + prenotazione.getAsl() + "','" + prenotazione.getDistretto() + "')";
        String query = q1 + q2;
        System.out.println(query);
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            messaggio1.setText("Inserimento effettuato!");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * Metodo che permette, su apposita operazione di click sul bottone 'Logout', di effettuare il logout e uscire dal proprio account.
     * @param event
     * @throws IOException
     */
    @FXML
    void log_out(ActionEvent event) throws IOException{
        App a = new App();
        Alert alert = new Alert(Alert.AlertType.WARNING,"seleziona", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Attenzione!");
        alert.setContentText("Sei sicuro di voler uscire?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            a.changeScene("Login.fxml");
        else if(result.get() == ButtonType.CANCEL)
            alert.close();
    }

    /**
     * Metodo ereditato da Initializable che consente di inizializzare la pagina.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Medico m = StrutturaAppoggio.getMedico();
        user.setText("Dott. " + m.getNome() + " " + m.getCognome());
    }
}
