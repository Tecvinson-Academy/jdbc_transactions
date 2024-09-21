package com.tecvisonacademy.dao;

import com.tecvisonacademy.util.SQLUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankDAO {

public boolean debitAccount(Connection connection, int accountId, double amount) throws SQLException
    {
        //Get the SQL statement
        String sql = SQLUtility.getQuery("debit account");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setDouble(1, amount); //Amount you want to debit
            preparedStatement.setInt(2, accountId); //Account you want to debit from

            //Execute the SQL query
            int rowsUpdate = preparedStatement.executeUpdate();

            return rowsUpdate > 0;//Return true if update is successful
        }
    }

    public boolean creditAccount(Connection connection, int accountId, double amount) throws SQLException
    {
        //Get the SQL statement
        String sql = SQLUtility.getQuery("credit account");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setDouble(1, amount); //Amount you want to debit
            preparedStatement.setInt(2, accountId); //Account you want to debit from

            //Execute the SQL query
            int rowsUpdate = preparedStatement.executeUpdate();

            return rowsUpdate > 0;//Return true if update is successful
        }

    }
}
