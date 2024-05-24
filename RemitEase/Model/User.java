package projectfiles.model;

public class User {
    private String userID;
    private String password;
    int PermissionLevel;

    public User() {
        // Default constructor
    }

	/**
	 *Constructor for objects of class User.
	 *@param id The user unique identifier.
	 *@param password The user's password.
     *@param PermissionLevel The user's permission level.
	 */
    public User(String id, String password, int PermissionLevel) {
        this.userID = id;
        this.password = password;
        this.PermissionLevel = PermissionLevel;
    }
    
    /**
     * Gets the user's ID
     * @return A String representing the user's ID
     */
    public String getId() {
        return userID;
    }

    /**
     * Sets the user's ID
     * @param id A string containing the new ID for the user
     */
    public void setId(String id) {
        this.userID = id;
    }
    
    /**
     * Gets the user's password.
     * @return A string representing the users password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Sets the user's password
     * @param password A string containing the new password for the user
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's permission level
     * @return An integer representing the user's permission level
     */
    public int getPermissionLevel() {
        return PermissionLevel;
    }

    /**
     * Sets the user's permission level
     * @param PermissionLevel An integer containing the new permission level for the user
     */
    public void setPermissionLevel(int PermissionLevel) {
        this.PermissionLevel = PermissionLevel;
    }
    
	/**
	 * Authenticate a user based on the provided password.
	 * @param password
	 * @return
	 */
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    

}