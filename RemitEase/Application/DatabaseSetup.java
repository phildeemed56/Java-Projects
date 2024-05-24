package projectfiles.app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    private static final String URL = "jdbc:mysql://remitease.cr2esock8dpy.us-west-2.rds.amazonaws.com:3306/RemitEaseDev?useSSL=false&serverTimezone=UTC";
    private static final String USER = "admin";
    private static final String PASSWORD = "rEmitEase$";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            createTables(conn);
            System.out.println("All tables created successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    


    private static void createTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
        	
            // Disable foreign key checks
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");

            // Drop tables
            stmt.execute("DROP TABLE IF EXISTS Remittance");
            stmt.execute("DROP TABLE IF EXISTS ExchangeRate");
            stmt.execute("DROP TABLE IF EXISTS Customer");
            stmt.execute("DROP TABLE IF EXISTS Recipient");
            stmt.execute("DROP TABLE IF EXISTS Partner");
            stmt.execute("DROP TABLE IF EXISTS User");

            // Re-enable foreign key checks
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            
            // User Table
            stmt.execute("CREATE TABLE IF NOT EXISTS User (" +
            "UserId VARCHAR(255) PRIMARY KEY, " +
            "Password VARCHAR(255) NOT NULL, " +
            "PermissionLevel INT NOT NULL, " +
            "CONSTRAINT CHK_PermissionLevel CHECK (PermissionLevel IN (1, 2)));");

            
            //Insert into User's table
            stmt.execute("INSERT INTO User VALUES ('CSR100','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR101','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR103','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR104','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR105','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR106','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR107','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR108','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR109','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR110','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR111','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR112','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR113','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR114','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR115','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR116','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR117','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR118','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR119','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR120','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR121','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR122','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR123','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR124','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('CSR125','1234', 1)");
            stmt.execute("INSERT INTO User VALUES ('PRT100','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT101','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT102','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT103','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT104','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT105','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT106','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT107','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT108','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT109','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT110','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT111','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT112','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT113','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT114','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT115','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT116','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT117','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT118','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT119','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT120','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT121','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT122','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT123','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT124','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT125','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT126','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT127','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT128','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT129','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT130','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT131','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT132','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT133','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT134','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT135','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT136','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT137','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT138','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT139','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT140','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT141','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT142','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT143','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT144','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT145','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT146','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT147','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT148','7352', 2)");
            stmt.execute("INSERT INTO User VALUES ('PRT149','7352', 2);");

            
         // Customer Table
            stmt.execute("CREATE TABLE IF NOT EXISTS Customer (" +
                         "CustomerId VARCHAR(255) PRIMARY KEY, " +
                         "FirstName VARCHAR(255), " +
                         "LastName VARCHAR(255), " +
                         "PhoneNumber VARCHAR(255), " +
                         "Email VARCHAR(255), " +
                         "Balance DOUBLE, " +
                         "Country VARCHAR(255), " +
                         "City VARCHAR(255), " +
                         "Address VARCHAR(255), " +
                         "FOREIGN KEY (CustomerId) REFERENCES User(UserId));");
            
            // Insert into Customer's Table
            stmt.execute("INSERT INTO Customer VALUES ('CSR100','Bolivian','Dark','743-857-3982','boliviandark@gmail.com',600.00,'USA','Las Vegas','124 University Road')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR101','Andrew','Park','324-002-0943','andrewpark@gmail.com',600.00,'USA','Dallas','125 Boulevard Road')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR103','Janet','Princess','356-562-3423','janetprincess@gmail.com',600.00,'USA','San Francisco','126 School Road')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR104','Tobias','Prince','783-095-8323','tobiasprince@gmail.com',700.00,'USA','Athens','127 Pent Road')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR105','Emma','Phelps','645-394-8518','emmaphelps@gmail.com',700.00,'USA','Orlando','128 Vegas Road')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR106','Wonder','Fulton','746-859-2830','wonderfulton@gmail.com',700.00,'USA','Bronx','129 Tempe Road')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR107','Fred','Cumming','465-038-9481','fredcumming@gmail.com',700.00,'USA','Bozeman','543 Temple Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR108','Harry','Chris','783-092-0938','harrychris@gmail.com',700.00,'USA','Seattle','544 Market Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR109','Sam','Morris','567-057-7261','sammorris@gmail.com',700.00,'USA','Atlanta','545 Tropicana Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR110','Chester','Taylor','746-947-9473','chestertaylor@gmail.com',700.00,'USA','Los Angeles','546 Flaming Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR111','Gray','Pierce','647-097-9273','graypierce@gmail.com',700.00,'USA','Chicago','547 Jet Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR112','Stone','Beckley','756-084-7623','stonebeckley@gmail.com',900.00,'USA','Phoenix','548 Seth Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR113','Pearl','Watson','512-947-9363','pearlwatson@gmail.com',900.00,'USA','Houston','549 Bottle Road')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR114','Derry','Emittson','342-183-7563','derrywatson@gmail.com',900.00,'USA','Miami','550 Prize Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR115','Grove','Beats','932-846-1623','grovebeats@gmail.com',900.00,'USA','Lincoln','551 Hudson Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR116','Fulham','Spurs','458-193-1273','fulhamspurs@gmail.com',900.00,'USA','Charlotte','552 Russel Street')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR117','Vicky','Rodwood','237-957-2845','vickyrodwood@gmail.com',1000.00,'USA','Greenville','723 Pecos Circle')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR118','Neo','Aephason','585-875-9485','neoaephason@gmail.com',1000.00,'USA','Louisville','724 Unit Circle')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR119','Sue','Dodge','768-953-0183','suedodge@gmail.com',1000.00,'USA','Colombus','725 Yale Circle')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR120','Stanly','Prize','649-173-9212','stanlyprize@gmail.com',1000.00,'USA','San Diego','726 Los Circle')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR121','Henry','Wonders','563-058-4321','henrywonders@gmail.com',1000.00,'USA','Reno','727 Dallas Circle')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR122','Seize','Stones','409-857-6473','seizestones@gmail.com',1000.00,'USA','Minneapolis','728 Fayettville Circle')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR123','Wesley','Noah','943-123-5832','wesleynoah@gmail.com',1250.00,'USA','Tempe','729 Athens Circle')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR124','Tara','Song','658-586-0382','tarasong@gmail.com',1250.00,'USA','Fairfax','730 Treat Circle')");
            stmt.execute("INSERT INTO Customer VALUES ('CSR125','Joe','Larry','740-135-9584','joelarry@gmail.com',1250.00,'USA','Anderson','500 Heat Grove');");
            
           


            // Recipient Table
            stmt.execute("CREATE TABLE IF NOT EXISTS Recipient (" +
                        "RecipientId INT AUTO_INCREMENT PRIMARY KEY, " +
                        "FirstName VARCHAR(255), " +
                        "LastName VARCHAR(255), " +
                        "PhoneNumber VARCHAR(255), " +
                        "Email VARCHAR(255), " +
                        "Balance DOUBLE, " +
                        "Country VARCHAR(255), " +
                        "City VARCHAR(255), " +
                        "Address VARCHAR(255));");
                        // Insert into Recipient's table

            //set initial auto increment value
            stmt.execute("ALTER TABLE Recipient AUTO_INCREMENT = 1000");

         
            // Insert into Recipient's table without specifying RecipientId
            stmt.execute("INSERT INTO Recipient (FirstName, LastName, PhoneNumber, Email, Balance, Country, City, Address) VALUES " +
                        "('Rex', 'Cobbs', '574-937-0853', 'rexcobbs@gmail.com', 10.00, 'Ecuador', 'Ambato', '123 King Street'), " +
                        "('Henry', 'Gaither', '235-643-6434', 'henrygaither@gmail.com', 10.00, 'Ecuador', 'Ambato', '124 King Street'), " +
                        "('Prince', 'Stone', '694-696-3246', 'princestone@gmail.com', 10.00, 'Ecuador', 'Ambato', '125 King Street'), " +
                        "('Leslie', 'Song', '754-596-4575', 'lesliesong@gmail.com', 10.00, 'Ecuador', 'Ambato', '126 King Street'), " +
                        "('Maxwell', 'Gonzalez', '796-059-9563', 'maxwellgonzalez@gmail.com', 10.00, 'Ecuador', 'Ambato', '127 Prince Street'), " +
                        "('Harry', 'Williamson', '345-564-9482', 'harrywilliamson@gmail.com', 20.00, 'Ecuador', 'Ambato', '128 King Street'), " +
                        "('Ben', 'Dickson', '452-293-9683', 'bendickson@gmail.com', 20.00, 'Ecuador', 'Ambato', '129 King Street'), " +
                        "('Fred', 'Johnson', '239-069-4964', 'fredjohnson@gmail.com', 20.00, 'Ecuador', 'Quito', '130 Royal Street'), " +
                        "('Daniel', 'Timson', '904-486-4963', 'danieltimson@gmail.com', 20.00, 'Ecuador', 'Quito', '131 King Street'), " +
                        "('Moses', 'Hall', '694-923-1942', 'moseshall@gmail.com', 20.00, 'Ecuador', 'Quito', '342 School Circle'), " +
                        "('Marsh', 'Wilberforce', '683-495-0634', 'marshwilberforce@gmail.com', 20.00, 'Ecuador', 'Quito', '343 School Circle'), " +
                        "('Herbert', 'Livingstone', '234-695-0593', 'herbertlivingstone@gmail.com', 20.00, 'Ecuador', 'Quito', '344 School Circle'), " +
                        "('Bill', 'Patterson', '434-059-4329', 'billpatterson@gmail.com', 20.00, 'Ghana', 'Kumasi', '573 Screen Grove'), " +
                        "('William', 'Peterson', '406-596-4923', 'williampeterson@gmail.com', 20.00, 'Ghana', 'Kumasi', '574 Screen Grove'), " +
                        "('Catherine', 'Well', '679-458-0493', 'catherinewell@gmail.com', 15.00, 'Ghana', 'Kumasi', '575 Screen Grove'), " +
                        "('Lovert', 'Realson', '458-0795-0694', 'lovertrealson@gmail.com', 15.00, 'Ghana', 'Kumasi', '576 Screen Grove'), " +
                        "('Precious', 'Walker', '753-948-9583', 'preciouswalker@gmail.com', 15.00, 'Ghana', 'Kumasi', '577 Screen Grove'), " +
                        "('Emmanuel', 'Isaccs', '590-058-3133', 'emmanuelisaccs@gmail.com', 15.00, 'Ghana', 'Kumasi', '578 Screen Grove'), " +
                        "('Philip', 'Mensah', '586-964-8235', 'philipmensah@gmail.com', 15.00, 'Ghana', 'Kumasi', '579 Screen Grove'), " +
                        "('Jorge', 'Alvarez', '732-984-2834', 'jorgealvarez@gmail.com', 15.00, 'Ghana', 'Kumasi', '742 Hilton Parkway'), " +
                        "('William', 'Forge', '964-485-9684', 'williamforge@gmail.com', 15.00, 'Ghana', 'Kumasi', '743 Hilton Parkway'), " +
                        "('Hill', 'Forceful', '903-482-0482', 'hillforceful@gmail.com', 15.00, 'Ghana', 'Accra', '744 Hilton Parkway'), " +
                        "('Tremzol', 'King', '233-595-6964', 'tremzolking@gmail.com', 20.00, 'Ghana', 'Accra', '745 Hilton Parkway'), " +
                        "('Fierl', 'Haizel', '694-683-5066', 'fierlhaizel@gmail.com', 20.00, 'Ghana', 'Accra', '746 Hilton Parkway'), " +
                        "('Howard', 'Solid', '593-906-9683', 'howardsolid@gmail.com', 20.00, 'Ghana', 'Accra', '747 Hilton Parkway'), " +
                        "('Seth', 'Jones', '860-492-4955', 'sethjones@gmail.com', 20.00, 'Ghana', 'Accra', '748 Hilton Parkway');");

                        
            // Partner Table
            stmt.execute("CREATE TABLE IF NOT EXISTS Partner (" +
                         "PartnerId VARCHAR(255) PRIMARY KEY, " +
                         "Name VARCHAR(255), " +
                         "Type VARCHAR(255), " +
                         "Country VARCHAR(255), " +
                         "City VARCHAR(255), " +
                         "Address VARCHAR(255), " +
                         "FOREIGN KEY (PartnerId) REFERENCES User(UserId));");

            	
            // Insert into Partner's Table
            stmt.execute("INSERT INTO Partner VALUES ('PRT100','Bank of Ecuador','Money Transfer','Ecuador','Ambato','123 King Street')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT101','Barclays Bank','Money Transfer','Ecuador','Quito','124 Royal Street')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT102','Bank of Ghana','Money Transfer','Ghana','Kumasi','567 Hilton Parkway')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT103','Ecobank','Money Transfer','Ghana','Accra','843 Queen Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT104', 'Bank of Argentina', 'National Bank', 'Argentina', 'Comodoro Rivadavia', '450 Stone Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT105', 'Swift Money Express', 'Money Transfer', 'Argentina', 'Mendoza', '743 Key Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT106', 'Bank of Columbia', 'Federal Bank', 'Columbia', 'Tunja', '429 Phone Arena')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT107', 'Columbia Money Transfer', 'Money Transfer', 'Columbia', 'Pasto', '482 Stand Hill')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT108', 'Bank of Kenya', 'National Bank', 'Kenya', 'Nairobi', '7932 Press Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT109', 'Nairobi Money Express', 'Money Transfer', 'Kenya', 'Nairobi', '842 Halt Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT110', 'Bank of Nigeria', 'National Bank', 'Nigeria', 'Lagos', '321 School District')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT111', 'Barclays Bank', 'Money Transfer', 'Nigeria', 'Abuja', '3832 Calc Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT112', 'Bank of Canada', 'National Bank', 'Canada', 'Ottawa', '8594 Phone Street')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT113', 'Canadian Money Express', 'Money Transfer', 'Canada', 'Ontario', '482 Book Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT114', 'Bank of Brazil', 'National Bank', 'Brazil', 'Marcelo', '732 Study Grove')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT115', 'Brazil Money Transfer', 'Money Transfer', 'Brazil', 'Marcapa', '3923 Diff Song')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT116', 'Bank of France', 'National Bank', 'France', 'Paris', '449 Solid Ave')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT117', 'Fidelity Bank', 'Money Transfer', 'France', 'Nice', '382 Eat Ave')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT118', 'Austria National Bank', 'National Bank', 'Austria', 'Vienna', '873 Type Grove')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT119', 'AUB Bank', 'Money Transfer', 'Austria', 'Graz', '820 University Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT120', 'Bank of Germany', 'National Bank', 'Germany', 'Munich', '921 School Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT121', 'Swift Money Exchange', 'Money Transfer', 'Germany', 'Dresden', '842 Market Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT122', 'Bank of Ireland', 'National Bank', 'Ireland', 'Derry', '832 Paint Corner')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT123', 'Swift Money Exchange', 'Money Transfer', 'Ireland', 'Galway', '302 Drive Ave')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT124', 'Federal Bank of Italy', 'National Bank', 'Italy', 'Rome', '830 Peace District')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT125', 'Rome Bank Express', 'Money Transfer', 'Italy', 'Rome', '9573 Drive Highway')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT126', 'National Bank of Netherlands', 'National Bank', 'Netherlands', 'Amsterdam', '382 Freeway Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT127', 'Amsterdam Money Express', 'Money Transfer', 'Netherlands', 'Amsterdam', '930 Button Ave')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT128', 'Bank of Portugal', 'National Bank', 'Portugal', 'Lisbon', '832 Cook Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT129', 'Mobile Money Bank', 'Money Transfer', 'Portugal', 'Porto', '214 Restaurant Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT130', 'Federal Bank of Spain', 'National Bank', 'Spain', 'Madrid', '394 Freeze Ave')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT131', 'Madrid Money Transfer', 'Money Transfer', 'Spain', 'Barcelona', '4920 Hot Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT132', 'Bank of Slovenia', 'National Bank', 'Slovenia', 'Koper', '942 Trentham Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT133', 'Slovenia Money Transfer', 'Money Transfer', 'Slovenia', 'Kranj', '3920 Flamingo Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT134', 'Bank of Slovekia', 'National Bank', 'Slovekia', 'Poprad', '9283 Water Circle')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT135', 'Swift Money Exchange', 'Money Transfer', 'Slovekia', 'Bojnice', '474 Wood Wridge')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT136', 'Bank of Greece', 'National Bank', 'Greece', 'Athens', '493 Metal Corner')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT137', 'Greece Mobile Money', 'Money Transfer', 'Greece', 'Patras', '832 Type Setting')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT138', 'National Bank of Estonia', 'National Bank', 'Estonia', 'Tartu', '8320 David Road')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT139', 'Vodafone Money Exchange', 'Money Transfer', 'Estonia', 'Narva', '958 Long Street')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT140', 'Luxemborg Bank', 'National Bank', 'Luxemborg', 'Vianden', '932 Herald Grove')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT141', 'Fast Money Express', 'Money Transfer', 'Luxemborg', 'Wiltz', '843 Yell Street')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT142', 'Malta Bank', 'National Bank', 'Malta', 'Mdina', '958 Peace Highway')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT143', 'Speed Money Express', 'Money Transfer', 'Malta', 'Mosta', '8372 Rest Field')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT144', 'Federal Bank of Cyprus', 'National Bank', 'Cyprus', 'Paphos', '485 Grove Street')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT145', 'Cyprus Money Exchange', 'Money Transfer', 'Cyprus', 'Nicosia', '947 Freeze Point')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT146', 'Belgium Bank', 'National Bank', 'Belgium', 'Brussels', '9472 Mouse Pad')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT147', 'Cal Bank', 'Money Transfer', 'Belgium', 'Bruges', '218 Kitchen Plate')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT148', 'Bank of Finland', 'National Bank', 'Finland', 'Turku', '842 Record Break')");
            stmt.execute("INSERT INTO Partner VALUES ('PRT149', 'Finland Money Express', 'Money Transfer', 'Finland', 'Pori', '942 Read Ave');");



            
            
            
            // Remittance Table
            stmt.execute("CREATE TABLE IF NOT EXISTS Remittance (" +
                         "TransactionId INT AUTO_INCREMENT PRIMARY KEY, " +
                         "SenderId VARCHAR(255), " +
                         "RecipientId INT, " +
                         "PartnerId VARCHAR(255), " +
                         "AmountSent DOUBLE, " +
                         "AmountReceived DOUBLE, " +
                         "SourceCurrency VARCHAR(255), " +
                         "TargetCurrency VARCHAR(255), " +
                         "Status VARCHAR(255), " +
                         "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                         "UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                         "CancellationReason VARCHAR(255), " +
                         "ConfirmationDetails VARCHAR(255), " +
                         "FOREIGN KEY (SenderId) REFERENCES Customer(CustomerId), " +
                         "FOREIGN KEY (RecipientId) REFERENCES Recipient(RecipientId) ON DELETE SET NULL, " +
                         "FOREIGN KEY (PartnerId) REFERENCES Partner(PartnerId));");

        	//Start transactionID at 1000
            stmt.execute("ALTER TABLE Remittance AUTO_INCREMENT = 4514564");
            
            // ExchangeRate Table
            stmt.execute("CREATE TABLE IF NOT EXISTS ExchangeRate (" +
                         "ExchangeRateId INT AUTO_INCREMENT PRIMARY KEY, " +
                         "Country VARCHAR(255), " +
                         "TargetCurrency VARCHAR(255), " +
                         "Rate DOUBLE, " +
                         "UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);");
            
          //Start transactionID at 100
            stmt.execute("ALTER TABLE ExchangeRate AUTO_INCREMENT = 100");
            
            
            //Insert into Exchange Rate Table
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Ecuador', 'USD', 1.00)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Ghana', 'Cedis', 13.53)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Argentina', 'Argentina Peso', 874.5)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Columbia', 'Columbian Pesos', 3892.17)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Kenya', 'Kenyan Shilling', 134.5)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Nigeria', 'Naira', 780)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Canada', 'CAD', 1.36)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Brazil', 'Brazillizian Real', 5.12)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('France', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Austria', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Germany', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Ireland', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Italy', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Netherlands', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Portugal', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Spain', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Slovenia', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Slovekia', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Greece', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Estonia', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Luxemborg', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Malta', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Cyprus', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Belgium', 'Euro', 0.93)");
            stmt.execute("INSERT INTO ExchangeRate (Country, TargetCurrency, Rate) VALUES ('Finland', 'Euro', 0.93)");

        }
    }
}
