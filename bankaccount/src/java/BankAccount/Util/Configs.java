/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAccount.Util;

//import BankAccount.Configs.*;

/**
 *
 * @author ramson
 */
public class Configs {
    
    public static final String WITHDRAW = "Withdrawal";
    
    public static final String DEPOSIT = "Deposit";
    
    public static final int ACCOUNT_NUMBER = 1;
    
    public static final int MAX_DEPOSIT_IN_A_DAY = 150000;
    
    public static final int MAX_DEPOSIT_PER_TRANSACTION = 40000;
    
    public static final int MAX_DEPOSIT_FREQUENCY = 4;
    
    public static final int MAX_WITHDRAWAL_IN_A_DAY = 50000;
    
    public static final int MAX_WITHDRAWAL_PER_TRANSACTION = 20000;
    
    public static final int MAX_WITHDRAWAL_FREQUENCY = 3;
    
    // Insufficient funds / credit
    public static final String INSUFFICIENT_BALANCE = "Transaction could not be completed due to insufficient balance";
    // Exceeded Maximum number of transactions reached
    public static final String ERROR_MAXIMUM_TRANSACTIONS_PER_DAY = "Exceeded Maximum - per day";
    // Exceeded Maximum amount value per transaction
    public static final String ERROR_MAXIMUM_AMOUNT_VALUE_PER_TRANSACTION = "Exceeded Maximum - amount per transaction";
    // Exceeded Maximum amount per day
    public static final String ERROR_MAXIMUM_AMOUNT_VALUE_PER_DAY = "Exceeded Maximum - per day";
    // success result code
    public static final String RC_SUCCESS = "111";
    // Failed result code
    public static final String RC_FAILED = "000";
}
