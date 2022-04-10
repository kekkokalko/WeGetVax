package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.ConcretePrototypeAnamnesi;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Vaccinando;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * AnamnesiController è la classe che definisce l'oggetto control per la visualizzazione delle anamnesi del vaccinando all'interno di una tabella. Queste ultime
 * sono state aggiunte in precedenza da un medico nella sua sezione dedicata.
 */
public class AnamnesiController implements Initializable{

    @FXML
    private Button logOut;

    @FXML
    private Button indietro;

    @FXML
    private Label user;

    @FXML
    private TableColumn<ConcretePrototypeAnamnesi, LocalDate> data;

    @FXML
    private TableColumn<ConcretePrototypeAnamnesi, String> esito;

    @FXML
    private TableColumn<ConcretePrototypeAnamnesi, String> luogo;

    @FXML
    private TableColumn<ConcretePrototypeAnamnesi, String> nome_medico;

    @FXML
    private TableView<ConcretePrototypeAnamnesi> tabella;


    /**
     * Metodo ereditato da Initializable che consente di fornire i giusti valori alla tabella. Tali valori sono prelevati grazie ad una query di selezione fatta
     * al Db nella tabella apposita 'Anamnesi'.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Vaccinando tempVaccinando = StrutturaAppoggio.getVaccinando();
        user.setText(tempVaccinando.getNome() + " " + tempVaccinando.getCognome());
        ObservableList<ConcretePrototypeAnamnesi> anamnesi = FXCollections.observableArrayList();
        try {
            Connection connectDB = DatabaseConnection.getInstance();
            ResultSet queryResult= connectDB.createStatement().executeQuery("select * from Anamnesi where CODICE_FISCALE = " + "'" + tempVaccinando.getCodice_Fiscale() + "'");
            while(queryResult.next()) {
                anamnesi.add(new ConcretePrototypeAnamnesi(Integer.parseInt(queryResult.getString(1)), queryResult.getString("Codice_Fiscale"), queryResult.getDate("Data_Ora_Controllo").toLocalDate(),queryResult.getString("Luogo"),queryResult.getString("Esito"), queryResult.getString("Nome_medico")));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        tabella.setItems(anamnesi);
        data.setCellValueFactory(new PropertyValueFactory<ConcretePrototypeAnamnesi, LocalDate>("data_ora_controllo"));
        luogo.setCellValueFactory(new PropertyValueFactory<ConcretePrototypeAnamnesi,String>("luogo"));
        esito.setCellValueFactory(new PropertyValueFactory<ConcretePrototypeAnamnesi, String>("esito"));
        nome_medico.setCellValueFactory(new PropertyValueFactory<ConcretePrototypeAnamnesi,String>("nome_medico"));
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

}
