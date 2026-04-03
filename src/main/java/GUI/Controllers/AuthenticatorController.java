package GUI.Controllers;

import java.io.IOException;
import java.util.Objects;

import Database.MasterDAO;
import Encryption.AES;
import Encryption.PDKF2;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.SecretKey;

public class AuthenticatorController {

    @FXML
    private PasswordField AuthTextField;

    @FXML
    private Label validatorLabel;

    private static final String password = MasterDAO.retrieveMasterPass();
    SecretKey key = PDKF2.deriveKey(password.toCharArray(), MasterDAO.retrieveSaltByte());

    public AuthenticatorController() throws Exception {
    }

    @FXML
    private void initialize() {
        validatorLabel.setVisible(false);

        AuthTextField.setOnMouseClicked(event -> {
            validatorLabel.setVisible(false);
        });

        AuthTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    switchToMenuScene(event);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    private boolean validateLogIn() {
        return BCrypt.checkpw(AuthTextField.getText(), password);
    }

    @FXML
    private void switchToForgetPasswordScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/ForgotPassword.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/password_generator_gui/stylesheets/ForgotPasswordStyleSheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToSignUpScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/SignUpScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/password_generator_gui/stylesheets/SignUpStyleSheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToMenuScene(ActionEvent event) throws Exception {
        if (validateLogIn()) {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/StartingMenu.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            AES.setKey(key); //inserts the key to the class.
        }
        else {
            validatorLabel.setVisible(true);
        }
    }

    @FXML
    private void switchToMenuScene(KeyEvent event) throws Exception {
        if (validateLogIn()) {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/StartingMenu.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            AES.setKey(key); //inserts the key to the class.
        }
        else {
            validatorLabel.setVisible(true);
        }
    }
}
