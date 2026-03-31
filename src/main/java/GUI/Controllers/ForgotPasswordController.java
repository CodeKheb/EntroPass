package GUI.Controllers;

import Database.UserOperations;
import GUI.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ForgotPasswordController {

    @FXML
    private CheckBox acknowledgementCheckbox;

    @FXML
    private Button cancelButton;

    @FXML
    private Button resetButton;

    void initialize() {
        // Prevents the player from clicking reset if the checkbox was not clicked
        resetButton.disableProperty().bind(acknowledgementCheckbox.selectedProperty().not());
    }

    @FXML
    void resetVault(ActionEvent event) throws SQLException {
        UserOperations.purgeVault();
    }

    @FXML
    void switchToAuthScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/AuthMenu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/password_generator_gui/stylesheets/AuthStyleSheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
