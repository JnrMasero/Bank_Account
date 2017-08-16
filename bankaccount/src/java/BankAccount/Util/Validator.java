/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAccount.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ramson
 */
public class Validator {
    
    public boolean isValidInput(String input, String expression){
        CharSequence inputStr = input;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }
}
