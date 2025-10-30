package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    // this class loads the dealership from a file and saves it to the dealership back to a file

    // this method reads the Inven.csv file and creates a dealership object
    // it returns a fully loaded dealerhship with all its vehicles
    public Dealership getDealership() {
        // a variable to hold the dealership
        Dealership dealership = null;

        // for errors
        try {
            // open file to read and filereader connects to the file
            FileReader fileReader = new FileReader("inventory.csv");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Reads the first line dealership info
            String firstLine = bufferedReader.readLine();

            // split the first line by |
            String[] dealershipParts = firstLine.split("\\|");

            // Creates Dealership object using the pieces
            // name, address, phone
            dealership = new Dealership(dealershipParts[0], dealershipParts[1], dealershipParts[2]);

            // reads the rest of the lines
            String line;
            // keeps reading lines until it reaches the end.
            while ((line = bufferedReader.readLine()) != null) {
                String[] vehicleParts = line.split("\\|");

                // Convert the string parts to the right data types
                // parseInt() converts string to int
                // parseDouble() conerts string to double
                int vin = Integer.parseInt(vehicleParts[0]);
                int year = Integer.parseInt(vehicleParts[1]);
                String make = vehicleParts[2];
                String model = vehicleParts[3];
                String vehicleType = vehicleParts[4];
                String color = vehicleParts[5];
                int odometer = Integer.parseInt(vehicleParts[6]);
                double price = Double.parseDouble(vehicleParts[7]);

                // Creates a new Vehicle object with this information
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                // adds this vehicle to the dealerships inventory
                dealership.addVehicle(vehicle);
            }

            // closes the file
            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return dealership;
    }

    // This method saves the dealership back to the inventory.csv file
    // Parameter: dealership = the Dealership object to save
    public void saveDealership(Dealership dealership) {
        try {
            FileWriter fileWriter = new FileWriter("inventory.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bufferedWriter.newLine();
            ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

            for (Vehicle vehicle : vehicles) {

                String line = vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice();

                bufferedWriter.write(line);

                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("error writing file: " + e.getMessage());
        }
    }
}
