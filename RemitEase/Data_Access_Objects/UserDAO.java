package projectfiles.Dao;

import java.sql.SQLException;
import projectfiles.model.User;
/**
 * Interface for UserDAO
 */
public interface UserDAO {
    /**
     * Checks if a user exists in the database. See {@link UserDAOImpl#doesUserExist(String) doesUserExist(String)}
     * @param userId
     * @return
     * @throws SQLException
     * @return boolean
     */
    boolean doesUserExist(String userId) throws SQLException;

    /**
     * Adds a new user to the database. See {@link UserDAOImpl#addUser(User) addUser(User)}
     * @param userId
     * @throws SQLException
     * @return void
     */
    void addUser(User userId) throws SQLException;

    /**
     * Gets a user by their ID. See {@link UserDAOImpl#getUserById(String) getUserById(String)}
     * @param userId
     * @return User
     * @throws SQLException
     */
    User getUserById(String userId) throws SQLException;
    void updatePassword(String userId, String newPassword) throws SQLException;

}
