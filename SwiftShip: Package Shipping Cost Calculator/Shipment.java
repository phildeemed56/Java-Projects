
import java.util.ArrayList;
import java.text.DecimalFormat;

//Author: Philip Mensah
//Purpose: Program to compute the cost of shipping packages.

public class Shipment {

    private ArrayList<Package> packages; // ArrayList to store packages
    private boolean frequentShipper; // Flag to indicate if the user is a frequent shipper

    // Constructor to initialize the Shipment object
    public Shipment() {
        packages = new ArrayList<>();
        frequentShipper = false;
    }

    // Method to add a package to the shipment
    public void addPackage(Package pkg) {
        packages.add(pkg);
    }

    // Method to set whether the user is a frequent shipper
    public void setFrequentShipper(boolean frequentShipper) {
        this.frequentShipper = frequentShipper;
    }

    // Method to calculate the subtotal cost of all packages
    public double calculateSubtotal() {
        double subtotal = 0;
        for (Package pkg : packages) {
            subtotal += pkg.calculateShippingCost();
        }
        return subtotal;
    }

    // Method to calculate the frequent shipper discount
    public double calculateFrequentShipperDiscount() {
        return frequentShipper ? calculateSubtotal() * 0.05 : 0;
    }

    // Method to calculate the bulk package discount
    public double calculateBulkPackageDiscount(double subtotal) {
        double frequentDiscount = calculateFrequentShipperDiscount();
        double totalAfterFrequentDiscount = subtotal - frequentDiscount;
        double bulkDiscount = totalAfterFrequentDiscount > 300 ? 20.0 : 0.0;

        // Format the discount to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(bulkDiscount));
    }

    // Method to calculate the grand total cost
    public double calculateGrandTotal() {
        double subtotal = calculateSubtotal();
        double discount = calculateFrequentShipperDiscount();
        double bulkDiscount = calculateBulkPackageDiscount(subtotal);
        return subtotal - discount - bulkDiscount;
    }

    // Method to generate string representation of shipment details
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        StringBuilder sb = new StringBuilder();
        sb.append("Total package(s) to ship: ").append(getTotalPackages()).append("\n");
        double subtotal = calculateSubtotal();
        sb.append("Package cost: $").append(df.format(subtotal)).append("\n");
        double frequentDiscount = calculateFrequentShipperDiscount();
        sb.append("Frequent shipper discount: $").append(df.format(frequentDiscount)).append("\n");
        double bulkDiscount = calculateBulkPackageDiscount(subtotal);
        sb.append("Bulk discount: $").append(df.format(bulkDiscount)).append("\n");
        double grandTotal = calculateGrandTotal();
        sb.append("Grand Total: $").append(df.format(grandTotal)).append("\n");
        return sb.toString();
    }

    // Method to get the total number of packages in the shipment
    public int getTotalPackages() {
        return packages.size();
    }
}