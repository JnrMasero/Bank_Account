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
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnector {
    public static String dbState ="";
    
    public Connection createConnection() {
        // 
        dbState += "------Connecting to PostgreSQL------\n";        
        try{        
            Class.forName("org.postgresql.Driver");
        }        
        catch(ClassNotFoundException e){        
            dbState += "Postgres Driver not found" + e.getMessage() + "\n";            
            //e.printStackTrace();
            //            
        }        
        //System.out.println("PostgreSQL Driver Registered!");        
        Connection connection =null;        
        try{        
            connection = DriverManager.getConnection("jdbc:postgresql://192.168.20.8:5432/bankaccount", "postgres", "");
        }        
        catch(SQLException e){
            dbState += "Connection Failed: " + e.getMessage() + "\n";          
            //e.printStackTrace();
        }
        //
        if(connection != null){        
            dbState += "bankaccount : CONNECTION ESTABLISHED\n";        
        }        
        else{        
            dbState += "Failed to make connection!\n";        
        }        
        
        System.out.println("\n\n\n\n"
                + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                + dbState + "\n"
                + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return connection;        
    }
    
    public String getDbState(){
        return dbState;
    }
}
