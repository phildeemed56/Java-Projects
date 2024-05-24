package projectfiles.control;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class WelcomePageController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


	    @FXML
	    private void handleLoginButtonAction(ActionEvent event) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/Login.fxml"));
	            Parent root = loader.load();
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Login");
	            stage.show();
	            
	         // Close the current welcome page window
	            Stage welcomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            welcomeStage.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @FXML
	    private void handleSignUpButtonAction(ActionEvent event) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/SignUp.fxml"));
	            Parent root = loader.load();
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Sign Up");
	            stage.show();
	            
	         // Close the current welcome page window
	            Stage welcomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            welcomeStage.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
