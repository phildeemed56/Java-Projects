package projectfiles.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import projectfiles.model.ExchangeRateManager;


public class ExchangeRateDAOImpl implements ExchangeRateDAO{

    @Override
    public ExchangeRateManager getExchangeRateByCountry(String targetCountry) throws SQLException{
        String sql = "SELECT ExchangeRateId, Country, TargetCurrency, Rate FROM ExchangeRate WHERE Country = ?";
        try (Connection conn = DatabaseCreds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, targetCountry);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ExchangeRateManager(
                        rs.getInt("ExchangeRateId"),
                        rs.getString("Country"),
                        rs.getString("TargetCurrency"),
                        rs.getDouble("Rate")
                    );
                }else{
                    throw new SQLException("Exchange Rate not found with country: "+ targetCountry );
                    }
                }   
            }
   
   
    }   

}
