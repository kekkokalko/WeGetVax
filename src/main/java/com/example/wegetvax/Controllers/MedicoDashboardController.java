package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import com.example.wegetvax.Database.DatabaseConnection;
import com.example.wegetvax.Models.Medico;
import com.example.wegetvax.Models.StrutturaAppoggio;
import com.example.wegetvax.Models.Vaccinando;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;


public class MedicoDashboardController implements Initializable {

    @FXML
    private Button logOut;

    @FXML
    private TextField barra;

    @FXML
    private Button cerca;

    @FXML
    private Label message;

    @FXML
    private Button profilo;

    @FXML
    private Label messaggio;


    @FXML
    public void back(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Medico tempMedico = StrutturaAppoggio.getMedico();
        message.setText("Benvenuto nel sistema di WeGetVax Dott. " + tempMedico.getCognome());
    }

    @FXML
    void search(ActionEvent event) throws IOException {
        if(!barra.getText().isBlank())
            ricerca();
        else
            messaggio.setText("Attenzione! Inserire un codice di tessera sanitaria!");
    }

    public void ricerca() throws IOException{
        App a = new App();
        Connection connectDB = DatabaseConnection.getInstance();
        String searchQuery = "SELECT COUNT(*) FROM VACCINANDO WHERE NUM_TESSERA_SANITARIA = " + barra.getText();
        System.out.println(searchQuery);
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(searchQuery);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    String selectQuery = "SELECT * FROM VACCINANDO WHERE NUM_TESSERA_SANITARIA = " + barra.getText();
                    statement = connectDB.createStatement();
                    queryResult = statement.executeQuery(selectQuery);
                    System.out.println(selectQuery);
                    if (queryResult.next()) {
                        Vaccinando v = new Vaccinando(null, null, null, null, null, null, null, null, null, null, null, null, null);
                        v.setTessera_sanitaria(queryResult.getString(1));
                        v.setCodice_fiscale(queryResult.getString(2));
                        v.setNome(queryResult.getString(3));
                        v.setCognome(queryResult.getString(4));
                        v.setData_di_nascita(queryResult.getDate(5).toLocalDate());
                        v.setCitta_natale(queryResult.getString(6));
                        v.setCitta(queryResult.getString(7));
                        v.setProvincia(queryResult.getString(8));
                        v.setCAP(queryResult.getString(9));
                        v.setMail(queryResult.getString(10));
                        v.setUsername(queryResult.getString(11));
                        v.setPassword(queryResult.getString(12));
                        v.setNumero_telefono(queryResult.getString(13));
                        StrutturaAppoggio.setVaccinando(v);
                        a.changeScene("SchedaClinica.fxml");
                    }
                }
                else
                        messaggio.setText("Vaccinando sconosciuto! Riprovare!");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void modifica_profilo(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("ModificaProfiloM1.fxml");
    }
}
