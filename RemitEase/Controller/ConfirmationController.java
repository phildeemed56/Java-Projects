package projectfiles.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import projectfiles.model.Recipient;
import projectfiles.model.Remittance;


//Correct import
import javafx.scene.control.TextArea;

import java.awt.TextField;
import java.io.IOException;
import java.sql.SQLException;

public class ConfirmationController {

    @FXML
    private Button mainMenuButton;
	
    private Remittance newRemittance;
    
    @FXML
    private TextField confirmationText;


  
    @FXML
    void handleMainMenuButtonAction(ActionEvent event) {
        openMainMenu(event);
    }
    
    @FXML
    private void openMainMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/projectfiles/view/MainMenu.fxml"));
            Stage stage = (Stage) mainMenuButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRemittance(Remittance sendRemittance) {
        this.newRemittance = sendRemittance;
        
    }
    
    public void postInitialize(Remittance remittance) throws SQLException {
    	setRemittance(remittance);

    }
    
//    private void updateConfirmationText() {
//        // Check if newRemittance is not null to avoid NullPointerException
//        if (newRemittance != null) {
//            confirmationText.setText(newRemittance.toString());
//
//        }else {
//        	System.out.print(newRemittance.getTransactionId());
//        }
    }

