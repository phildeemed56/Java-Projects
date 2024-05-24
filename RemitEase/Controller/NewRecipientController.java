package projectfiles.control;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewRecipientController {

    @FXML
    private ComboBox<String> selectionComboBox;

    @FXML
    private TextArea selectionTextArea;

    @FXML
    private TextField amountSendTextField;

    @FXML
    private TextField amountReceiveTextField;

    @FXML
    private Label localCurrencyLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button continueButton;

    private final double EXCHANGE_RATE_EURO = 0.94;
    private final double EXCHANGE_RATE_CEDIS = 13.40;

    @FXML
    private void initialize() {
        // Populate the ComboBox with countries
        selectionComboBox.getItems().addAll("Ghana", "Ecuador");

        // Set action listener for the ComboBox selection
        selectionComboBox.setOnAction(event -> updateLocalCurrencyLabel());

        // Set action listener for the amountSendTextField to update amountReceiveTextField
        amountSendTextField.textProperty().addListener((observable, oldValue, newValue) -> updateAmountReceive());
    }

    private void updateLocalCurrencyLabel() {
        String selectedCountry = selectionComboBox.getValue();
        if ("Ghana".equals(selectedCountry)) {
            localCurrencyLabel.setText("Cedis");
        } else if ("Ecuador".equals(selectedCountry)) {
            localCurrencyLabel.setText("Euro");
        }
    }

    private void updateAmountReceive() {
        String amountSendStr = amountSendTextField.getText();
        if (!amountSendStr.isEmpty()) {
            double amountSend = Double.parseDouble(amountSendStr);
            String selectedCountry = selectionComboBox.getValue();
            if ("Ghana".equals(selectedCountry)) {
                double amountReceive = amountSend * EXCHANGE_RATE_CEDIS;
                amountReceiveTextField.setText(String.format("%.2f", amountReceive));
            } else if ("Ecuador".equals(selectedCountry)) {
                double amountReceive = amountSend * EXCHANGE_RATE_EURO;
                amountReceiveTextField.setText(String.format("%.2f", amountReceive));
            }
        }
    }

 // Event handler for the backButton
    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            // Load the TransactionInitiation.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/TransactionInitiation.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Transaction Initiation");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Event handler for the continueButton
    @FXML
    private void handleContinueButton(ActionEvent event) {
        try {
            // Load the Partner.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/Partner.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Partner");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}