module com.example.wegetvax {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.wegetvax to javafx.fxml;
    exports com.example.wegetvax;
    exports com.example.wegetvax.Controllers;
    opens com.example.wegetvax.Controllers to javafx.fxml;
    opens com.example.wegetvax.Models;
    exports com.example.wegetvax.Models to javafx.fxml, java.base;
}