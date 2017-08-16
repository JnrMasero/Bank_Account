/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAccount.DB;

import java.sql.Connection;
import java.sql.Date;
//import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author Cain
 */
public class Inserts {

    // Declare global variables
    public Connection con;
    //public PreparedStatement ps;
    public ResultSet rs;
    public Statement stmt;
    
    public void transaction(int acc_number, int amount, String trans_type){
        try {
            String sql = "INSERT INTO transactions (acc_number,amount,trans_type) values(?,?,?)";
            PostgresConnector conn = new PostgresConnector();
            con = conn.createConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, acc_number);
            pstmt.setInt(2, amount);
            pstmt.setString(3, trans_type);
            //pstmt.setString(3, password);

            int count = pstmt.executeUpdate();

            System.out.println("Input inserts: " + count);
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    + "\nSQL Message: " + ex.getMessage() + "\n"
                    + "Cause :" + ex.getCause() + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
}
