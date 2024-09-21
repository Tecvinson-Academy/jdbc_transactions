package com.tecvisonacademy.service;

import com.tecvisonacademy.config.DataSourceConfig;
import com.tecvisonacademy.dao.BankDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class BankTransactionService {

    private BankDAO bankDAO = new BankDAO();

    public void transferFunds(int fromAccountId, int toAccountId, double amount)
    {
        Connection connection = null;

        try
        {
            connection = DataSourceConfig.getDataSource().getConnection();
            connection.setAutoCommit(false);

            //Debit from source account
            boolean debitSource = bankDAO.debitAccount(connection, fromAccountId, amount);

            //Credit to destination account
            boolean creditDestination = bankDAO.creditAccount(connection, toAccountId, amount);

            if (!debitSource || !creditDestination)
            {
                throw  new SQLException("Transaction failed: Debit or Credit operation was not successful");
            }

            //Commit only if both operations succeeds
            connection.commit();
            System.out.println("Transaction successful");


        }catch(SQLException ex)
        {
           if(connection != null)
           {
               try{
                   connection.rollback();
                   System.out.println("Transaction failed, rolled back.");
               }catch (SQLException rollbackException)
               {
                   rollbackException.printStackTrace();
               }
           }
            ex.printStackTrace();
        } finally{
            try{
                if( connection !=null) connection.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
}
