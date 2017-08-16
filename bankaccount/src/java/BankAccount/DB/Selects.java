/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAccount.DB;

/**
 *
 * @author Cain
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Selects {

    public Connection con;

    public PreparedStatement ps;

    public ResultSet rs;

    public Statement st;
    
    public int getActiveBalance(int acc_number){
        int balance = 0;
        
        try {
            String sql = "SELECT active_balance from accounts where acc_number = ?;";
            PostgresConnector conn = new PostgresConnector();
            con = conn.createConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, acc_number);
            rs = ps.executeQuery();

            // Expecting one value for balance
            if (rs.next()) {
                balance = rs.getInt("active_balance");
            }

            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    + "\nSQL Message: " + ex.getMessage() + "\n"
                    + "Cause :" + ex.getCause() + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (Exception ex){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    + "\nSQL Message: " + ex.getMessage() + "\n"
                    + "Cause :" + ex.getCause() + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        
        return balance;
    }
    
    public int sumOfTransactionsOn(String trans_date, String trans_type){
        int totalTransactionValue = 0;
        try {
            String sql = "SELECT COALESCE(sum(amount), 0) from transactions where trans_type = ? and trans_date::Date = ?::Date;";
            PostgresConnector conn = new PostgresConnector();
            con = conn.createConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, trans_type);
            ps.setString(2, trans_date);
            //ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                totalTransactionValue = rs.getInt("coalesce");
            }

            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    + "\nSQL Message: " + ex.getMessage() + "\n"
                    + "Cause :" + ex.getCause() + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (Exception ex){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    + "\nSQL Message: " + ex.getMessage() + "\n"
                    + "Cause :" + ex.getCause() + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        
        return totalTransactionValue;
    }
    
    public int transactionFrequency(String trans_date, String trans_type){
        int frequency = 0;
	System.out.println("!!!!!!!!!!! trans_date " + trans_date);
        System.out.println("!!!!!!!!!!! trans_type " + trans_type);
        
        try {
            String sql = "SELECT COALESCE(count(rec_id), 0) from transactions where trans_type = ? and trans_date::date = ?::Date;";
            PostgresConnector conn = new PostgresConnector();
            con = conn.createConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, trans_type);
            ps.setString(2, trans_date);
            //ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                frequency = rs.getInt("coalesce");
		System.out.println("!!!!!!!!!!! frequency " + frequency);
            }

            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    + "\nSQL Message: " + ex.getMessage() + "\n"
                    + "Cause :" + ex.getCause() + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (Exception ex){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    + "\nSQL Message: " + ex.getMessage() + "\n"
                    + "Cause :" + ex.getCause() + "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        
        return frequency;
    }
}
