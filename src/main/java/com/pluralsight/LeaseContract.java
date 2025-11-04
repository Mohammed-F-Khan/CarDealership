package com.pluralsight;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail,
                         Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() * 0.50;
        this.leaseFee = vehicleSold.getPrice() * 0.07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        double total = (vehiclePrice - expectedEndingValue) + leaseFee;
        return total;
    }

    @Override
    public double getMonthlyPayment() {
        double amountToFinance = getTotalPrice();
        double interestRate = 4.0 / 100;
        int numberOfMonths = 36;
        double monthlyInterestRate = interestRate / 12;
        double monthlyPayment = (monthlyInterestRate * amountToFinance) /
                (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonths));
        return monthlyPayment;
    }
}