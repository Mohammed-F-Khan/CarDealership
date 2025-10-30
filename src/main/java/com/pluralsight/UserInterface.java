package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

// This class handles ALL interaction with the user
// It displays menus, reads input, and shows results
public class UserInterface {
    private Scanner scanner;

    // the dealership object w're wrking with
    private Dealership dealership;

    // contructor
    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    // This private method loads the dealership from the file
    // It's called at the start of display()
    private void init() {

        DealershipFileManager fileManager = new DealershipFileManager();

        // getDealership() method to load the dealership
        // This reads the inventory.csv file and creates the Dealership object
        this.dealership = fileManager.getDealership();
    }
    // This is the main method that starts the user interface
    // It shows the menu and handles user choices

    public void display() {
        // first load dealership from the file
        init();

        // a loop that keeps running until user wants to quit
        boolean quit = false;
        while (!quit) {
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║   " + dealership.getName() + "           ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make / model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 - Quit");
            System.out.print("\nEnter your choice: ");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline left over

            // Use a switch statement to call the right method
            switch (choice) {
                case 1:
                    // Call method to search by price
                    processGetByPriceRequest();
                    break;
                case 2:
                    // Call method to search by make/model
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    // Call method to search by year
                    processGetByYearRequest();
                    break;
                case 4:
                    // Call method to search by color
                    processGetByColorRequest();
                    break;
                case 5:
                    // Call method to search by mileage
                    processGetByMileageRequest();
                    break;
                case 6:
                    // Call method to search by type
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    // Call method to list all vehicles
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    // Call method to add a vehicle
                    processAddVehicleRequest();
                    break;
                case 9:
                    // Call method to remove a vehicle
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    // Set quit to true to exit the loop
                    quit = true;
                    System.out.println("Thank you for using " + dealership.getName() + "!");
                    break;
                default:
                    // If user enters invalid number
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // this method searches by price range
    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();

        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        // calls the dealership method to search by price
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);

        // displays results
        displayVehicles(vehicles);
    }

    // this method searches by make and model
    private void processGetByMakeModelRequest() {
        // Ask user for the make (brand)
        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        // Ask user for the model
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        // Call the dealership's method to search by make/model
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);

        // Display the results
        displayVehicles(vehicles);
    }

    // This method handles searching by year range
    private void processGetByYearRequest() {
        // Ask user for minimum year
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();

        // Ask user for maximum year
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        // Call the dealership's method to search by year
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);

        // Display the results
        displayVehicles(vehicles);
    }

    // This method handles searching by color
    private void processGetByColorRequest() {
        // Ask user for the color
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        // Call the dealership's method to search by color
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(color);

        // Display the results
        displayVehicles(vehicles);
    }

    // This method handles searching by mileage range
    private void processGetByMileageRequest() {
        // Ask user for minimum mileage
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();

        // Ask user for maximum mileage
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        // Call the dealership's method to search by mileage
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);

        // Display the results
        displayVehicles(vehicles);
    }

    // This method handles searching by vehicle type
    private void processGetByVehicleTypeRequest() {
        // Ask user for the type
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String type = scanner.nextLine();

        // Call the dealership's method to search by type
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(type);

        // Display the results
        displayVehicles(vehicles);
    }

    // This method handles listing ALL vehicles
    private void processGetAllVehiclesRequest() {
        // Call the dealership's method to get all vehicles
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

        // Display the results
        displayVehicles(vehicles);
    }

    // This method handles adding a new vehicle
    private void processAddVehicleRequest() {
        // Ask user for all the vehicle information
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter odometer: ");
        int odometer = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Clear the newline

        // Create a new Vehicle object with this information
        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

        // Add it to the dealership
        dealership.addVehicle(vehicle);

        // IMPORTANT: Save the dealership back to the file
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully!");
    }

    // This method handles removing a vehicle
    private void processRemoveVehicleRequest() {
        // Ask user for the VIN of the vehicle to remove
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        // Search through all vehicles to find the one with this VIN
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        Vehicle vehicleToRemove = null;

        // Loop through all vehicles
        for (Vehicle vehicle : vehicles) {
            // Check if this vehicle has the VIN we're looking for
            if (vehicle.getVin() == vin) {
                // Save this vehicle so we can remove it
                vehicleToRemove = vehicle;
                break; // Stop looking once we found it
            }
        }

        // Check if we found a vehicle
        if (vehicleToRemove != null) {
            // Remove the vehicle from the dealership
            dealership.removeVehicle(vehicleToRemove);

            // IMPORTANT: Save the dealership back to the file
            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);

            System.out.println("Vehicle removed successfully!");
        } else {
            // If we didn't find a vehicle with that VIN
            System.out.println("Vehicle not found.");
        }
    }

    // This is a helper method that displays a list of vehicles
    // It's called by all the search methods to show results
    // Parameter: vehicles = the list of vehicles to display
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        // checks if the list is empty
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles found.");
            return;
        }

        // prints a header
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                VEHICLE INVENTORY                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
        System.out.printf("%-8s %-6s %-12s %-12s %-8s %-10s %-10s %-10s\n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price");
        System.out.println("--------------------------------------------------------------------------------");

        // Loop through each vehicle in the list
        for (Vehicle vehicle : vehicles) {
            // Print each vehicle's information in a formatted way
            // %s = string, %d = integer, %.2f = decimal with 2 places
            System.out.printf("%-8d %-6d %-12s %-12s %-8s %-10s %-10d $%-9.2f\n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }

        // Print a footer showing how many vehicles were found
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Total vehicles: " + vehicles.size());
    }
}