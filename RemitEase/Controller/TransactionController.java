package projectfiles.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projectfiles.Dao.CustomerDAO;
import projectfiles.Dao.CustomerDAOImpl;
import projectfiles.Dao.ExchangeRateDAOImpl;
import projectfiles.Dao.PartnerDAOImpl;
import projectfiles.Dao.RecipientDAOImpl;
import projectfiles.Dao.RemittanceDAOImpl;
import projectfiles.app.SessionManager;
import projectfiles.model.ExchangeRateManager;
import projectfiles.model.Partner;
import projectfiles.model.Recipient;
import projectfiles.model.Remittance;
import projectfiles.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {

	@FXML
	private Label amountTheyReceiveLabel;
    @FXML
    private TextField amountSendTextField;

    @FXML
    private Label sendCurrency;
    @FXML
    private Label amountSendLabel;

    @FXML
    private Label amountReceiveLabel;

    @FXML
    private Label localCurrencyLabel;

    @FXML
    private TextField amountReceiveTextField;

    @FXML
    private ComboBox<String> partnerComboBox;

    @FXML
    private Label partnerLabel;

    @FXML
    private Label selectedPartnerLabel;

    @FXML
    private Label amountToBeSentLabel;

    @FXML
    private Label amountToBeSentTextField;

    @FXML
    private Label amountToBeReceivedLabel;

    @FXML
    private Label amountToBeReceivedTextField;

    @FXML
    private Label extraChargeLabel;

    @FXML
    private Label extraChargeTextField;

    @FXML
    private Label totalChargeLabel;

    @FXML
    private Label totalChargeTextField;

    @FXML
    private Button backButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button continueButton;

	public Recipient newRecipient2 ;

    User user = SessionManager.getInstance().getCurrentUser();

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        openRecipient(event, newRecipient2);
    }

    @FXML
    void handleResetButtonAction(ActionEvent event) {
        resetFields();
    }

    @FXML
    void handleContinueButtonAction(ActionEvent event) throws SQLException{
        openConfirmation(event, newRecipient2 );
    }

   

    
    
    

    

    private void openRecipient(ActionEvent event, Recipient recipient) {
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
    
    @FXML
    void initialize( ) throws SQLException{
        // Populate the partner combo box
        populatePartnerComboBox(newRecipient2);
        //System.out.println(newRecipient2.getCountry() + " I n rhw initialize trans contorllwe");

        // Add event listener to the partner combo box
        partnerComboBox.setOnAction(event -> handlePartnerComboBoxChanged());
    }
    
    private void calculateTotal() throws SQLException {
        if (amountSendTextField.getText().isEmpty() || partnerComboBox.getValue() == null) {
            return; // Do not calculate if there is no amount or no partner selected
        }

        try {
            double amountToSend = Double.parseDouble(amountSendTextField.getText());
            String selectedPartner = partnerComboBox.getValue();
            if (selectedPartner == null) {
                return; // Exit if no partner is selected
            }
            
            //DAO to bring in exchange rate
            ExchangeRateDAOImpl exchangeRateDAO = new ExchangeRateDAOImpl();
            
            ExchangeRateManager exchangerate = exchangeRateDAO.getExchangeRateByCountry(newRecipient2.getCountry());

            double conversionRate = exchangerate.getExchangeRate();  // Assume this method exists
            double additionalCharges = 4.99;  // Assume this method exists
        	double percentageccharge = .01;
            double amountReceived = amountToSend * conversionRate;
            double totalAmount = amountToSend + additionalCharges + amountToSend*percentageccharge;
            double totalfees = additionalCharges + amountToSend*percentageccharge;
            amountToBeReceivedTextField.setText(String.format("%.2f USD", amountReceived));
            amountToBeSentTextField.setText(String.format("%.2f %s", amountToSend, exchangerate.getTargetCurrency()));
            totalChargeTextField.setText(String.format("%.2f USD", totalAmount));
            extraChargeTextField.setText(String.format("%.2f USD", totalfees));
            amountTheyReceiveLabel.setText(String.format("%.2f", amountReceived));           
            
            
            // Add currency labels
            sendCurrency.setText("USD");
            localCurrencyLabel.setText(exchangerate.getTargetCurrency());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for amount.");
        }
    }

   

    private void populatePartnerComboBox(Recipient recipient) throws SQLException {
    	 if (recipient == null) {
    	        System.out.println("Recipient data is not available.");
    	        return; // or handle this case appropriately
    	    }
    	 System.out.println(newRecipient2.getId()+ " In the trans controller");
        //Dao Partner by ID
        PartnerDAOImpl partnerDAO = new PartnerDAOImpl();
        //partner array list
        List<Partner> partners = partnerDAO.getPartnerByCountry(newRecipient2.getCountry());

        // Convert the list of partners to a list of strings
        List<String> partnerNames = new ArrayList<>();
        for (Partner partner : partners) {
            partnerNames.add(partner.getName());
        }

        //Observable list of partners
        ObservableList<String> obspartners = FXCollections.observableArrayList(partnerNames);

        partnerComboBox.setItems(obspartners);
    }

    private void handlePartnerComboBoxChanged() {
        String selectedPartner = partnerComboBox.getValue();
        selectedPartnerLabel.setText(selectedPartner);
        
    }


    private void resetFields() {
        amountSendTextField.clear();
        amountTheyReceiveLabel.setText("");
        partnerComboBox.getSelectionModel().clearSelection();
        selectedPartnerLabel.setText("");
        amountToBeSentTextField.setText("");
        amountToBeReceivedTextField.setText("");
        extraChargeTextField.setText("");
        totalChargeTextField.setText("");
    }

    private void openConfirmation(ActionEvent event, Recipient recipient ) throws SQLException {
        try {

            //partner DAO 
            PartnerDAOImpl partnerDAO = new PartnerDAOImpl();
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            //Remittance object
            Remittance SendRemittance = new Remittance(
            0,
            customerDAO.getCustomerById(user.getId()),
            newRecipient2,
            partnerDAO.getPartnerByNameAndCountry(selectedPartnerLabel.getText(),newRecipient2.getCountry()),
            Double.parseDouble(amountSendTextField.getText()),
            Double.parseDouble(amountTheyReceiveLabel.getText()),
            "USD",
            localCurrencyLabel.getText(),
            "Pending",
            null,
            null,
            "None",
            "None"

        );

        //send remiitance
        RemittanceDAOImpl remittanceDAO = new RemittanceDAOImpl();
        int RemittanceID = remittanceDAO.addRemittance(SendRemittance);
        
        //set new remittance id
        SendRemittance.setTransactionId(RemittanceID);
        
        

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectfiles/view/Confirmation.fxml"));
            Parent root = loader.load();
        //get the controller isntance
         // Get the controller instance
         ConfirmationController transactionController = loader.getController();
        
         // Pass the entered Remittance to the confirmation
         
         transactionController.setRemittance(SendRemittance);
         transactionController.postInitialize(SendRemittance);
         
         
            Stage stage = (Stage) continueButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	public void setRecipient(Recipient recipient) {
		this.newRecipient2 = recipient;
	
	}

    public void postInitialize(Recipient recipient) throws SQLException {
    	setRecipient(recipient);
    	
    	 // Populate the partner combo box
        populatePartnerComboBox(newRecipient2);
        System.out.println(newRecipient2.getCountry() + " I n rhw initialize trans contorllwe");

        // Add event listener to the partner combo box
        partnerComboBox.setOnAction(event -> handlePartnerComboBoxChanged());
        
        // Add listener to amountSendTextField to react to text changes
        amountSendTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {  // Regex to ensure only numbers are entered
                amountSendTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            try {
				calculateTotal();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Recalculate whenever the text changes

            
        });
    	
       
    }

    public void setsendAmount(double amountSent) {
        amountSendTextField.setText(String.format("%.0f", amountSent));
    }

    public void setPartnerComboBoxValue(Remittance remittance) {
        partnerComboBox.setValue(remittance.getPartnerID().getName());
        selectedPartnerLabel.setText(remittance.getPartnerID().getName());
        try {
            calculateTotal();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
