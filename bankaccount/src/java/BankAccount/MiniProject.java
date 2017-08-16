/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankAccount;

/**
 *
 * @author ramson
 */
import BankAccount.DB.Inserts;
import BankAccount.DB.Selects;
import BankAccount.DB.Updates;
import BankAccount.Util.Configs;
import BankAccount.Util.POJOs.Balance;
import BankAccount.Util.POJOs.Deposit;
import BankAccount.Util.POJOs.Withdraw;
import BankAccount.Util.Validator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/minibank")
@Produces("application/json")
public class MiniProject {

    @GET
    @Path("/balance/")
    @Produces("application/json")
    public Response balance() {
        Balance bal = new Balance();
        Selects sel = new Selects();
        int balance = sel.getActiveBalance(Configs.ACCOUNT_NUMBER);
        bal.setMessage("Successful");
        bal.setActive_balance("" + balance);
        bal.setDate(dateToday());
        bal.setResultCode(Configs.RC_SUCCESS);
        return Response.status(Response.Status.OK).entity(bal).build();
    }

    @POST
    @Path("/deposit/{amount}/")
    @Produces("application/json")
    public Response deposit(@PathParam("amount") String amount) {
        Validator validate = new Validator();
        Deposit dep = new Deposit();
        dep.setDate(dateToday());
        dep.setDeposit_amount(amount);
        Inserts ins = new Inserts();
        Updates ups = new Updates();
        Selects sel = new Selects();

        // validate amount ie. must be int        
        if (validate.isValidInput(amount, "[0-9]*") && amount.length() < 11) {
            int depositAmount = Integer.parseInt(amount);

            // Check whether amount is within the allowed deposit amount per transaction
            if (depositAmount < Configs.MAX_DEPOSIT_PER_TRANSACTION) {
                // Get the total deposit for the the day
                int totalDepositsToday = sel.sumOfTransactionsOn(dateToday(), Configs.DEPOSIT);
                // Check if there is a breach in max deposit per day
                if ((depositAmount + totalDepositsToday) < Configs.MAX_DEPOSIT_IN_A_DAY) {
                    // Check that the total deposit frequency has not been breached
                    int totalDepositAttempts = sel.transactionFrequency(dateToday(), Configs.DEPOSIT);
                    if (totalDepositAttempts < Configs.MAX_DEPOSIT_FREQUENCY) {
                        // Save the transaction

                        int activeBalance = sel.getActiveBalance(Configs.ACCOUNT_NUMBER);
                        ins.transaction(Configs.ACCOUNT_NUMBER, depositAmount, Configs.DEPOSIT);
                        // Update the balance
                        ups.updateBalance((activeBalance + depositAmount), Configs.ACCOUNT_NUMBER);

                        dep.setMessage("Successful Transaction");
                        dep.setResultCode(Configs.RC_SUCCESS);
                        return Response.status(Response.Status.OK).entity(dep).build();

                    } else { // Error, frequency exceeded
                        dep.setMessage(Configs.ERROR_MAXIMUM_TRANSACTIONS_PER_DAY.replaceAll("-", Configs.DEPOSIT));
                        dep.setResultCode(Configs.RC_FAILED);
                        return Response.status(Response.Status.OK).entity(dep).build();
                    }

                } else { // Error, Maximum deposit per day exceeded
                    dep.setMessage(Configs.ERROR_MAXIMUM_AMOUNT_VALUE_PER_DAY.replaceAll("-", Configs.DEPOSIT));
                    dep.setResultCode(Configs.RC_FAILED);
                    return Response.status(Response.Status.OK).entity(dep).build();
                }

            } else { // Error, Maximum deposit per transaction
                dep.setMessage(Configs.ERROR_MAXIMUM_AMOUNT_VALUE_PER_TRANSACTION.replaceAll("-", Configs.DEPOSIT));
                dep.setResultCode(Configs.RC_FAILED);
                return Response.status(Response.Status.OK).entity(dep).build();
            }

        } else { // return error
            dep.setMessage("Invalid amount. Amount must be an Integer");
            dep.setResultCode(Configs.RC_FAILED);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dep).build();
        }
    }

    @POST
    @Path("/withdraw/{amount}/")
    @Produces("application/json")
    public Response withdraw(@PathParam("amount") String val) {
        Validator validate = new Validator();
        Withdraw with = new Withdraw();
        with.setDate(dateToday());
        with.setWithdraw_amount(val);
        Inserts ins = new Inserts();
        Updates ups = new Updates();
        Selects sel = new Selects();

        // Validate the amount to withdraw
        if (validate.isValidInput(val, "[0-9]*") && val.length() < 11) {
            int withdrawalAmount = Integer.parseInt(val);

            // Check withdrawal amount per transaction
            if (withdrawalAmount < Configs.MAX_WITHDRAWAL_PER_TRANSACTION) {
                // Check whether the Max withdrawal per day has been reached
                int totalWithdrawalsToday = sel.sumOfTransactionsOn(dateToday(), Configs.WITHDRAW);
                if ((withdrawalAmount + totalWithdrawalsToday) < Configs.MAX_WITHDRAWAL_IN_A_DAY) {
                    // get the number of withdrawal attempts today
                    int totalWithdrawalAttempts = sel.transactionFrequency(dateToday(), Configs.WITHDRAW);
                    // Check total withdraw attempts
                    if (totalWithdrawalAttempts < Configs.MAX_WITHDRAWAL_FREQUENCY) {
                        // Check whether there is sufficient balance
                        int activeBalance = sel.getActiveBalance(Configs.ACCOUNT_NUMBER);

                        if (activeBalance > withdrawalAmount) {

                            ins.transaction(Configs.ACCOUNT_NUMBER, withdrawalAmount, Configs.WITHDRAW);
                            // Update the balance
                            ups.updateBalance((activeBalance - withdrawalAmount), Configs.ACCOUNT_NUMBER);

                            with.setMessage("Successful Transaction");
                            with.setResultCode(Configs.RC_SUCCESS);
                            return Response.status(Response.Status.OK).entity(with).build();
                        } else {
                            with.setMessage(Configs.INSUFFICIENT_BALANCE);
                            with.setResultCode(Configs.RC_FAILED);
                            return Response.status(Response.Status.OK).entity(with).build();
                        }

                    } else {
                        with.setMessage(Configs.ERROR_MAXIMUM_TRANSACTIONS_PER_DAY.replaceAll("-", Configs.WITHDRAW));
                        with.setResultCode(Configs.RC_FAILED);
                        return Response.status(Response.Status.OK).entity(with).build();
                    }
                } else {
                    with.setMessage(Configs.ERROR_MAXIMUM_AMOUNT_VALUE_PER_DAY.replaceAll("-", Configs.WITHDRAW));
                    with.setResultCode(Configs.RC_FAILED);
                    return Response.status(Response.Status.OK).entity(with).build();
                }
            } else {
                with.setMessage(Configs.ERROR_MAXIMUM_AMOUNT_VALUE_PER_TRANSACTION.replaceAll("-", Configs.WITHDRAW));
                with.setResultCode(Configs.RC_FAILED);
                return Response.status(Response.Status.OK).entity(with).build();
            }
        } else {
            with.setMessage("Invalid amount. Amount must be an Integer");
            with.setResultCode(Configs.RC_FAILED);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(with).build();
        }
    }

    public String dateToday() {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}
