package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.ConcretePrototypeAnamnesi;
import com.example.wegetvax.Models.Medico;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Vaccinando;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Statement;
import java.util.Random;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * AnamnesiMController è la classe che definisce l'oggetto control per l'aggiunta di anamnesi di uno specifico vaccinando da parte di un medico.
 * Queste, una volta aggiunte, verranno visualizzate dal vaccinando nella sua sezione apposita.
 */
public class AnamnesiMContreoller implements Initializable {

    @FXML
    private TextField codice_fiscale;

    @FXML
    private DatePicker data;

    @FXML
    private ComboBox<String> esito;

    @FXML
    private Button indietro;

    @FXML
    private Button inserisci;

    @FXML
    private TextField luogo;

    @FXML
    private Label user;

    @FXML
    private Label messaggio;

    @FXML
    private Label messaggio1;

    @FXML
    private Button logOut;


    private ToggleGroup button_group;

    //Primo oggetto istanziato da Prototype per anamnesi
    ConcretePrototypeAnamnesi prova = new ConcretePrototypeAnamnesi(0, "", null, "", "", "");

    /**
     * Metodo ereditato da Initializable che consente di inizializzare la pagina e il menù a tendina con i giusti valori.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        esito.getItems().removeAll(esito.getItems());
        esito.getItems().addAll("Idoneo", "Non Idoeno Temporaneo", "Non idoneo Permanente");
        esito.getSelectionModel().select("Idoneo");
        Medico tempMedico = StrutturaAppoggio.getMedico();
        user.setText("Dott. " + tempMedico.getNome() + " " + tempMedico.getCognome());
        Vaccinando v = StrutturaAppoggio.getVaccinando();
        codice_fiscale.setText(v.getCodice_Fiscale());
    }

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
     * Metodo che permette, dopo aver compilato correttamente gli appositi campi, di aggiungere una nuova Anamnesi all'interno del Db. Quest'ultima operazione è
     * effettuata richiamando il metodo 'convalida'.
     * @param event
     */
    @FXML
    void conferma(ActionEvent event) {
        if (!data.getValue().toString().isBlank() && !luogo.getText().isBlank() && !esito.getSelectionModel().getSelectedItem().isBlank())
            convalida();
        else
            messaggio.setText("Attenzione! Ci sono alcuni campi vuoti!");
    }

    /**
     * Metodo che inserisce la nuova anamnesi definita all'interno del Db.
     */
    public void convalida() {
        Medico tempMedico = StrutturaAppoggio.getMedico();
        Random rand = new Random();
        Connection connectDB = DatabaseConnection.getInstance();
        ConcretePrototypeAnamnesi anamnesi = (ConcretePrototypeAnamnesi) prova.clone();
        anamnesi.setNumero_protocollo(rand.nextInt(1000));
        anamnesi.setCodice_fiscale(codice_fiscale.getText());
        anamnesi.setData_ora_controllo(data.getValue());
        anamnesi.setLuogo(luogo.getText());
        anamnesi.setEsito(esito.getSelectionModel().getSelectedItem());
        anamnesi.setNome_medico(tempMedico.getNome() + " " + tempMedico.getCognome());
        String q1 = "INSERT INTO ANAMNESI (NUM_PROTOCOLLO,CODICE_FISCALE,DATA_ORA_CONTROLLO,LUOGO,ESITO,NOME_MEDICO) VALUES ('";
        String q2 = anamnesi.getNumero_protocollo() + "','" + anamnesi.getCodice_fiscale() + "', TO_DATE('" + anamnesi.getData_ora_controllo() + "', 'YYYY-MM-DD'), '" + anamnesi.getLuogo() + "','" + anamnesi.getEsito() + "','" + anamnesi.getNome_medico() + "')";
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
}
