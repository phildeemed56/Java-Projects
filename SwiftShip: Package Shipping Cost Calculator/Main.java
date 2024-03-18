
import java.util.Scanner;

//Author: Philip Mensah
//Purpose: Program to compute the cost of shipping packages for SwiftShip company.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shipment shipment = new Shipment();     //Creating a new Shipment object
        System.out.println("Welcome to the parcel service store. Please enter the weight and the size of your packages.");

        int packageNumber = 1; // Track the package number

        boolean morePackages = true;
        while (morePackages) {
            System.out.println("Please enter the data for package " + (shipment.getTotalPackages() + 1));
            Package pkg = createPackage(scanner);    // Create a new Package object
            if (pkg != null) {     // Check if the package creation was successful
                double actualWeight = Math.ceil(pkg.getWeight());
                int dimensionalWeight = pkg.calculateDimensionalWeight();
                double shippingCost = pkg.calculateShippingCost();
                
                
               // Display package details
                System.out.printf("The package’s actual weight is %.2f pounds, and the dimensional weight is %d (%d x %d x %d).%n", actualWeight, dimensionalWeight, (int)Math.ceil(pkg.getLength()), (int)Math.ceil(pkg.getWidth()), (int)Math.ceil(pkg.getHeight()));
                System.out.printf("The shipping cost is $%.2f%n", shippingCost);

                shipment.addPackage(pkg);   // Add the package to the shipment

                packageNumber++; // Increment the package number
            }

            System.out.print("Do you have more packages to ship? (Y/N): ");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("Y")) {
                morePackages = false;  // Exit the loop if the user does not have more packages
            }
        }

        System.out.print("Please enter the frequent shipping number (enter 0 if you don’t have one): ");
        int frequentShipperNumber = scanner.nextInt();
        shipment.setFrequentShipper(frequentShipperNumber != 0);

        // Displaying shipment details using toString() method
        System.out.println(shipment.toString());

        scanner.close();
    }

    // Method to create a package
    private static Package createPackage(Scanner scanner) {
        double weight, length, width, height;
        while (true) {
            System.out.print("Please enter the weight of the package in pounds: ");
            weight = scanner.nextDouble();
            if (weight <= 0) {
                System.out.println("The weight should be greater than 0. Please enter again.");
            } else if (weight > 120) {
                System.out.println("Cannot accept overweight package.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Please enter the length of the package in inches: ");
            length = scanner.nextDouble();
            if (length <= 0) {
                System.out.println("The length should be greater than 0. Please enter again.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Please enter the width of the package in inches: ");
            width = scanner.nextDouble();
            if (width <= 0) {
                System.out.println("The width should be greater than 0. Please enter again.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Please enter the height of the package in inches: ");
            height = scanner.nextDouble();
            if (height <= 0) {
                System.out.println("The height should be greater than 0. Please enter again.");
            } else {
                break;
            }
        }

        Package pkg = new Package(weight, length, width, height);
        if (pkg.isOversized() || pkg.isOverweight()) {
            System.out.println("Cannot accept oversized or overweight package.");
            return null;  // return null if the package is oversized or overweight
        }

        return pkg;  // return the created package
    }
    
}
    
    
    
