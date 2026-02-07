module org.example.password_generator_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires passay;

    opens org.password_generator to javafx.fxml;
    exports org.password_generator;
    exports GUI;
    opens GUI to javafx.fxml;
}