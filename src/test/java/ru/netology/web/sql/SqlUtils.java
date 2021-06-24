package ru.netology.web.sql;

import java.sql.*;

/**
 * Класс для работы с БД
 */
public class SqlUtils {

    public static boolean checkApprovedPayment() {
        final String sql = "SELECT p.*, o.* FROM payment_entity AS p LEFT JOIN order_entity AS o ON p.transaction_id = o.payment_id WHERE p.status == 'APPROVED'";

        return executeQuery(sql);
    }

    public static boolean checkDeclinedPayment() {
        final String sql = "SELECT p.*, o.* FROM payment_entity AS p LEFT JOIN order_entity AS o ON p.transaction_id = o.payment_id WHERE p.status == 'DECLINED'";

        return executeQuery(sql);
    }

    public static boolean checkApprovedCredit() {
        final String sql = "SELECT c.*, o.* FROM credit_request_entity AS c LEFT JOIN order_entity AS o ON c.id = o.credit_id WHERE c.status == 'APPROVED'";

        return executeQuery(sql);
    }

    public static boolean checkDeclinedCredit() {
        final String sql = "SELECT c.*, o.* FROM credit_request_entity AS c LEFT JOIN order_entity AS o ON c.id = o.credit_id WHERE c.status == 'DECLINED'";

        return executeQuery(sql);
    }

    /**
     * Выполнение соединения с базой и select-запрос
     *
     * @param sql
     * @return
     */
    public static boolean executeQuery(String sql) {

        try {
            Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException exception) {
            System.out.println("[executeQuery]: Error: " + exception.getMessage());
            return false;
        }

    }

    public static String getUrl() {
        System.out.println("[getUrl]: " + System.getProperty("datasource.url"));
        return System.getProperty("datasource.url");
    }

    public static String getPostgresUrl() {
        return "jdbc:postgresql://192.168.99.100:5432/app";
    }

    public static String getMySQLUrl() {
        return "jdbc:mysql://192.168.99.100:3306/app";
    }

    public static String getUser() {
        return "app";
    }

    public static String getPassword() {
        return "password";
    }

    /**
     * Очистка таблиц
     *
     * @throws SQLException
     */
    public static void cleanData() throws SQLException {
        String deleteOrderEntity = "DELETE FROM order_entity;";
        String deletePaymentEntity = "DELETE FROM payment_entity;";
        String deleteCreditEntity = "DELETE FROM credit_request_entity;";

        try (
                Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());

                PreparedStatement orderStatement = connection.prepareStatement(deleteOrderEntity);
                PreparedStatement paymentStatement = connection.prepareStatement(deletePaymentEntity);
                PreparedStatement creditStatement = connection.prepareStatement(deleteCreditEntity);
        ) {
            orderStatement.executeUpdate();
            paymentStatement.executeUpdate();
            creditStatement.executeUpdate();
        }
    }
}
