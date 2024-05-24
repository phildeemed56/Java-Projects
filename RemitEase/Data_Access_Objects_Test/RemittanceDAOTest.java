package projectfiles.DaoTest;

import java.sql.SQLException;
import projectfiles.Dao.CustomerDAOImpl;
import projectfiles.Dao.PartnerDAOImpl;
import projectfiles.Dao.RecipientDAOImpl;
import projectfiles.Dao.RemittanceDAOImpl;
import projectfiles.model.Customer;
import projectfiles.model.Partner;
import projectfiles.model.Recipient;
import projectfiles.model.Remittance;


/**
 * Main application to test the RemittanceDAO class.
 
 */
public class RemittanceDAOTest {   
    public static void main(String[] args) throws SQLException {

        //Import DAO for customer, recipient and Partner so we can instantiate new objects.
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        PartnerDAOImpl partnerDAO = new PartnerDAOImpl();
        RecipientDAOImpl recipientDAO = new RecipientDAOImpl(); //need to develop

        //need to create new customer, partner,and user object.
            Customer testCustomer = customerDAO.getCustomerById("CSR100");
            Partner testPartner = partnerDAO.getPartnerById("PRT100");
            Recipient testRecipient = recipientDAO.getRecipientById(1002); 
        //debug
        System.out.println("Customer: " + testCustomer.getId());
        System.out.println("Partner: " + testPartner.getId());
        System.out.println("Recipient: " + testRecipient.getId());

        Remittance testRemittance = new Remittance(
            0,
            testCustomer,
            testRecipient,
            testPartner,
            100.00,
            100.00,
            "USD",
            "USD",
            "Pending",
            null,
            null,
            "None",
            "None"

        );
    
        //debug remittance to string
        System.out.println(testRemittance.getSenderID().getId() + " " +  testRemittance.getAmountSent());
        
        
        // Instantiate the DAO implementation
        RemittanceDAOImpl remittanceDAO = new RemittanceDAOImpl();
        

        //Test adding a remittance for the first time.
        try {
            System.out.println("Testing addRemittance...");
            int remittanceId = remittanceDAO.addRemittance(testRemittance);
            System.out.println("Remittance added successfully with ID: " + remittanceId);
            testRemittance.setTransactionId(remittanceId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //Test retrieving a remittance and return retrived remittance
        Remittance retrievedRemittance = null;
        try {
            System.out.println("Testing getRemittanceById...");
            retrievedRemittance = remittanceDAO.getRemittanceById(testRemittance.getTransactionId());
            System.out.println("Remittance retrieved: " + retrievedRemittance.getSenderID().getId() + " " + retrievedRemittance.getAmountSent());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
            
        
        
        //Test updating a remittance
        try {
            System.out.println("Testing updateRemittance...");
           // retrievedRemittance.setTransactionId( 555) ; // change to invalidate test
            retrievedRemittance.setAmountSent(10000.00); // Change a detail
            remittanceDAO.updateRemittance(retrievedRemittance);
            System.out.println("Remittance updated successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    
    
    }
}
