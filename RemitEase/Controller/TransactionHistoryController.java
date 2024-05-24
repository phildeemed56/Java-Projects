package projectfiles.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projectfiles.model.Recipient;
import projectfiles.model.RecipientList;
import projectfiles.model.Remittance;
import projectfiles.model.RemittanceList;
import projectfiles.model.User;
import projectfiles.Dao.RemittanceDAOImpl;
import projectfiles.Dao.RemittanceListDAOImpl;
import projectfiles.app.SessionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class TransactionHistoryController {
    @FXML
    private Button backButton;

	
    @FXML
    private ListView<Remittance> recipientsListView;
    
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        openWelcomePage(event);
    }
    User user = SessionManager.getInstance().getCurrentUser();

    
    private void openWelcomePage(ActionEvent event) {
      
        	 try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/MainMenu.fxml"));
                 Parent root = loader.load();
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root));
                 stage.setTitle("Main Menu");
                 stage.show();


             } catch (IOException e) {
                 e.printStackTrace();
             }
        }
    
    
    


    public void initialize() {

    	//Instantiate a remittance list
        RemittanceList remittanceList = new RemittanceList();

        //Instantiate a DAO for remittance list
        RemittanceListDAOImpl remittanceListDAO = new RemittanceListDAOImpl();
        
        try {
        	remittanceList = remittanceListDAO.getRemittanceList(user.getId());
        }catch (SQLException e) {
    	}
    
        recipientsListView.getItems().addAll(remittanceList.getRemittanceList());
          		
        recipientsListView.setCellFactory(listView -> new RecipientCell());
      

    }		
        		


    static class RecipientCell extends ListCell<Remittance> {
        HBox hbox = new HBox(10);  // Added spacing
        Text transactionId = new Text();
        Text amount = new Text();
        Text date = new Text();
        Text status = new Text();
        Text recipientName = new Text();
        
        Text delimiter = new Text(" - ");
        
        Button sendAgainButton = new Button("Send Again");
        Button cancelButton = new Button("Cancel");
        Pane spacer = new Pane();
    
        public RecipientCell() {
            super();
            hbox.getChildren().addAll(transactionId, delimiter, recipientName, amount, date, status, spacer, sendAgainButton, cancelButton);
            HBox.setHgrow(spacer, Priority.ALWAYS);
            configureButtons();
        }
    
        private void configureButtons() {
            cancelButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
            sendAgainButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        }
    
        @Override
        protected void updateItem(Remittance remittance, boolean empty) {
            super.updateItem(remittance, empty);
            if (empty || remittance == null) {
                setText(null);
                setGraphic(null);
            } else {
                transactionId.setText("RE" + remittance.getTransactionId());
                recipientName.setText(formatName(remittance.getRecipientID()));
                amount.setText(String.format("$%.2f", remittance.getAmountSent()));
                date.setText(remittance.getCreatedAt().toString());
                status.setText(remittance.getStatus());
    
                sendAgainButton.setOnAction(e ->sendAgain(remittance));
                cancelButton.setOnAction(e -> cancelRemittance(e, remittance));
    
                // Enable or disable cancel button based on the status
                cancelButton.setDisable(!"Pending".equals(remittance.getStatus()));
    
                setGraphic(hbox);
            }
        }
    
        private String formatName(Recipient recipient) {
            return recipient != null ? recipient.getFirstName() + " " + recipient.getLastName() : "Unknown";
        }
    
    private void cancelRemittance(ActionEvent event, Remittance remittance) {
    // Create a confirmation dialog
    Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this remittance?");
    confirmDialog.setHeaderText("Confirm Cancellation");
    confirmDialog.setTitle("Cancellation Confirmation");

    Optional<ButtonType> result = confirmDialog.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Prompt for cancellation reason
        TextInputDialog reasonDialog = new TextInputDialog();
        reasonDialog.setTitle("Cancellation Reason");
        reasonDialog.setHeaderText("Enter the reason for cancellation:");
        reasonDialog.setContentText("Reason:");

        Optional<String> reason = reasonDialog.showAndWait();
        reason.ifPresent(r -> {
            remittance.setCancellationReason(r);
            remittance.setStatus("Cancelled");

            try {
                // Update remittance in the database using the DAO implementation
                RemittanceDAOImpl remittanceDAO = new RemittanceDAOImpl();
                remittanceDAO.updateRemittance(remittance);

                // Update UI elements
                status.setText(remittance.getStatus());
                cancelButton.setDisable(true);

                // Notify user of successful cancellation
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Remittance successfully cancelled.");
                successAlert.setHeaderText(null);
                successAlert.showAndWait();
            } catch (SQLException e) {
                // Handle SQL errors during update
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to cancel remittance: " + e.getMessage());
                errorAlert.setHeaderText(null);
                errorAlert.showAndWait();
                e.printStackTrace();
            }
        });
         }   
    }

    private void sendAgain(Remittance remittance) {
        try {
            // Logic to handle what "Sending Again" should do
            // For example, reloading transaction data or sending a notification
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/Transaction.fxml"));
            Parent root = loader.load();
            TransactionController transactionController = loader.getController();
            
            // Assuming transactionController has a method to set and handle the recipient
            transactionController.setRecipient(remittance.getRecipientID());
            transactionController.setsendAmount(remittance.getAmountSent());
            transactionController.setPartnerComboBoxValue(remittance);
            
            // If there's any initialization needed after setting the recipient
            transactionController.postInitialize(remittance.getRecipientID());
            
            // Display the transaction interface
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Resend Transaction"); // Set a meaningful title
            stage.show();
            
            // Optionally, send an email or another form of notification
            //deprecated by me jorge, what you gonna do?
            //recipient.sendEmailUpdate("Sending transaction details again to: " + recipient.getEmail());
    
        } catch (IOException e) {
            System.out.println("Failed to load the transaction view.");
            e.printStackTrace();
        } catch (Exception e) { // Catch other possible exceptions
            System.out.println("An error occurred while sending the transaction again.");
            e.printStackTrace();
        }
    }

    }

        



    }
    

