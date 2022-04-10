package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.ConcretePrototypePrenotazione;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Vaccinando;
import com.example.wegetvax.Models.Vaccinazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * VaccinazioneController è la classe che definisce il controller che permette la visualizzazione dei resoconti vaccinali di un vacccinando.
 */
public class VaccinazioneController implements Initializable {

    @FXML
    private TableColumn<Vaccinazione, String> braccio_inoculatore;

    @FXML
    private TableColumn<Vaccinazione, LocalDate> data_scadenza;

    @FXML
    private TableColumn<Vaccinazione, LocalDate> data_somministrazione;

    @FXML
    private Button indietro;

    @FXML
    private Button logOut;

    @FXML
    private TableColumn<Vaccinazione, String> nome_farmaco;

    @FXML
    private TableColumn<Vaccinazione, String> nome_luogo;

    @FXML
    private TableColumn<Vaccinazione, String> nome_medico;

    @FXML
    private TableColumn<Vaccinazione, Integer> numero_dose;

    @FXML
    private TableColumn<Vaccinazione, String> numero_lotto;

    @FXML
    private TableView<Vaccinazione> tabella;

    @FXML
    private Label user;

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
     * Metodo ereditato da Initializable che consente di inizializzare la pagina permettendo la visualizzazione completa della tabella con i valori nelle rispettive colonne.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Vaccinando tempVaccinando = StrutturaAppoggio.getVaccinando();
        user.setText(tempVaccinando.getNome() + " " + tempVaccinando.getCognome());
        ObservableList<Vaccinazione> vaccinazione = FXCollections.observableArrayList();
        try {
            System.out.println(tempVaccinando.getCodice_Fiscale());
            Connection connectDB = DatabaseConnection.getInstance();
            ResultSet queryResult= connectDB.createStatement().executeQuery("select * from Vaccinazione where CODICE_FISCALE = " + "'" + tempVaccinando.getCodice_Fiscale() + "'");
            while(queryResult.next()) {
                vaccinazione.add(new Vaccinazione.VaccinazioneBuilder()
                        .setBraccioInoculatore(queryResult.getString(11))
                        .setDataScadenza(queryResult.getDate(8).toLocalDate())
                        .setDataSomministrazione(queryResult.getDate(10).toLocalDate())
                        .setNomeFarmaco(queryResult.getString(6))
                        .setNomeLuogo(queryResult.getString(12))
                        .setNumeroDose(Integer.parseInt(queryResult.getString(5)))
                        .setNumeroLotto(queryResult.getString(7))
                        .build());
                tabella.setItems(vaccinazione);
                numero_dose.setCellValueFactory(new PropertyValueFactory<Vaccinazione, Integer>("numeroDose"));
                nome_farmaco.setCellValueFactory(new PropertyValueFactory<Vaccinazione, String>("nomeFarmaco"));
                numero_lotto.setCellValueFactory(new PropertyValueFactory<Vaccinazione, String>("numeroLotto"));
                data_scadenza.setCellValueFactory(new PropertyValueFactory<Vaccinazione,LocalDate>("dataScadenza"));
                nome_medico.setCellValueFactory(new PropertyValueFactory<Vaccinazione,String>("nomeMedico"));
                data_somministrazione.setCellValueFactory(new PropertyValueFactory<Vaccinazione,LocalDate>("dataScadenza"));
                braccio_inoculatore.setCellValueFactory(new PropertyValueFactory<Vaccinazione,String>("braccioInoculatore"));
                nome_luogo.setCellValueFactory(new PropertyValueFactory<Vaccinazione,String>("nomeLuogo"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }
}
