package com.example.wegetvax.Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DatabaseConnection è la classe che definsice e permette di ottenere la connessione ad un Database oracle.
 */
public class DatabaseConnection {
    //Variabile statica che permette di capire se l'operazione di istanza è stata effettuata o no.
    public static Connection databaseLink=null;

    //Metodo che effettua l'operazione di istanziare un oggetto
    public static Connection getConnection(){
        String databaseName= "WeGetVax";
        String databaseUser = "system";
        String databasePassword= "kekko";
        String url ="jdbc:oracle:thin:@localhost:1521:orcl";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println("Connected to Oracle Database Server!");
        }catch(Exception e) {
            System.out.println("Ops, error:");
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

    //Metodo che ritorna un'istanza
    public static Connection getInstance() {
        if(databaseLink==null)
            databaseLink = DatabaseConnection.getConnection();
        return databaseLink;
    }

    //Costruttore privato
    private DatabaseConnection() {

    }
}
