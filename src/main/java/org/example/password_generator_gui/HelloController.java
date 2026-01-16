package org.example.password_generator_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    PasswordBuilder passwordBuilder = new PasswordBuilder();
    PrimaryLogic primaryLogic = new PrimaryLogic();

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("I miss you :(( Sorry :((");
    }

    @FXML
    protected void onButtonClick() {
        welcomeText.setText(primaryLogic.generatePassword());
    }
}
