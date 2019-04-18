package com.noideateam.braincode_noideateam.restreturn;

import java.util.ArrayList;

public class LocationArray {

    public ArrayList<Location> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Location> location) {
        this.location = location;
    }

    private ArrayList<Location> location = new ArrayList<>();

    public LocationArray(String pointName, double distance, boolean suggest, String better_street,
    String better_city, String better_zip, String notes, String shopType, String deliveryHours)
    {

        this.location.add(new Location(
                pointName, distance, true, better_street, better_city, better_zip, notes, shopType, deliveryHours
        ));
    }
    public LocationArray(){};


    public void add(String pointName, double distance, boolean suggest, String better_street,
                    String better_city, String better_zip, String notes, String shopType, String deliveryHours){

        this.location.add(new Location(
                pointName, distance, true, better_street, better_city, better_zip, notes, shopType, deliveryHours
        ));
    }



}
