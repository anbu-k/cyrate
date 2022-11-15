package com.example.cyrate.models;

public class BusinessListCardModel {
    private int busId;
    private String busName;
    private String busType;
    private String phoneNumber;
    private String photoUrl;
    private String hours;
    private String location;
    private int ownerId;
    private String menuLink;
    private String priceGauge;

    //-------review's stuff--------//

    private int reviewSum;
    private int reviewCount;

    /**
     *
     * @param busId
     * @param busName
     * @param busType
     * @param phoneNumber
     * @param photoUrl
     * @param hours
     * @param location
     * @param ownerId
     * @param menuLink
     * @param priceGauge
     * @param reviewSum
     * @param reviewCount
     */
    public BusinessListCardModel(int busId, String busName, String busType, String phoneNumber, String photoUrl,
                                 String hours, String location, int ownerId, String menuLink,
                                 String priceGauge, int reviewSum, int reviewCount) {
        this.busId = busId;
        this.busName = busName;
        this.busType = busType;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.hours = hours;
        this.location = location;
        this.ownerId = ownerId;
        this.menuLink = menuLink;
        this.priceGauge = priceGauge;
        this.reviewSum = reviewSum;
        this.reviewCount = reviewCount;
    }

    /**
     *
     * @return Business ID
     */
    public int getBusId() {
        return busId;
    }

    /**
     *
     * @param busId
     */
    public void setBusId(int busId) {
        this.busId = busId;
    }

    /**
     *
     * @return Business Name
     */
    public String getBusName() {
        return busName;
    }

    /**
     *
     * @param busName
     */
    public void setBusName(String busName) {
        this.busName = busName;
    }

    /**
     *
     * @return Business Type
     */
    public String getBusType() {
        return busType;
    }

    /**
     *
     * @param busType
     */
    public void setBusType(String busType) {
        this.busType = busType;
    }

    /**
     *
     * @return Business Phone Number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return Business Profile Picture Url
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     *
     * @param photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     *
     * @return Business Hours
     */
    public String getHours() {
        return hours;
    }

    /**
     *
     * @param hours
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     *
     * @return Business Location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return Business Owner (User) ID
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     *
     * @param ownerId
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     *
     * @return Business Menu Link
     */
    public String getMenuLink() {
        return menuLink;
    }

    /**
     *
     * @param menuLink
     */
    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    /**
     *
     * @return Business Price Gauge
     */
    public String getPriceGauge() {
        return priceGauge;
    }

    /**
     *
     * @param priceGauge
     */
    public void setPriceGauge(String priceGauge) {
        this.priceGauge = priceGauge;
    }

    /**
     *
     * @return Sum of all Ratings for this Business
     */
    public int getReviewSum() {
        return reviewSum;
    }

    /**
     *
     * @param reviewSum
     */
    public void setReviewSum(int reviewSum) {
        this.reviewSum = reviewSum;
    }

    /**
     *
     * @return Total number of reviews left for this Business
     */
    public int getReviewCount() {
        return reviewCount;
    }

    /**
     *
     * @param reviewCount
     */
    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "RestaurantListCardModel{" +
                "busId=" + busId +
                ", busName='" + busName + '\'' +
                ", busType='" + busType + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", hours='" + hours + '\'' +
                ", location='" + location + '\'' +
                ", ownerId=" + ownerId +
                ", menuLink='" + menuLink + '\'' +
                ", priceGauge='" + priceGauge + '\'' +
                ", reviewSum=" + reviewSum +
                ", reviewCount=" + reviewCount +
                '}';
    }
}
