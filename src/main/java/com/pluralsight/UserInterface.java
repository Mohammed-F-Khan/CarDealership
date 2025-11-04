package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserInterface {
    private Scanner scanner;
    private Dealership dealership;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }

    public void display() {
        init();

        boolean quit = false;
        while (!quit) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   " + dealership.getName() + "           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make / model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Sell/Lease a vehicle");
            System.out.println("99 - Quit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processSellOrLeaseVehicle();
                    break;
                case 99:
                    quit = true;
                    System.out.println("Thank you for using " + dealership.getName() + "!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    private void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String type = scanner.nextLine();
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    private void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void processAddVehicleRequest() {
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
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
        scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully!");
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        Vehicle vehicleToRemove = null;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void processSellOrLeaseVehicle() {
        System.out.print("Enter VIN of vehicle to sell/lease: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        Vehicle vehicleToSell = null;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vin) {
                vehicleToSell = vehicle;
                break;
            }
        }

        if (vehicleToSell == null) {
            System.out.println("Vehicle not found in inventory.");
            return;
        }

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateOfContract = today.format(formatter);

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Is this a (S)ale or (L)ease? ");
        String saleOrLease = scanner.nextLine().toUpperCase();

        Contract contract = null;

        if (saleOrLease.equals("S")) {
            System.out.print("Will you finance this purchase? (Y/N): ");
            String financeChoice = scanner.nextLine().toUpperCase();
            boolean isFinanced = financeChoice.equals("Y");

            contract = new SalesContract(dateOfContract, customerName, customerEmail,
                    vehicleToSell, isFinanced);

            System.out.println("\n--- SALES CONTRACT ---");
            System.out.println("Customer: " + customerName);
            System.out.println("Vehicle: " + vehicleToSell.getYear() + " " +
                    vehicleToSell.getMake() + " " + vehicleToSell.getModel());
            System.out.println("Sales Price: $" + String.format("%.2f", vehicleToSell.getPrice()));
            System.out.println("Sales Tax: $" + String.format("%.2f", ((SalesContract)contract).getSalesTaxAmount()));
            System.out.println("Recording Fee: $" + String.format("%.2f", ((SalesContract)contract).getRecordingFee()));
            System.out.println("Processing Fee: $" + String.format("%.2f", ((SalesContract)contract).getProcessingFee()));
            System.out.println("Total Price: $" + String.format("%.2f", contract.getTotalPrice()));
            if (isFinanced) {
                System.out.println("Monthly Payment: $" + String.format("%.2f", contract.getMonthlyPayment()));
            } else {
                System.out.println("Payment: CASH");
            }

        } else if (saleOrLease.equals("L")) {
            int currentYear = LocalDate.now().getYear();
            int vehicleAge = currentYear - vehicleToSell.getYear();

            if (vehicleAge > 3) {
                System.out.println("Sorry, this vehicle is too old to lease (over 3 years old).");
                return;
            }

            contract = new LeaseContract(dateOfContract, customerName, customerEmail, vehicleToSell);

            System.out.println("\n--- LEASE CONTRACT ---");
            System.out.println("Customer: " + customerName);
            System.out.println("Vehicle: " + vehicleToSell.getYear() + " " +
                    vehicleToSell.getMake() + " " + vehicleToSell.getModel());
            System.out.println("Vehicle Price: $" + String.format("%.2f", vehicleToSell.getPrice()));
            System.out.println("Expected Ending Value: $" + String.format("%.2f", ((LeaseContract)contract).getExpectedEndingValue()));
            System.out.println("Lease Fee: $" + String.format("%.2f", ((LeaseContract)contract).getLeaseFee()));
            System.out.println("Total Lease Cost: $" + String.format("%.2f", contract.getTotalPrice()));
            System.out.println("Monthly Payment: $" + String.format("%.2f", contract.getMonthlyPayment()));

        } else {
            System.out.println("Invalid choice. Please enter S for Sale or L for Lease.");
            return;
        }

        ContractFileManager contractManager = new ContractFileManager();
        contractManager.saveContract(contract);

        dealership.removeVehicle(vehicleToSell);

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);

        System.out.println("\nContract saved successfully!");
        System.out.println("Vehicle removed from inventory.");
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles found.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                VEHICLE INVENTORY                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
        System.out.printf("%-8s %-6s %-12s %-12s %-8s %-10s %-10s %-10s\n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price");
        System.out.println("--------------------------------------------------------------------------------");

        for (Vehicle vehicle : vehicles) {
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

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Total vehicles: " + vehicles.size());
    }
}