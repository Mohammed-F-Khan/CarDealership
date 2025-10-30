package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    // these are the properties of dealership
    private String name;
    private String address;
    private String phone;

    // The Arraylist holds all the vehicles in the dealership
    // like a list or collection of Vehicle objects
    private ArrayList<Vehicle> inventory;

    // constructor
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        // This creates a new empty arrayList to hold vehicles
        this.inventory = new ArrayList<Vehicle>();
    }

    // getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    // Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // This method searches for vehicles within a price range
    // Parameters: min = lowest price, max = highest price
    // Returns: a list of vehicles that match
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        // create a new empty list to hold matching vehicles
        ArrayList<Vehicle> matches = new ArrayList<Vehicle>();

        // Loops through every Vehicle in our inventory
        for (Vehicle vehicle : inventory) {
            // check if this vehicle's price is between our min and max
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                // if yes, adds it to the matches list
                matches.add(vehicle);
            }
        }

        // return the list of matching vehicles
        return matches;
    }

    // This method searches for vehicles by color
    // Parameters: color = color to search for like "Red"
    // Returns: list of matching vehicles

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> matches = new ArrayList<Vehicle>();

        // loop through every vehicle
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matches.add(vehicle);
            }
        }

        return matches;
    }

    // This method searches for vehicles within a mileage range
    // Parameters: min = lowest mileage, max = highest mileage
    // Returns: list of matching vehicles

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> matches = new ArrayList<Vehicle>();

        // loop through every vehicle
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    // This method searches for vehicles by type
    // Parameters: vehicleType = type like "car", "truck", "SUV", "van"
    // Returns: list of matching vehicles
    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> matches = new ArrayList<Vehicle>();

        // Loop through every vehicle
        for (Vehicle vehicle : inventory) {
            // Check if type matches (ignoring uppercase/lowercase)
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                matches.add(vehicle);
            }
        }

        return matches;
    }

    // This method returns all vehicles in the inventory
    // No filtering - just returns the entire list
    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    //this method adds a new vehicle to the inventory
    // parameter: vehicle = the Vehicle object to add
    public void addVehicle(Vehicle vehicle) {
        // uses ArrayList's add() method to add the vehicle to our list
        inventory.add(vehicle);
    }

    // This method removes a vehicle from the inventory
    // Parameter: vehicle = the Vehicle object to remove
    public void removeVehicle(Vehicle vehicle) {
        // Use ArrayList's remove() method to remove the vehicle from our list
        inventory.remove(vehicle);
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        // Create empty list for matches
        ArrayList<Vehicle> matches = new ArrayList<Vehicle>();

        // Loop through every vehicle
        for (Vehicle vehicle : inventory) {
            // Check if make AND model both match
            // .equalsIgnoreCase() compares strings ignoring uppercase/lowercase
            if (vehicle.getMake().equalsIgnoreCase(make) &&
                    vehicle.getModel().equalsIgnoreCase(model)) {
                // Add matching vehicle to our list
                matches.add(vehicle);
            }
        }

        return matches;
    }

    // This method searches for vehicles within a year range
// Parameters: min = earliest year, max = latest year
// Returns: list of matching vehicles
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> matches = new ArrayList<Vehicle>();

        // Loop through every vehicle
        for (Vehicle vehicle : inventory) {
            // Check if year is between min and max
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                matches.add(vehicle);
            }
        }

        return matches;
    }
}
