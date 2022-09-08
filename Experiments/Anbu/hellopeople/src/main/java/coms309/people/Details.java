package coms309.people;


/**
 * Provides the Definition/Structure for the people row
 *
 * @author Vivek Bengre
 */

public class Details {

    private String locationName; //private String restuarantName

   // private String lastName;  

    private String address;

    private String telephone;

    public Details() {

    }

    public Details(String restaurantName, String address, String telephone) {
        this.locationName = restaurantName;
        //this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

    public String getlocationName() {
        return this.locationName;
    }

    public void setlocationName(String restaurantName) {
        this.locationName = restaurantName;
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
        return locationName + " "
               // + lastName + " "
                + address + " "
                + telephone;
    }
}
