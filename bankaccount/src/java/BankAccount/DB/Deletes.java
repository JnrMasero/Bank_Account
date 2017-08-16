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
public class Deletes {
    
    public Connection con;

    public PreparedStatement ps;

    public ResultSet rs;

    public Statement st;
    
    //    inputs table
    // -------------------
    /**
     * 
     * @param session
     * @throws SQLException
     * 
     * This method clears all session inputs from the inputs table
     * Use this method to clear agent inputs on cancel 
     * 
     */
    public void deleteAgentInputs(String session) throws SQLException{
        String sql = "DELETE FROM inputs * WHERE session_id = ?";
        PostgresConnector conn = new PostgresConnector();
        con = conn.createConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, session);
        int executedUpdates = ps.executeUpdate();
        
        System.out.println(executedUpdates);
        
        con.close();
        ps.close();    
        conn = null;
    }
}
