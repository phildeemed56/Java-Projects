package projectfiles.DaoTest;

import projectfiles.Dao.RemittanceListDAOImpl;
import projectfiles.model.RemittanceList;

public class RemitanceListDAOTest {
    public static void main(String[] args) {
        //isntanteia a remitance list
        RemittanceList remittancelist = new RemittanceList();

        //instancia o DAO
        RemittanceListDAOImpl remittanceListDAO = new RemittanceListDAOImpl();
        
        //Test retrieving a remittance list. try with resources

        try {
            System.out.println("Testing getRemittanceList...");
            remittancelist = remittanceListDAO.getRemittanceList("CSR100");
            System.out.println("Remittance list retrieved: " );

            //traverse through remitana list
            for (int i = 0; i < remittancelist.getRemittanceList().size(); i++) {
                System.out.println("Remittance ID: " + remittancelist.getRemittanceList().get(i).getTransactionId());
                System.out.println("Amount: " + remittancelist.getRemittanceList().get(i).getAmountSent());
                System.out.println("Recipient ID: " + remittancelist.getRemittanceList().get(i).getRecipientID().getId());
                System.out.println("Customer ID: " + remittancelist.getRemittanceList().get(i).getSenderID().getFirstName());
                System.out.println("Date: " + remittancelist.getRemittanceList().get(i).getCreatedAt());
                System.out.println("Status: " + remittancelist.getRemittanceList().get(i).getStatus());
                System.out.println("Description: " + remittancelist.getRemittanceList().get(i).getUpdatedAt());
                System.out.println(" ");
                
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
