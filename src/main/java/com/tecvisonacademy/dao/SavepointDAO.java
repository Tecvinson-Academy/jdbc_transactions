package com.tecvisonacademy.dao;

import com.tecvisonacademy.util.SQLUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class SavepointDAO {

    public void deductStock(Connection connection, int stock, int itemId) throws SQLException
    {
        String sql = SQLUtility.getQuery("Deduct stock from inventory");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, stock);
            preparedStatement.setInt(2,itemId);
            preparedStatement.executeUpdate();
        }
    }

    public void insertOrderHistory(Connection connection, int orderId, int itemId, int quantity, int customerId) throws SQLException
    {
        String sql = SQLUtility.getQuery("Insert order into order history");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2,itemId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4,customerId);
            preparedStatement.executeUpdate();
        }
    }

    public void updateCustomerBalance(Connection connection, double amount, int customerId) throws SQLException
    {
        String sql = SQLUtility.getQuery("Update customer's balance");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2,customerId);
            preparedStatement.executeUpdate();
        }
    }

    public Savepoint createSavepoint(Connection connection, String savepointName) throws SQLException
    {
        return connection.setSavepoint(savepointName);
    }

    public void rollbackToSavepoint(Connection connection, Savepoint savepoint) throws SQLException
    {
        connection.rollback(savepoint);
    }

    public void rollbackTransaction(Connection connection) throws SQLException
    {
        connection.rollback();
    }

    public void commitTransaction(Connection connection) throws SQLException
    {
        connection.commit();
    }
}
