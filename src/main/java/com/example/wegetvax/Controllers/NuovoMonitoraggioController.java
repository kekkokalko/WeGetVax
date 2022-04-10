package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.Monitoraggio;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * NuovoMonitoraggioController è la classe che definisce il controller dell'operazione di aggiunta dei monitoraggi sullo stato fisico e della salute di un vaccinando.
 */
public class NuovoMonitoraggioController implements Initializable {

        @FXML
        private DatePicker data;

        @FXML
        private ComboBox<String> dolori_muscolari;

        @FXML
        private TextField frequenza_cardiaca;

        @FXML
        private Button indietro;

        @FXML
        private Button inserisci;

        @FXML
        private Button logOut;

        @FXML
        private ComboBox<String> mal_di_gola;

        @FXML
        private ComboBox<String> mal_di_testa;

        @FXML
        private Label messaggio;

        @FXML
        private Label messaggio1;

        @FXML
        private ComboBox<String> nausea;

        @FXML
        private ComboBox<String> respirazione;

        @FXML
        private ComboBox<String> situazione_psicologica;

        @FXML
        private TextField temperatura;

        @FXML
        private ComboBox<String> tosse;

        @FXML
        private Label user;

    /**
     * Metodo che permette l'aggiunta di un nuovo monitoraggio. Richiama il metodo 'convalida'.
     * @param event
     */
    @FXML
    void aggiungi(ActionEvent event) {
        if (!data.getValue().toString().isBlank() && !temperatura.getText().isBlank() && !mal_di_gola.getSelectionModel().getSelectedItem().isBlank()
                && !mal_di_testa.getSelectionModel().getSelectedItem().isBlank() && !dolori_muscolari.getSelectionModel().getSelectedItem().isBlank() &&
                !nausea.getSelectionModel().getSelectedItem().isBlank()  && !situazione_psicologica.getSelectionModel().getSelectedItem().isBlank()  &&
                !tosse.getSelectionModel().getSelectedItem().isBlank() && !respirazione.getSelectionModel().getSelectedItem().isBlank() && !frequenza_cardiaca.getText().isBlank())
            convalida();
        else {
            messaggio.setText("Attenzione! Ci sono alcuni campi vuoti!");
            messaggio1.setText("");
        }
    }

    /**
     * Metodo che consente, dopo aver compilato i campi dell'interfacccia, l'aggiunta nel Db del monitoraggio.
     */
    public void convalida() {
        Vaccinando v = StrutturaAppoggio.getVaccinando();
        /* Istanzio un nuovo monitoraggio non attraverso il suo costruttore ma attraverso MonitoragioBuilder. Si sfruttano i metodi setter a 'cascata' e il metodo 'build'
           che costuisce l'oggetto sulla base dei valori di ritorno dei setter. Il vantaggio è che non si costruisce un costruttore con tanti parametri e ricordarsi dell'ordine
           della lista di inizializzazione. I metori setter, infatti, sono sparpagliati e non seguono tale ordine. Questo perchè l'ordine di default è stato definito nel Builder.
         */

        Monitoraggio m = new Monitoraggio.MonitoraggioBuilder()
                .setData(data.getValue())
                .setTemperatura(Integer.parseInt(temperatura.getText()))
                .setMalDiGola(mal_di_gola.getSelectionModel().getSelectedItem())
                .setMalDiTesta(mal_di_testa.getSelectionModel().getSelectedItem())
                .setDoloriMuscolari(dolori_muscolari.getSelectionModel().getSelectedItem())
                .setNausea(nausea.getSelectionModel().getSelectedItem())
                .setSituazionePsicologica(situazione_psicologica.getSelectionModel().getSelectedItem())
                .setTosse(tosse.getSelectionModel().getSelectedItem())
                .setRespirazione(respirazione.getSelectionModel().getSelectedItem())
                .setFrequenzaCardiaca(Integer.parseInt(frequenza_cardiaca.getText()))
                .build();
        Connection connectDB = DatabaseConnection.getInstance();
        String q1 = "INSERT INTO MONITORAGGIO (CODICE_FISCALE,DATA,TEMPERATURA,MAL_DI_GOLA,MAL_DI_TESTA,DOLORI_MUSCOLARI,NAUSEA,SITUAZIONE_PSICOLOGICA,TOSSE,RESPIRAZIONE,FREQUENZA_CARDIACA) VALUES ('";
        String q2 = v.getCodice_Fiscale() +"', TO_DATE('" + m.getData() + "', 'YYYY-MM-DD'), '" + m.getTemperatura() + "','" + m.getMalDiGola() + "','" + m.getMalDiTesta() + "','" + m.getDoloriMuscolari()
                + "','" + m.getNausea() + "','" + m.getSituazionePsicologica() + "','" + m.getTosse() + "','" + m.getRespirazione() + "','" + m.getFrequenzaCardiaca() + "')";
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
     * Metodo che consente, su apposita operazione di click sul bottone 'Indietro', di ritornare alla pagina precedente.
     * @param event
     * @throws IOException
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        App a = new App();
        a.changeScene("Monitoraggi.fxml");
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
     * Metodo ereditato da Initializable che consente di inizializzare la pagina.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Vaccinando v = StrutturaAppoggio.getVaccinando();
        user.setText(v.getNome() + " " + v.getCognome());
        mal_di_gola.getItems().removeAll(mal_di_gola.getItems());
        mal_di_gola.getItems().addAll("Si", "No");
        mal_di_gola.getSelectionModel().select("No");
        mal_di_testa.getItems().removeAll(mal_di_testa.getItems());
        mal_di_testa.getItems().addAll("Si", "No");
        mal_di_testa.getSelectionModel().select("No");
        dolori_muscolari.getItems().removeAll(dolori_muscolari.getItems());
        dolori_muscolari.getItems().addAll("Si", "No");
        dolori_muscolari.getSelectionModel().select("No");
        nausea.getItems().removeAll(nausea.getItems());
        nausea.getItems().addAll("Si", "No");
        nausea.getSelectionModel().select("No");
        situazione_psicologica.getItems().removeAll(situazione_psicologica.getItems());
        situazione_psicologica.getItems().addAll("Sereno", "Abbastanza sereno", "Abbastanza preoccupato", "Molto preoccupato");
        situazione_psicologica.getSelectionModel().select("Sereno");
        tosse.getItems().removeAll(tosse.getItems());
        tosse.getItems().addAll("Non presente", "Secca", "Grassa","Secca e stizzosa","Secca e insistente");
        tosse.getSelectionModel().select("Non presente");
        respirazione.getItems().removeAll(respirazione.getItems());
        respirazione.getItems().addAll("Normale", "Respiro corto","Difficoltà a respirare e parlare");
        respirazione.getSelectionModel().select("Normale");
    }
}
