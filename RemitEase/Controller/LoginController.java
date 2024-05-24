package projectfiles.control;
import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectfiles.Dao.UserDAOImpl;
import projectfiles.app.SessionManager;
import projectfiles.model.User;
import javafx.event.ActionEvent;

public class LoginController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private TextField userIDTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    @FXML
    private Button resetButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // Add action listeners for buttons
        loginButton.setOnAction(event -> login());
        backButton.setOnAction(event -> openWelcomePage());
        resetButton.setOnAction(event -> resetFields());
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        openWelcomePage();
    }

    @FXML
    private void resetFields() {
        // Clear the text fields
        userIDTextField.clear();
        passwordTextField.clear();
    }
    
    @FXML
    private void login(ActionEvent event) {
        login(); // Call the login method
    }

    private void login() {
       User localuser = new User(
       userIDTextField.getText(),
       passwordTextField.getText(),
       1
       );

        //dao for user
        UserDAOImpl userDAO = new UserDAOImpl();
        
        User remoteUser = new User();
        try { 
        	remoteUser = userDAO.getUserById(localuser.getId());
    		
        }catch (SQLException e) {
        	
        }


       // Here you would check if the userID and password match those in the database
       // For demonstration purposes, let's assume userID is "admin" and password is "password"
       if (localuser.authenticate(remoteUser.getPassword())) {
            SessionManager.getInstance().setCurrentUser(remoteUser);
            statusLabel.setText("Login successful");
            openMainMenu();
       } else {
           statusLabel.setText("Error: User ID or Password is incorrect.");
       }
   
    }
    
    
    
    @FXML
    private void openWelcomePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/projectfiles/view/WelcomePage.fxml"));
            Stage stage = (Stage) userIDTextField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/MainMenu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Main Menu");
            stage.show();

            // Close the current login window
            Stage loginStage = (Stage) userIDTextField.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
