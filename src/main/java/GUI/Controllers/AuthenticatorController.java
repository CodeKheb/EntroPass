package GUI.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Encryption.ValidatePassword;
import GUI.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthenticatorController {

    @FXML
    private PasswordField AuthTextField;

    @FXML
    private Label validatorLabel;

    @FXML
    private Button UnlockButton;

    @FXML
    void initialize() {
        validatorLabel.setVisible(false);

        AuthTextField.setOnMouseClicked(event -> {
            validatorLabel.setVisible(false);
        });
    }

    @FXML
    private boolean validateLogIn() throws IOException {
        String hashedPass = "$2a$12$yjGunLLYocir1U6fpY6tPOtJflUFG..wWVaLofXXMlDU8.81/USXW";
        return ValidatePassword.checkPassword(AuthTextField.getText(), hashedPass);
    }

    @FXML
    public void switchToMenuScene(ActionEvent event) throws IOException {
        if (validateLogIn()) {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/StartingMenu.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            validatorLabel.setVisible(true);
        }
    }
}
