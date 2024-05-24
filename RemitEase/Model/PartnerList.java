package projectfiles.model;

import java.util.ArrayList;

/**
 * This class mamages a list of partners that are collaborating with the recipient
 */
public class PartnerList {
    private ArrayList<Partner> partners;

    /**
     * Default constructor for PartnerList
     */
    public PartnerList() {
        partners = new ArrayList<>();
    }

    /**
     * Method to return the list of partners
     * @return ArrayList<Partner> partners
     */
    public ArrayList<Partner> getPartners() {
        return partners;
    }

    /**
     * Method to add find partner by ther UserID
     * @param id The UserID of the partner
     * @return Partner The partner with the given UserID
     */
    public Partner findPartner(String partnerID) {
        for (Partner partner : partners) {
            if (partner.getId().equals(partnerID)) {
                return partner;
            }
        }
        return null;
    }

}
