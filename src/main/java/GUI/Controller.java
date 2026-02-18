package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.password_generator.PasswordBuilder;
import org.password_generator.PasswordConfiguration;
import org.password_generator.PasswordStrengthChecker;

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToMenuScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/StartingMenu.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToPasswordBuilderScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/PasswordBuilder.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Label passwordText;
    @FXML
    private TextField digitField;
    @FXML
    private CheckBox mixedCaseCheckBox;
    @FXML
    private CheckBox specialCharCheckBox;
    @FXML
    private CheckBox numberCheckBox;
    @FXML
    private Label passwordStrength;
    @FXML
    private void initialize() {
        if (digitField != null) {
            digitField.setText("8");

            //prevents users from entering anything other than numbers.
            digitField.textProperty().addListener((_, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) { //if the new value does not match any possible digit through regex checking
                    digitField.setText(oldValue); //the digitField would be set back to the old value
                }
            });
        }
    }

    private int getPasswordLength() {return Integer.parseInt(digitField.getText());}

    @FXML
    private void onButtonClick() {
        PasswordConfiguration configuration = new PasswordConfiguration(numberCheckBox.isSelected(), specialCharCheckBox.isSelected(), mixedCaseCheckBox.isSelected());
        try {
            if (getPasswordLength() < 8 || getPasswordLength() > 128) passwordText.setText("Length must be between 8 and 128 characters!");

            int passwordLength = getPasswordLength();
            setPassword(passwordLength, configuration);
            setPasswordStrength(passwordLength, configuration);
        }
        catch (NumberFormatException e) {
            passwordText.setText("Please enter a valid number");
        }
    }

    private void setPassword(int passwordLength, PasswordConfiguration configuration) {
        PasswordBuilder builder = new PasswordBuilder(passwordLength);
        passwordText.setText(builder.buildPassword(configuration));
    }

    private void setPasswordStrength(int passwordLength, PasswordConfiguration configuration) {
        passwordStrength.setText(
                PasswordStrengthChecker.
                checkStrength(configuration, passwordLength)
        );
    }
}
