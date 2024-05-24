package projectfiles.DaoTest;

import projectfiles.model.User;

import java.sql.SQLException;

import projectfiles.Dao.UserDAOImpl;

public class TestUserDAO {

    public static void main(String[] args) {
        UserDAOImpl userDao = new UserDAOImpl();
        User newUser = new User("ghefwfegfhgfrfergf", "testPassword", 1);
        try {
            // Test addUser
            System.out.println("Testing addUser...");
            userDao.addUser(newUser);
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add user: " + e.getMessage());
        }

        try {
            // Test doesUserExist
            System.out.println("Testing doesUserExist...");
            boolean exists = userDao.doesUserExist(newUser.getId());
            System.out.println("Does user exist? " + exists);
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try { 

            // Test getUserByID
            System.out.println("Testing getUserByID...");
            User fetchedUser = userDao.getUserById(newUser.getId());
            if (fetchedUser != null) {
                System.out.println("User fetched: " + fetchedUser.getId() + " Password: " + fetchedUser.getPassword());
            }
        }catch (SQLException e) {
            System.err.println("Failed to fetch user: " + e.getMessage());
        }

        try {

            // Test updatePassword
            System.out.println("Testing updatePassword...");
            userDao.updatePassword(newUser.getId(), "newTestPasshrtshrsthword");
            System.out.println("Password updated successfully.");

            // Verify password update
            System.out.println("Verifying password update...");
            User updatedUser = userDao.getUserById(newUser.getId());
            if (updatedUser != null && "newTestPasshrtshrsthword".equals(updatedUser.getPassword())) {
                System.out.println("Password update verified.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to update password: " + e.getMessage());
        }
    }
}
