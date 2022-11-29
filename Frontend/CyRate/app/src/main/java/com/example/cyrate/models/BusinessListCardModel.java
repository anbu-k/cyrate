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

    //-------favorites stuff--------//
    private int fid;

    public BusinessListCardModel(int busId, String busName, String busType, String phoneNumber, String photoUrl,
                                 String hours, String location, int ownerId, String menuLink,
                                 String priceGauge, int reviewSum, int reviewCount, int fid) {
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

    public int getBusId() {
        return busId;
    }

    public int getFid(){
        return fid;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public String getPriceGauge() {
        return priceGauge;
    }

    public void setPriceGauge(String priceGauge) {
        this.priceGauge = priceGauge;
    }

    public int getReviewSum() {
        return reviewSum;
    }

    public void setReviewSum(int reviewSum) {
        this.reviewSum = reviewSum;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

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
