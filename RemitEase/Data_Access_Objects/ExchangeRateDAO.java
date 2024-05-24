package projectfiles.Dao;

import java.sql.SQLException;

import projectfiles.model.ExchangeRateManager;

public interface ExchangeRateDAO {
    /**
     * This method is used to get the exchange rate of the currency by target country
     * @param targetCountry A string that represents the target country
     * @return A ExchangeRate object that represents the exchange rate of the currency
     */
    ExchangeRateManager getExchangeRateByCountry(String targetCountry) throws SQLException;
    
}
