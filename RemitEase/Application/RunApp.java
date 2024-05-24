package projectfiles.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projectfiles.control.WelcomePageController;

import java.io.IOException;

public class RunApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load WelcomePage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/WelcomePage.fxml"));
            Parent root = loader.load();
            WelcomePageController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            // Set up the scene
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome to RemitEase");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
