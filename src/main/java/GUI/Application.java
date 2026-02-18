package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/org/password_generator_gui/Scenes/StartingMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Password Generator 1.5");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
