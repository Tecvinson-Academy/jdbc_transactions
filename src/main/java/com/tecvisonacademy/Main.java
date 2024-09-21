package com.tecvisonacademy;

import com.tecvisonacademy.service.BankTransactionService;
import com.tecvisonacademy.service.SavepointService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //BankTransactionService bankTransactionService = new BankTransactionService();
        //bankTransactionService.transferFunds(1, 2, 50);


        SavepointService savepointService = new SavepointService();
        savepointService.executeWithSavepoint();
    }
}