package com.pluralsight;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String dateOfContract, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean isFinanced) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
        this.salesTaxAmount = vehicleSold.getPrice() * 0.05;
        this.recordingFee = 100.00;
        if (vehicleSold.getPrice() < 10000) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        double total = vehiclePrice + salesTaxAmount + recordingFee + processingFee;
        return total;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0.0;
        }

        double amountToFinance = getTotalPrice();
        double interestRate;
        int numberOfMonths;

        if (amountToFinance >= 10000) {
            interestRate = 4.25 / 100;
            numberOfMonths = 48;
        } else {
            interestRate = 5.25 / 100;
            numberOfMonths = 24;
        }

        double monthlyInterestRate = interestRate / 12;
        double monthlyPayment = (monthlyInterestRate * amountToFinance) /
                (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonths));

        return monthlyPayment;
    }
}