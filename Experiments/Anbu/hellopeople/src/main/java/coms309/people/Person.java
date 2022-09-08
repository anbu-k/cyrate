package coms309.people;


/**
 * Provides the Definition/Structure for the people row
 *
 * @author Vivek Bengre
 */

public class Person {

    private String restaurantName; //private String restuarantName

   // private String lastName;  

    private String address;

    private String telephone;

    public Person() {

    }

    public Person(String restaurantName, String address, String telephone) {
        this.restaurantName = restaurantName;
        //this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

    public String getrestaurantName() {
        return this.restaurantName;
    }

    public void setrestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

   // public String getLastName() {
    //    return this.lastName;
   // }

    //public void setLastName(String lastName) {
    //    this.lastName = lastName;
   // }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return restaurantName + " "
               // + lastName + " "
                + address + " "
                + telephone;
    }
}
