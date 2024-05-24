package projectfiles.Dao;

import java.sql.SQLException;
import java.util.List;

import projectfiles.model.Partner;

public interface PartnerDAO {
    /**
     * Adds a new partner object to the database. 
     * see {@link PartnerDaoImpl#addPartner(Partner) addPartner(Partner)}
     * @param partner
     * @throws SQLException
     */
	void addPartner(Partner partner) throws SQLException;  // Now correctly declaring the exception

    /**
     * Partner method for retrieving a {@link Partner} object by their {@link Partner#getCountry() Country}.
     * See {@link PartnerDAOImpl#getPartnerById(String) getPartnerById(String)}
     * @param Country The ID of the Partner to retrieve.
     * @return The Partner object if found, otherwise null.
     */
    List<Partner> getPartnerByCountry(String Country) throws SQLException;

    /**
     * Update the details of a partner in the Partner database. 
     * See {@link PartnerDAOImpl#updatePartner(Partner) updatePartner(Partner)}
     * @param partner
     * @throws SQLException
     */
    void updatePartner(Partner partner) throws SQLException;

    /**
     * Partner method for retrieving a {@link Partner} object by their {@link Partner#getName() Name} and {@link Partner#getCountry() Country}.
     * See {@link PartnerDAOImpl#getPartnerByNameAndCountry(String, String) getPartnerByNameAndCountry(String, String)}
     * @param name The name of the Partner to retrieve.
     * @param country The country of the Partner to retrieve.
     * @return The Partner object if found, otherwise null.
     */
    public Partner getPartnerByNameAndCountry(String name, String country) throws SQLException;

    /**
     * Retrieve a {@link Partner} object from the database using the {@link Partner#getId() PartnerId}.
     * See {@link PartnerDAOImpl#getPartnerById(String) getPartnerById(String)}
     * @param countryId The ID of the partner to retrieve.
     * @return The partner object if found, otherwise null.
     * @throws SQLException If an error occurs during the database operation.
     * @throws SQLException If no partner is found with the given ID.
     */
    public Partner getPartnerById(String countryId) throws SQLException;


    
    public boolean countryServed(String country) throws SQLException;




}
