package projectfiles.model;


/**
 * This model class represents the entity that handles remittance for the recipient.
 * Partners are collaborators that engage with the recipient in ther respective coutty
 */
public class Partner extends User {
    private String name;
    private String type;
    private String country;
    private String city;
    private String address;
    private double balance;

    public Partner() {
        // Default constructor
    }
    
    /**
     * Constructs a new Partner, typically set by the system or database.
     * @param id Unique identifier for the partner.
     * @param password The partners password for authentication purposes.
     * @param name The name of the partner.
     * @param type What is the role of the partner.
     */
    public Partner(String id, String password, int permissionLevel, String name, String type, String country, String city, String address) {
        super(id, password, permissionLevel); 
        this.setName(name);
        this.type = type;
        this.country = country;
        this.city = city;
        this.address = address; 
    }

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Gets the type of the partner.
     * @return A String that contains the type of the partner.
     */
    public String getType() {
        return type;
    }
    
    /**
     * Sets the type of the partner.
     * @param type A String containing the type of the type of the partner.
     */
    public void setType(String type) {
        this.type = type; //should limit this to predefined types
    }

    /**
     * The country that is being serviced by the partner.
     * @return A String containing the name of the partner's country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the name of the partners country.
     * @param country A String containing the name of the partners country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the name of the partners city.
     * @return A String containing the name of the partner's city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the name of the partners city.
     * @param city A String containing the name of the partner's city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the partner's street address.
     * @return A String containing the address of the partner.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets partner's street address.
     * @param address A String containing the address of the partner.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the partners available balance.
     * @return A double containing the partners available balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the partners balance. Deprecated by deposits and withdraw methods.
     * @param balance A double amount to replace the partners balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Deposits a specified amount to the partners balance.
     * @param amount A non negative double to be added to the partners balance.
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraws a specified amount from the partners available balance
     * @param amount
     */
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            // Handle insufficient balance
        }
    }
    
    /**
     * Method for adding a partner.
     * @param partner
     */
    public void addPartner(Partner partner) {
        // Implementation for adding a partner
    }

    
}   