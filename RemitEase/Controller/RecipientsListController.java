package projectfiles.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projectfiles.model.Recipient;
import projectfiles.model.RecipientList;
import projectfiles.model.RemittanceList;
import projectfiles.model.User;
import projectfiles.Dao.RemittanceDAOImpl;
import projectfiles.Dao.RemittanceListDAOImpl;
import projectfiles.app.SessionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class RecipientsListController {
    @FXML
    private Button backButton;

	
    @FXML
    private ListView<Recipient> recipientsListView;
    
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
        
        RecipientList recipients = new RecipientList();
//        recipients.add(new Recipient("email@example.com", "John", "Doe", "1234567890"));
//        recipients.add(new Recipient("another@example.com", "Jane", "Roe", "0987654321"));
//        recipients.add(new Recipient("another@example.com", "Jane", "Roe", "0987654321"));
//        recipients.add(new Recipient("another@example.com", "Jane", "Roe", "0987654321"));
//        recipients.add(new Recipient("another@example.com", "Jane", "Roe", "0987654321"));
//        recipients.add(new Recipient("another@example.com", "Jane", "Roe", "0987654321"));
//        recipients.add(new Recipient("another@example.com", "Jane", "Roe", "0987654321"));
    
        
    	//Instantiate a remittance list
        RemittanceList remittanceList = new RemittanceList();

        //Instantiate a DAO for remittance list
        RemittanceListDAOImpl remittanceListDAO = new RemittanceListDAOImpl();
        
        try {
        	remittanceList = remittanceListDAO.getRemittanceList(user.getId());
        }catch (SQLException e) {
    	}
    
        recipients.extractRecipientsFromRemittanceList(remittanceList);

        recipientsListView.getItems().addAll(recipients.getRecipientsList());
          		
        recipientsListView.setCellFactory(listView -> new RecipientCell());
      

    }		
        		

static class RecipientCell extends ListCell<Recipient> {
    private final HBox hbox = new HBox(10); // Add spacing between elements
    private final Text name = new Text();
    private final Text address = new Text();
    private final Text city = new Text();
    private final Text country = new Text();
    private final Text email = new Text();
    private final Text phone = new Text();

    private final Text delimiter = new Text(" - ");

    private final Button sendAgainButton = new Button("Send Again");
    private final Button updateButton = new Button("Update");
    private final Pane spacer = new Pane();

    public RecipientCell() {
        super();
        configureTextStyles();
        configureButtons();
        hbox.getChildren().addAll(name, delimiter, address, city, country, phone, email, spacer, sendAgainButton, updateButton);
        HBox.setHgrow(spacer, Priority.ALWAYS); // Ensure the buttons stay right-aligned
    }

    private void configureTextStyles() {
        name.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        address.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        city.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        country.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        email.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        phone.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
    }

    private void configureButtons() {
        sendAgainButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        updateButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        sendAgainButton.setTooltip(new Tooltip("Click to send the information again to the recipient"));
        updateButton.setTooltip(new Tooltip("Click to update recipient details"));
    }

    @Override
    protected void updateItem(Recipient recipient, boolean empty) {
        super.updateItem(recipient, empty);
        if (empty || recipient == null) {
            setText(null);
            setGraphic(null);
        } else {
            name.setText(formatName(recipient));
            address.setText(safeText(recipient.getAddress()));
            city.setText(safeText(recipient.getCity()));
            country.setText(safeText(recipient.getCountry()));
            email.setText(safeText(recipient.getEmail()));
            phone.setText(safeText(recipient.getPhoneNumber()));
            sendAgainButton.setOnAction(event -> sendAgain(recipient));
            updateButton.setOnAction(event -> updateRecipient(event, recipient));
            setGraphic(hbox);
        }
    }

    private String formatName(Recipient recipient) {
        return recipient.getFirstName() + " " + recipient.getLastName();
    }

    private String safeText(String text) {
        return text != null ? text : "";
    }

    

        private void updateRecipient(ActionEvent event, Recipient recipient) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/RecipientUpdate.fxml"));
                Parent root = loader.load();  // Load the FXML and instantiate the controller

                RecipientUpdateController controller = loader.getController();
                controller.setRecipient(recipient);  // Set the recipient
                controller.postInitialize();  // Manually initialize the parts of the controller that need the recipient

                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendAgain(Recipient recipient) {
            try {
                // Logic to handle what "Sending Again" should do
                // For example, reloading transaction data or sending a notification
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/Transaction.fxml"));
                Parent root = loader.load();
                TransactionController transactionController = loader.getController();
                
                // Assuming transactionController has a method to set and handle the recipient
                transactionController.setRecipient(recipient);
                
                // If there's any initialization needed after setting the recipient
                transactionController.postInitialize(recipient);
                
                // Display the transaction interface
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Resend Transaction"); // Set a meaningful title
                stage.show();
                
                // Optionally, send an email or another form of notification
                recipient.sendEmailUpdate("Sending transaction details again to: " + recipient.getEmail());
        
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
    

