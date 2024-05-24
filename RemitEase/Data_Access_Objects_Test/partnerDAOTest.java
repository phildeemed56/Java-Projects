package projectfiles.DaoTest;

import projectfiles.model.Partner;
import projectfiles.Dao.PartnerDAOImpl;

import java.sql.SQLException;

public class partnerDAOTest {

    public static void main(String[] args) {
        // Initialize the PartnerDAOImpl
        PartnerDAOImpl partnerDAO = new PartnerDAOImpl();

        // Create a new Partner object
        Partner newPartner = new Partner("payjihyjh01", "password123",2,  "RemitEase", "Credit Union ", "USA", "City", "123 ABC Strerge");

        try {
            // Add a partner to the database
            System.out.println("Adding new partner...");
            partnerDAO.addPartner(newPartner);
            System.out.println("New partner added successfully!");
        } catch (SQLException e) {
        	System.err.println(e.getMessage());
        }
        	
        try {
            // Retrieve a partner from the database
            System.out.println("Retrieving partner...");
            Partner retrievedPartner = partnerDAO.getPartnerById(newPartner.getId());
            System.out.println("Partner retrieved: " + retrievedPartner.getName()); 
        } catch (SQLException e) {
        	System.err.println("Failed to retrive customer: "+ e.getMessage()) ;
        }
            
        try {
            	
            // Update partner details
            System.out.println("Updating partner...");
            //newPartner.setId(newPartner.getId());//invalidate test
            newPartner.setCity("Brooklyn");
            partnerDAO.updatePartner(newPartner);
            System.out.println("Partner updated successfully!");

            // Retrieve updated partner to verify changes
            System.out.println("Verifying update...");
            Partner updatedPartner = partnerDAO.getPartnerById(newPartner.getId());
            if (updatedPartner != null && "Brooklyn".equals(updatedPartner.getCity())) {
                System.out.println("Update verified: Partner city is now " + updatedPartner.getCity());
            } else {
                System.out.println("Update verification failed.");
            }

        } catch (SQLException e) {
            System.err.println( e.getMessage());
        }
        
    }
}
