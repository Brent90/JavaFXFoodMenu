/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author slinger
 */
public class DAO {

    public void insertOrder(String time, String description, int orderNumber) {

        String insert = "insert into orders (time, description, orderNumber) \n"
                + "values(" + "'" + time + "'" + "," + "'" + description + "'" + "," + orderNumber + ")";
                 //"values(" + orderNumber  + "," + "'" + description + "'" + "," + "'" + date + "'" + ")";

        try {
          dbExecuteUpdate(insert);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    
        public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            Connection con = DatabaseConnector.getConnection();
            //Create Statement
            stmt = con.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        }
    }
        
    

}
