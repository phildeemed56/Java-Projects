package projectfiles.DaoTest;

import java.sql.SQLException;

import projectfiles.Dao.RecipientDAOImpl;
import projectfiles.Dao.RemittanceListDAOImpl;
import projectfiles.model.Recipient;
import projectfiles.model.RecipientList;
import projectfiles.model.Remittance;
import projectfiles.model.RemittanceList;

/**
 * This class tests the RecipientDAO class.
 */
public class RecipientDAOTest {

    public static void main(String[] args) {
    
    // Create a test recipient
    Recipient testRecipient = new Recipient(
        0 , // no id
        "emailfsf@gmail.com",
        "Jorge",
        "Alvarez",
        "702-888-8888",
        "USA",
        "Las Vegas",
        "123 Test St."

    ); 

    //Instantiate the DAO implementationq
    RecipientDAOImpl recipientDAO = new RecipientDAOImpl();
    int AddedrecipienID = 0;
    //Test adding recipient
    try {
        System.out.println("Testing addRecipient...");
        AddedrecipienID = recipientDAO.addRecipient(testRecipient);
        
        System.out.println("Recipient added successfully. the generated ID is: " + AddedrecipienID) ;
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    
    }

    Recipient retrievedRecipient = null;
    //test retrieving a recipient
    try {
        System.out.println("Testing getRecipientById...");
         retrievedRecipient = recipientDAO.getRecipientById(AddedrecipienID);
        System.out.println("Recipient retrieved: " + retrievedRecipient.getId() + " " + retrievedRecipient.getLastName());
     } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
    
    //test updating a 
    try {
        System.out.println("Testing updateRecipient...");
        //retrievedRecipient.setId(8759879); //invalidate test
        retrievedRecipient.setCity("Henderson");
        recipientDAO.updateRecipient(retrievedRecipient);
        System.out.println("Recipient updated successfully.");
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }




    //test receipientList from remittancList
    
    try {
        RecipientList recipientsList = new RecipientList();
        RemittanceList remittanceList = new RemittanceList();
        
        //Instantiate a DAO for remittance list
        RemittanceListDAOImpl remittanceListDAO = new RemittanceListDAOImpl();
        
        remittanceList = remittanceListDAO.getRemittanceList("CSR100");
    
        recipientsList.extractRecipientsFromRemittanceList(remittanceList);
    
        //traverse thorugh remiitance list
        for (int i = 0; i < remittanceList.getRemittanceList().size(); i++) {
            System.out.println("Remittance ID: " + remittanceList.getRemittanceList().get(i).getTransactionId());
            System.out.println("Amount: " + remittanceList.getRemittanceList().get(i).getAmountSent());
            System.out.println("Recipient ID: " + remittanceList.getRemittanceList().get(i).getRecipientID().getId());
            System.out.println("Customer ID: " + remittanceList.getRemittanceList().get(i).getSenderID().getFirstName());
            System.out.println("Date: " + remittanceList.getRemittanceList().get(i).getCreatedAt());
            System.out.println("Status: " + remittanceList.getRemittanceList().get(i).getStatus());
            System.out.println("Description: " + remittanceList.getRemittanceList().get(i).getUpdatedAt());
            System.out.println(" ");
            
        }
        //traverse thorugh recipient list
        for (int i = 0; i < recipientsList.getRecipientsList().size(); i++) {
            System.out.println("Recipient ID: " + recipientsList.getRecipientsList().get(i).getId());
            System.out.println("Recipient Email: " + recipientsList.getRecipientsList().get(i).getEmail());
            System.out.println("Recipient First Name: " + recipientsList.getRecipientsList().get(i).getFirstName());
            System.out.println("Recipient Last Name: " + recipientsList.getRecipientsList().get(i).getLastName());
            System.out.println("Recipient Country: " + recipientsList.getRecipientsList().get(i).getCountry());
            System.out.println("Recipient City: " + recipientsList.getRecipientsList().get(i).getCity());
            System.out.println("Recipient Address: " + recipientsList.getRecipientsList().get(i).getAddress());
            System.out.println(" ");
            
        } 
        
    }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    
} 
}
