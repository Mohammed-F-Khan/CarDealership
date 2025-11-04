package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public void saveContract(Contract contract) {
        try {
            FileWriter fileWriter = new FileWriter("contracts.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                Vehicle vehicle = salesContract.getVehicleSold();

                String line = "SALE|" +
                        salesContract.getDateOfContract() + "|" +
                        salesContract.getCustomerName() + "|" +
                        salesContract.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "|" +
                        salesContract.getSalesTaxAmount() + "|" +
                        salesContract.getRecordingFee() + "|" +
                        salesContract.getProcessingFee() + "|" +
                        salesContract.getTotalPrice() + "|" +
                        (salesContract.isFinanced() ? "YES" : "NO") + "|" +
                        salesContract.getMonthlyPayment();

                bufferedWriter.write(line);
                bufferedWriter.newLine();

            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                Vehicle vehicle = leaseContract.getVehicleSold();

                String line = "LEASE|" +
                        leaseContract.getDateOfContract() + "|" +
                        leaseContract.getCustomerName() + "|" +
                        leaseContract.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "|" +
                        leaseContract.getExpectedEndingValue() + "|" +
                        leaseContract.getLeaseFee() + "|" +
                        leaseContract.getTotalPrice() + "|" +
                        leaseContract.getMonthlyPayment();

                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing contract file: " + e.getMessage());
        }
    }
}