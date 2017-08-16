/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAccount.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Cain
 */
public class Updates {

    // Declare global variables
    public Connection con;
    public PreparedStatement ps;
    public ResultSet rs;
    public Statement stmt;

    public void updateBalance(int amount, int id) {
        try {
            String updateTable = "UPDATE accounts SET active_balance=? where acc_number=?";
            PostgresConnector conn = new PostgresConnector();
            con = conn.createConnection();
            ps = con.prepareStatement(updateTable);
            ps.setFloat(1, amount);
            ps.setInt(2, id);
            
            int v = ps.executeUpdate();
            
            con.close();
            ps.close();
            
            System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n"
                    + v + " updates \n"
                    + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    + "\nSQL Message: " + ex.getMessage() + "\n"
                    + "Cause :" + ex.getCause() + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }
}
