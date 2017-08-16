/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAccount.Util.POJOs;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramson
 */
@XmlRootElement(name = "Balance")
public class Balance {
    //int resultCode;
    private String resultCode, message, active_balance, date;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActive_balance() {
        return active_balance;
    }

    public void setActive_balance(String active_balance) {
        this.active_balance = active_balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

        
}
