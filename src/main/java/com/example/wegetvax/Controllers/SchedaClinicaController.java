package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Models.Medico;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Vaccinando;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * SchedaClinicaController è la classe che definisce il controller che gestisce la scheda clinica di un vaccinando e visualizzata da un medico.
 */
public class SchedaClinicaController implements Initializable {

    @FXML
    private Button Indietro;

    @FXML
    private Button anamnesi;

    @FXML
    private Label codice_fiscale;

    @FXML
    private Label cognome;

    @FXML
    private Button logOut;

    @FXML
    private Label nome;

    @FXML
    private Button prenotazione;

    @FXML
    private Label tessera_sanitaria;

    @FXML
    private Label user;

    @FXML
    private Button vaccinazione;

    /**
     * Metodo che consente di passare alla pagina nella quale si possono aggiungere nuove anamnesi.
     */
    @FXML
    void anamnesis(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("AnamnesiM.fxml");
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
     * Metodo che consente di passare alla pagina nella quale si possono aggiungere nuove prenotazioni.
     */
    @FXML
    void reservation(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("PrenotazioneM.fxml");
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si possono aggiungere nuove vaccinazioni.
     */
    @FXML
    void vaccination(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("VaccinazioneM.fxml");
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
        Vaccinando v = StrutturaAppoggio.getVaccinando();
        nome.setText(v.getNome());
        cognome.setText(v.getCognome());
        codice_fiscale.setText(v.getCodice_Fiscale());
        tessera_sanitaria.setText(v.getTessera_Sanitaria());
        Medico m = StrutturaAppoggio.getMedico();
        user.setText("Dott. " + m.getNome() + " " + m.getCognome());
    }
}