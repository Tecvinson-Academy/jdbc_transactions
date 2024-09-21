package com.tecvisonacademy.service;

import com.tecvisonacademy.config.DataSourceConfig;
import com.tecvisonacademy.dao.SavepointDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Random;

public class SavepointService {

    private SavepointDAO savepointDAO = new SavepointDAO();

    public void executeWithSavepoint(){
        Connection connection = null;
        Savepoint savepoint = null;

        try{
            connection = DataSourceConfig.getDataSource().getConnection();
            connection.setAutoCommit(false);

            //Deduct Stock
            savepointDAO.deductStock(connection, 10,1);

            //Set savepoint after stock deduction
            savepoint = savepointDAO.createSavepoint(connection, "StockDeduction");


            //Insert into order History
            Random random = new Random();
            int orderId = random.nextInt(10000) + 1;
            savepointDAO.insertOrderHistory(connection, orderId, 1,10,1);

            boolean paymentSuccessful = false;

            try{
                savepointDAO.updateCustomerBalance(connection, 500,1);
                paymentSuccessful = true;
            }catch (SQLException ee)
            {
                if(savepoint != null){
                     savepointDAO.rollbackToSavepoint(connection, savepoint);
                    System.out.println("Rolled back to savepoint, Inventory deduction remains.");
                }else
                {
                    savepointDAO.rollbackTransaction(connection);
                    System.out.println("Full rollback due to error");
                }
            }

            if(paymentSuccessful)
            {
                savepointDAO.commitTransaction(connection);
                System.out.println("Transaction commited successfully.");
            }

        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }finally {
            try{
               if(connection != null) connection.close();
            }catch (SQLException rx)
            {
                rx.printStackTrace();
            }
        }
    }
}
