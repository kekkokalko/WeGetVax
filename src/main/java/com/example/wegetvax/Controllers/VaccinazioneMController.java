package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.Medico;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Vaccinando;
import com.example.wegetvax.Models.Vaccinazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * VaccinazioneMController è la classe che definisce il controller che permette l'aggiunta dei nuovi resoconti vaccinali di un vacccinando da parte di un medico.
 */
public class VaccinazioneMController implements Initializable {

    @FXML
    private ComboBox<String> braccio_inoculante;

    @FXML
    private TextField codice_fiscale;

    @FXML
    private TextField cognome;

    @FXML
    private DatePicker data_nascita;

    @FXML
    private DatePicker data_scadenza;

    @FXML
    private DatePicker data_somministrazione;

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
    private TextField nome;

    @FXML
    private TextField nome_farmaco;

    @FXML
    private TextField nome_medico;

    @FXML
    private TextField numero_dose;

    @FXML
    private TextField numero_lotto;

    @FXML
    private Label user;

    private ToggleGroup button_group;

    /**
     * Metodo che consente, su apposita operazione di click sul bottone 'Indietro', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    @FXML
    void back(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("SchedaClinica.fxml");
    }

    /**
     * Metodo che permette la creazione di un nuovo resoconto vaccinale. Controlla che i campi appositi siano stati tutti compilati, dopodichè richiama il metodo 'convalida'.
     */
    @FXML
    void conferma(ActionEvent event) {
        if (!numero_dose.getText().isBlank() && !nome_farmaco.getText().isBlank() && !numero_lotto.getText().isBlank() && !data_scadenza.getValue().toString().isBlank()
                && !data_somministrazione.getValue().toString().isBlank() && !braccio_inoculante.getSelectionModel().getSelectedItem().isBlank() && !luogo.getText().isBlank())
            convalida();
        else {
            messaggio.setText("Attenzione! Ci sono alcuni campi vuoti!");
            messaggio1.setText("");
        }
    }

    /**
     * Metodo che, dopo aver definito un nuovo resoconto mediante la compilazione dei campi, l'aggiunge all'interno del Db.
     */
    public void convalida() {
        Connection connectDB = DatabaseConnection.getInstance();
        Vaccinazione v = new Vaccinazione.VaccinazioneBuilder()
                .setBraccioInoculatore(braccio_inoculante.getValue())
                .setDataScadenza(data_scadenza.getValue())
                .setDataSomministrazione(data_somministrazione.getValue())
                .setNomeFarmaco(nome_farmaco.getText())
                .setNomeLuogo(luogo.getText())
                .setNumeroDose(Integer.parseInt(numero_dose.getText()))
                .setNumeroLotto(numero_lotto.getText())
                .build();
        String q1 = "INSERT INTO VACCINAZIONE (NOME_VACCINANDO,COGNOME_VACCINANDO,CODICE_FISCALE,DATA_DI_NASCITA,NUMERO_DOSE,NOME_FARMACO,NUM_LOTTO,DATA_SCADENZA,NOME_MEDICO,DATA_SOMMINISTRAZIONE,BRACCIO_INOCULAZIONE,NOME_LUOGO) VALUES ('";
        String q2 = v.getNomeVaccinando() + "','" + v.getCognomeVaccinando() + "','" + v.getCodiceFiscale() + "', TO_DATE('" + v.getDataDiNascita() + "', 'YYYY-MM-DD'), '" + v.getNumeroDose() + "','" + v.getNomeFarmaco() + "','" + v.getNumeroLotto() + "'," +
                    "TO_DATE('" + v.getDataScadenza() + "', 'YYYY-MM-DD'), '" + v.getNomeMedico() + "', TO_DATE('" + v.getDataSomministrazione() + "', 'YYYY-MM-DD'), '" + v.getBraccioInoculatore() + "','" + v.getNomeLuogo() + "')";
        String insertQuery=q1+q2;
        System.out.println(insertQuery);
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertQuery);
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
    void log_out(ActionEvent event) throws IOException {
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
     * Metodo ereditato da Initializable che consente di inizializzare la pagina in maniera corretta.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        braccio_inoculante.getItems().removeAll(braccio_inoculante.getItems());
        braccio_inoculante.getItems().addAll("Sinistro", "Destro");
        braccio_inoculante.getSelectionModel().select("Sinistro");
        Medico m = StrutturaAppoggio.getMedico();
        user.setText("Dott. " + m.getNome() + " " + m.getCognome());
        Vaccinando v = StrutturaAppoggio.getVaccinando();
        nome.setText(v.getNome());
        cognome.setText(v.getCognome());
        codice_fiscale.setText(v.getCodice_Fiscale());
        data_nascita.setValue(v.getData_di_Nascita());
        nome_medico.setText(m.getNome() + " " + m.getCognome());
    }
}
