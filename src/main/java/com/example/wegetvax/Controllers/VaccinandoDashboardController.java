package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Utente;
import com.example.wegetvax.Models.Vaccinando;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import com.example.wegetvax.Controllers.LoginController;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * VaccinandoDashboardController è la classe che definisce il controller che gestisce la homepage di un generico vaccinando.
 */
public class VaccinandoDashboardController implements Initializable {

    @FXML
    private Button anamnesi;

    @FXML
    private Button logOut;

    @FXML
    private Button prenotazione;

    @FXML
    private Button vaccinazione;

    @FXML
    private Label message;

    @FXML
    private Button profilo;

    @FXML
    private Button monitoraggi;

    /**
     * Metodo che consente, su apposita operazione di click sul bottone 'LogOut', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        App a = new App();
        Alert alert = new Alert(Alert.AlertType.WARNING,"seleziona",ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Attenzione!");
        alert.setContentText("Sei sicuro di voler uscire?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            a.changeScene("PrimaPagina.fxml");
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
        Vaccinando tempCitizen = StrutturaAppoggio.getVaccinando();
        message.setText("Benvenuto nel sistema di WeGetVax " + tempCitizen.getNome());
        System.out.println(tempCitizen.getNome() + "!!!!");
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si possono visualizzare nuove anamnesi.
     */
    @FXML
    void anamnesis(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("Anamnesi.fxml");
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si possono visualizzare e aggiungere nuove prenotazioni.
     */
    @FXML
    void reservation(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("Prenotazione.fxml");
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si possono visualizzare i resoconti.
     */
    @FXML
    void vaccination(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("Vaccinazione.fxml");
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si può modificare il proprio profilo.
     */
    @FXML
    void modifica_profilo(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("ModificaProfilo.fxml");
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si possono visualizzare i monitoraggi registrati e aggiungerne altri.
     */
    @FXML
    void monitoraggio(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("Monitoraggi.fxml");
    }
}
