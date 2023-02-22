
/********************************************************************************************
 * Java Course 3 Module 4
 * 
 * User Policies - retrieving the records and getting the date registered before '2012-01-01'
 * 
 * @author: Jellie Mae Ortiz
 * Date Created: May 25, 2022
 ********************************************************************************************/

import java.sql.*; // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package

public class UserPolicy {

    public static void main(String[] args) {

        try (
                // Step 1: Construct a database 'Connection' object called 'conn'
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserPolicy", "root",
                        "Root123"); // For MySQL only
                Statement stmt = con.createStatement();)

        {
            // created a string variable and query to retrieve the records from
            // user_policies table
            String strSelect = "select * from user_policies";
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("");
            System.out.println("The user policies are:\n");
            System.out.printf("%5s %12s %20s %20s", "Policy No", "User ID", "Date Registered", "Policy Type ID \n");

            while (rset.next()) { // Repeatedly process each row
                String pNum = rset.getString("policy_no"); // retrieve a 'double'-cell in the row same goes with the
                                                           // other variables
                int userId = rset.getInt("user_id");
                String date = rset.getString("date_registered");
                String pTypeID = rset.getString("policy_type_id");
                System.out.printf(pNum + "\t \t" + userId + "\t \t" + date + "\t \t" + pTypeID + "\n");// printing the
                                                                                                       // values of the
                                                                                                       // fields
            }

            // created a string variable and query to retrieve the records of the user
            // policies registered before '2012-01-01'
            String strSelectDate = "select * from user_policies where date_registered < '2012-01-01'";
            rset = stmt.executeQuery(strSelectDate);

            System.out.println("\nThe registered policy/ies before 2012-01-01 is/are: ");

            // loop for getting the registered policy before 2012-01-01
            while (rset.next()) { // Repeatedly process each row
                String pNum = rset.getString("policy_no");
                int userId = rset.getInt("user_id");
                String date = rset.getString("date_registered");
                String pTypeID = rset.getString("policy_type_id");
                System.out.println("\n" + pNum + "," + " " + userId + "," + " " + date + "," + " " + pTypeID);// printing
                                                                                                              // the
                                                                                                              // values
                                                                                                              // of the
                                                                                                              // field
            }

            con.close();// closing the DB connection

        } catch (SQLException ex) {
            ex.printStackTrace();// catch exception
        }
    }
}
