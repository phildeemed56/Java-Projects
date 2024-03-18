
**SwiftShip Parcel Shipping Cost Calculator**

Welcome to SwiftShip, your efficient parcel service store! Our program simplifies the process of computing shipping costs for your packages. Whether you're shipping one package or multiple, SwiftShip has got you covered.

**How It Works**

**Input Package Details:** Enter the weight and dimensions for each package you wish to ship. Our program ensures that each package adheres to our size and weight requirements:

Maximum weight: 120 pounds

Maximum outside linear inches: 100 (length + width + height)

We validate inputs to ensure they are positive numbers and reject overweight or oversize packages.

**Calculate Dimensional Weight:** For each package, our program calculates the dimensional weight, reflecting the space the package occupies. This is determined using the following steps:

Determine the package dimensions in inches, rounding up any fractional measurements.

Multiply the package length by the width by the height to find the cubic size in inches.

Divide the cubic size by 166 to determine the dimensional weight in pounds, rounding up any fractional weights.

**Compare and Calculate Shipping Costs:** The shipping cost for each package is calculated by comparing the actual weight and dimensional weight. The greater of the two becomes the billable weight, which we use to calculate the shipping rate. Our current rate is $3.12 per pound.

**Apply Discounts:** If you have a frequent shipper number, you'll have the opportunity to input it. We offer a 5% discount on shipping costs for frequent shippers. Additionally, if your total amount after the frequent shipper discount exceeds $300, we automatically apply a bulk package discount of $20.

**Review and Finalize:** After entering all package details and any applicable discount codes, the program will display:

Total number of packages to ship
Subtotal before any discounts
Frequent shipper discount (if applied)
Bulk package discount (if applied)
Grand total

**Sample Output**

Welcome to SwiftShip Parcel Service. Please enter the weight and size of your packages.

Please enter the data for package 1

Please enter the weight of the package in pounds: -5

The weight should be greater than 0. Please enter again: 151

Cannot accept overweight package.

Do you have more packages to ship? (Y/N): Y

Please enter the data for package 2

Please enter the weight of the package in pounds: 120

Please enter the length of the package in inches: 16.2

Please enter the width of the package in inches: 18.6

Please enter the height of the package in inches: 30.6

The package's actual weight is 120.0 pounds, and the dimensional weight is 61.0 (17 x 19 x 31).
The shipping cost is $374.40.

Do you have more packages to ship? (Y/N): Y

Please enter the data for package 3

Please enter the weight of the package in pounds: 20

Please enter the length of the package in inches: 12.6

Please enter the width of the package in inches: 5.8

Please enter the height of the package in inches: 15.6

The package's actual weight is 20.0 pounds, and the dimensional weight is 8.0 (13 x 6 x 16).

The shipping cost is $62.40.

Do you have more packages to ship? (Y/N): N

Please enter your frequent shipper number (enter 0 if you don't have one): 8953796

Total package(s) to ship: 2

Package cost: $436.80

Frequent shipper discount: $21.84

Bulk discount: $20.00

Grand Total: $394.96

**Get Started**

Clone this repository to your local machine and run the program using your preferred Java development environment.

Thank you for choosing SwiftShip for all your shipping needs! If you have any questions or suggestions, feel free to reach out.
