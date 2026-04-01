package GUI.Controllers;

import GUI.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import org.Password_Generator.Configurator;
import org.Password_Generator.StrengthChecker;

import java.io.IOException;
import java.util.Objects;

public class SignUpController {

    @FXML
    private ProgressBar masterKeyStrength;

    @FXML
    private PasswordField masterykeyField;

    @FXML
    private Button setKey;

    @FXML
    private Button signInButton;

    @FXML
    public void initialize() {
        masterykeyField.textProperty().addListener((observable, oldValue, newValue) -> {
            double entropy = StrengthChecker.getStrength(getConfiguration(getMasterKey()), getMasterKeyLength());
            double strength = StrengthChecker.checkStrength(entropy);
            masterKeyStrength.setProgress(strength);
        });
    }

    @FXML
    void setMasterKey(ActionEvent event) {

    }

    @FXML
    void switchToSignInScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/AuthMenu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/password_generator_gui/stylesheets/AuthStyleSheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    //getter methods
    private String getMasterKey() { return masterykeyField.getText();}
    private int getMasterKeyLength() {return masterykeyField.getLength();}
    private Configurator getConfiguration(String masterKey) {
        boolean hasLower   = masterKey.matches(".*[a-z].*");
        boolean hasUpper   = masterKey.matches(".*[A-Z].*");
        boolean hasNumbers = masterKey.matches(".*[0-9].*");
        boolean hasSymbols = masterKey.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}].*");

        return new Configurator(hasNumbers, hasSymbols, hasUpper, hasLower);
    }

    public void switchToMenuScene(ActionEvent actionEvent) {

    }
}
