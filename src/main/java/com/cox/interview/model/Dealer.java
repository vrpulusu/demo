package com.cox.interview.model;

import java.util.List;

public class Dealer {

    private int dealerId;
    private String name;
    private List<Vehicle> vehicles;

    public Dealer() {
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicleList) {
        this.vehicles = vehicleList;
    }

    @Override
    public String toString()
    {
        return "dealer [dealerId="
                + dealerId
                + ", name="
                + name
                + ", vehicles="
                + vehicles
                + "]";
    }
}
