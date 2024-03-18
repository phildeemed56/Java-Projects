

import java.util.ArrayList;
import java.util.Scanner;

//Author: Philip Mensah
//Purpose: Program to compute the cost of shipping packages.

//Package class represents each individual package
public class Package {
 private double weight;
 private double length;
 private double width;
 private double height;

 // Constructor to initialize Package object with weight and dimensions
 public Package(double weight, double length, double width, double height) {
     this.weight = weight;
     this.length = length;
     this.width = width;
     this.height = height;
 }

 // Method to determine if the package is oversized
 public boolean isOversized() {
     double totalLinearInches = length + width + height;
     return totalLinearInches > 100;
 }

 // Method to determine if the package is overweight
 public boolean isOverweight() {
     return weight > 120;
 }

//Method to calculate dimensional weight
public int calculateDimensionalWeight() {
  int roundedLength = (int) Math.ceil(length);
  int roundedWidth = (int) Math.ceil(width);
  int roundedHeight = (int) Math.ceil(height);

  int dimensionalWeight = (roundedLength * roundedWidth * roundedHeight) / 166;
  int remainder = (roundedLength * roundedWidth * roundedHeight) % 166;
  if (remainder > 0) {
      dimensionalWeight++; // Increase to the next whole pound
  }
  return dimensionalWeight == 0 ? 1 : dimensionalWeight; // Minimum dimensional weight is 1 pound
}

 // Method to calculate shipping cost
 public double calculateShippingCost() {
     int actualWeight = (int) Math.ceil(weight);
     int dimensionalWeight = calculateDimensionalWeight();
     int billableWeight = Math.max(actualWeight, dimensionalWeight);
     return billableWeight * 3.12;
 }

 // Getters for package attributes
 public double getWeight() {
     return weight;
 }

 public double getLength() {
     return length;
 }

 public double getWidth() {
     return width;
 }

 public double getHeight() {
     return height;
 }
}

