package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.ConcretePrototypeAnamnesi;
import com.example.wegetvax.Models.ConcretePrototypePrenotazione;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Vaccinando;
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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * PrenotazioneController è la classe che definisce il controller dell'operazione di visualizzazione delle prenotazioni effettuate da un vaccinando.
 */
public class PrenotazioneController implements Initializable {

    @FXML
    private TableColumn<ConcretePrototypePrenotazione, String> asl;

    @FXML
    private TableColumn<ConcretePrototypePrenotazione, String> citta;


    @FXML
    private TableColumn<ConcretePrototypePrenotazione, Integer> civico;


    @FXML
    private TableColumn<ConcretePrototypePrenotazione, LocalDate> data;

    @FXML
    private TableColumn<ConcretePrototypePrenotazione, Integer> distretto;

    @FXML
    private Button indietro;

    @FXML
    private Button inserisci;

    @FXML
    private Button logOut;

    @FXML
    private TableColumn<ConcretePrototypePrenotazione, String> luogo;

    @FXML
    private TableColumn<ConcretePrototypePrenotazione, String> provincia;

    @FXML
    private TableView<ConcretePrototypePrenotazione> tabella;

    @FXML
    private Label user;

    @FXML
    private TableColumn<ConcretePrototypePrenotazione, String> via;

    @FXML
    private Button aggiungi;

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
     * Metodo ereditato da Initializable che consente di inizializzare la pagina. Permette la visualizzazione in una tabella apposita delle prenotazioni. Ciò è possibile
     * grazie ad una query di selezione all'interno del Db.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Vaccinando tempVaccinando = StrutturaAppoggio.getVaccinando();
        user.setText(tempVaccinando.getNome() + " " + tempVaccinando.getCognome());
        ObservableList<ConcretePrototypePrenotazione> prenotazione = FXCollections.observableArrayList();
        try {
            Connection connectDB = DatabaseConnection.getInstance();
            ResultSet queryResult= connectDB.createStatement().executeQuery("select * from Prenotazione where CODICE_FISCALE = " + "'" + tempVaccinando.getCodice_Fiscale() + "'");
            while(queryResult.next()) {
                prenotazione.add(new ConcretePrototypePrenotazione(Integer.parseInt(queryResult.getString(1)), queryResult.getString("Codice_Fiscale"),
                        queryResult.getDate("Data_Ora_Previste").toLocalDate(),queryResult.getString("Luogo"),queryResult.getString("Citta"),
                        queryResult.getString("Via"),Integer.parseInt(queryResult.getString("Civico")),queryResult.getString("Provincia"),
                        queryResult.getString("Asl_di_appartenenza"),Integer.parseInt(queryResult.getString("Distretto"))));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        tabella.setItems(prenotazione);
        data.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione, LocalDate>("data_ora_prevista"));
        luogo.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione,String>("luogo"));
        citta.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione, String>("citta"));
        via.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione,String>("via"));
        civico.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione,Integer>("civico"));
        provincia.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione,String>("provincia"));
        via.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione,String>("via"));
        asl.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione,String>("asl"));
        distretto.setCellValueFactory(new PropertyValueFactory<ConcretePrototypePrenotazione,Integer>("distretto"));
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si può creare una nuova prenotazione.
     */
    @FXML
    void newReservation(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("NuovaPrenotazione.fxml");
    }
}
