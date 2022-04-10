package com.example.wegetvax.Controllers;

import com.example.wegetvax.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * PrimaPaginaController è la classe che definisce il controller che gestisce la prima pagina dell'applicazione.
 */
public class PrimaPaginaController {

    @FXML
    private Button accedi;

    @FXML
    private Button registrati;

    public PrimaPaginaController()
    {
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si può effettuare il login.
     */
    @FXML
    public void accesso(ActionEvent event) throws IOException {
        App a = new App();
        a.changeScene("Login.fxml");
    }

    /**
     * Metodo che consente di passare alla pagina nella quale si può effettuare la registrazione di un utente.
     */
    @FXML
    public void registrazione(ActionEvent event) throws IOException{
        App a = new App();
        a.changeScene("Registrazione.fxml");
    }


}