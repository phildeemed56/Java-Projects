package projectfiles.model;



/**
 * This class models  the exchange rate of currency.
 */
public class ExchangeRateManager {
     
//fields   
    private int id;
    private String country;
    private String targetCurrency;
    private double exchangeRate;


    //emptru contryctor
    public ExchangeRateManager() {
    }

    //full contryctor
    public ExchangeRateManager(int id, String country, String targetCurrency, double exchangeRate) {
        this.id = id;
        this.country = country;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }
    
    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


}
