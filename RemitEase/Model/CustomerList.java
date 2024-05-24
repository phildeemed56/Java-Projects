package projectfiles.model;

import java.util.ArrayList;

/**
 * This model class will handle a collection of customer objects
 */
public class CustomerList {
    private ArrayList<Customer> customerList;

    /**
     * Default constructor for the customer list.
     */
    public CustomerList() {
        customerList = new ArrayList<Customer>();   
    }

    /**
     * Method to add Customer to the list.
     * @param customer the customer object to be added to the list.
     */
    public void addCustomer(Customer customer){
        assert customerList.add(customer);
            }
        
    /**
     * Method to remove a customer from list 
     * @param customer the customer object to be removed from the ArrayList
     * @return true if the customer was removed successfully, false otherwise;
    */
    public boolean removeCustomer(Customer customer) {
        return customerList.remove(customer);
    }  

    /**
     * Method to return the Arraylist of customers
     * @return the list of customers
     */
    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }//do we need to return a list opposed to a arrayList?

    

}
