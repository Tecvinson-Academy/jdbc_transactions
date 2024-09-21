package com.tecvisonacademy.util;

public class SQLUtility {

    public static final String UPDATE_ACCOUNT_BALANCE_DEBIT =
            "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";

    public static final String UPDATE_ACCOUNT_BALANCE_CREDIT =
            "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";


    public static String UPDATE_INVENTORY_STOCK =
            "UPDATE inventory SET stock = stock - ? WHERE item_id = ?";

    public static final String INSERT_ORDER_HISTORY =
        "INSERT INTO order_history (order_id, item_id, quantity, customer_id) VALUES (?,?,?,?)";

    public static final String UPDATE_CUSTOMER_BALANCE =
            "UPDATE customers SET balance = balance - ? WHERE customer_id = ?";


    public static String getQuery(String key)
    {
        switch (key) {
            case "debit account":
                return UPDATE_ACCOUNT_BALANCE_DEBIT;
            case "credit account":
                return UPDATE_ACCOUNT_BALANCE_CREDIT;
            case "Deduct stock from inventory":
                return UPDATE_INVENTORY_STOCK;
            case "Insert order into order history":
                return INSERT_ORDER_HISTORY;
            case "Update customer's balance":
                return UPDATE_CUSTOMER_BALANCE;
            default:
                throw  new IllegalArgumentException("No such query: " + key);
        }
    }


}
