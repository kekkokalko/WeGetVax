package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.*;
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
 * MonitoraggioController è la classe che definisce il controller dell'operazione di visualizzazione dei monitoraggi sullo stato fisico e della salute di un vaccinando.
 */
public class MonitoraggioController implements Initializable {

    @FXML
    private Button aggiungi;

    @FXML
    private TableColumn<Monitoraggio, LocalDate> data;

    @FXML
    private TableColumn<Monitoraggio, String> dolori_muscolari;

    @FXML
    private TableColumn<Monitoraggio, Integer> frequenza_cardiaca;

    @FXML
    private Button indietro;

    @FXML
    private Button logOut;

    @FXML
    private TableColumn<Monitoraggio, String> mal_di_gola;

    @FXML
    private TableColumn<Monitoraggio, String> nausea;

    @FXML
    private TableColumn<Monitoraggio, String> respirazione;

    @FXML
    private TableColumn<Monitoraggio, String> situazione_psicologica;

    @FXML
    private TableView<Monitoraggio> tabella;

    @FXML
    private TableColumn<Monitoraggio, Integer> temperatura;

    @FXML
    private TableColumn<Monitoraggio, String> tosse;

    @FXML
    private TableColumn<Monitoraggio, String> mal_di_testa;

    @FXML
    private Label user;

    /**
     * Metodo che consente, su apposita operazione di click sul bottone 'Indietro', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    @FXML
    void back(ActionEvent event) throws IOException{
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
     * Metodo che consente di passare alla pagina riguardante l'aggiunta di una nuova anamnesi. Ciò lo si effettua schiacciando sul bottone apposito.
     * @param event
     * @throws IOException
     */
    @FXML
    void newReservation(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("NuovoMonitoraggio.fxml");
    }

    /**
     * Metodo ereditato da Initializable che consente di inizializzare la pagina e di visualizzare i valori delle varie anamnesi all'interno della tabella, facendo una
     * query di selezione nel Db.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Vaccinando tempVaccinando = StrutturaAppoggio.getVaccinando();
        user.setText(tempVaccinando.getNome() + " " + tempVaccinando.getCognome());
        ObservableList<Monitoraggio> monitoraggio = FXCollections.observableArrayList();
        try {
            Connection connectDB = DatabaseConnection.getInstance();
            ResultSet queryResult= connectDB.createStatement().executeQuery("select * from Monitoraggio where CODICE_FISCALE = " + "'" + tempVaccinando.getCodice_Fiscale() + "'");
            while(queryResult.next()) {
                monitoraggio.add(new Monitoraggio.MonitoraggioBuilder()
                        .setData(queryResult.getDate(2).toLocalDate())
                        .setTemperatura(queryResult.getInt(3))
                        .setMalDiGola(queryResult.getString(4))
                        .setMalDiTesta(queryResult.getString(5))
                        .setDoloriMuscolari(queryResult.getString(6))
                        .setNausea(queryResult.getString(7))
                        .setSituazionePsicologica(queryResult.getString(8))
                        .setTosse(queryResult.getString(9))
                        .setRespirazione(queryResult.getString(10))
                        .setFrequenzaCardiaca(queryResult.getInt(11))
                        .build());
                tabella.setItems(monitoraggio);
                data.setCellValueFactory(new PropertyValueFactory<Monitoraggio, LocalDate>("data"));
                temperatura.setCellValueFactory(new PropertyValueFactory<Monitoraggio, Integer>("temperatura"));
                mal_di_gola.setCellValueFactory(new PropertyValueFactory<Monitoraggio, String>("malDiGola"));
                mal_di_testa.setCellValueFactory(new PropertyValueFactory<Monitoraggio,String>("malDiTesta"));
                dolori_muscolari.setCellValueFactory(new PropertyValueFactory<Monitoraggio,String>("doloriMuscolari"));
                nausea.setCellValueFactory(new PropertyValueFactory<Monitoraggio,String>("nausea"));
                situazione_psicologica.setCellValueFactory(new PropertyValueFactory<Monitoraggio,String>("situazionePsicologica"));
                tosse.setCellValueFactory(new PropertyValueFactory<Monitoraggio,String>("tosse"));
                respirazione.setCellValueFactory(new PropertyValueFactory<Monitoraggio,String>("respirazione"));
                frequenza_cardiaca.setCellValueFactory(new PropertyValueFactory<Monitoraggio,Integer>("frequenzaCardiaca"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
