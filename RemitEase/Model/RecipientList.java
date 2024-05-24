package projectfiles.model;

import java.util.ArrayList;


/**
 * This class manages a collection of recipients list.
 */
public class RecipientList {
    //initialize the arrayList
    private ArrayList<Recipient> recipientsList;

    /**
     * Default constructor for the RecipientList class.
     */
    public RecipientList() {
        recipientsList = new ArrayList<Recipient>();
    }

    /**
     * Method to return a list of all recipients
     * @return An ArrayList of all recipients
     */
    public ArrayList<Recipient> getRecipientsList() {
        return recipientsList;
    }


    /**
     * Method to find a recipient by their ID
     * @param recipientId The ID of the recipient to find
     */
    public Recipient findRecipientById(int recipientId) {
        for (Recipient recipient : recipientsList) {
            if (recipient.getId() == recipientId) {
                return recipient;
            }
        }
        return null;
    }


    /**
     * Method to extract a list of recipients form a {@link RemittanceList} 
     * @param remittanceList The list of remittances to extract recipients from
     * @return An ArrayList of recipients
     */

     public void extractRecipientsFromRemittanceList(RemittanceList remittanceList) {
        for (Remittance remittance : remittanceList.getRemittanceList()) {
            Recipient recipient = remittance.getRecipientID();
            if (!recipientsList.contains(recipient)) {
                recipientsList.add(recipient);
            }
        }
    
    }

}