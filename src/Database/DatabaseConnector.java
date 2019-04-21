/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author slinger
 */
public class DatabaseConnector {

    static Connection conn;

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:sqlite:/home/slinger/orderDatabase.sqlite";

        conn = DriverManager.getConnection(url);

        System.out.println("connected");

        return conn;

    }

}
