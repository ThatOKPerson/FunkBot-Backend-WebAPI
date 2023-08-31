package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT DeliveryEmployeeID, Name, Salary, BankAccNumber, NINumber FROM DeliveryEmployee;");

        List<DeliveryEmployee> deliveryEmployeeList = new ArrayList<>();

        while (rs.next()) {
            DeliveryEmployee deliveryEmployee = new DeliveryEmployee(
                    rs.getInt("DeliveryEmployeeID"),
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccNumber"),
                    rs.getString("NINumber")
            );

            deliveryEmployeeList.add(deliveryEmployee);

        }
        return deliveryEmployeeList;
    }

    public DeliveryEmployee getDeliveryEmployeeByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT DeliveryEmployeeID, Name, Salary, BankAccNumber, NINumber" +
                " FROM DeliveryEmployee WHERE DeliveryEmployeeID = " + id);


        while (rs.next()) {
            return new DeliveryEmployee(
                    rs.getInt("DeliveryEmployeeID"),
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccNumber"),
                    rs.getString("NINumber")
            );
        }
        return null;
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO DeliveryEmployee (Name, Salary, BankAccNumber, NINumber) VALUES (?,?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, deliveryEmployee.getName());
        st.setDouble(2, deliveryEmployee.getSalary());
        st.setString(3, deliveryEmployee.getBankAccNumber());
        st.setString(4, deliveryEmployee.getNiNumber());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public void updateDeliveryEmployee(int id, DeliveryEmployeeRequest deliveryEmployee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE DeliveryEmployee SET Name = ?, BankAccNumber = ?, NINumber = ?, Salary = ? WHERE DeliveryEmployeeID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, deliveryEmployee.getName());
        st.setString(2, deliveryEmployee.getBankAccNumber());
        st.setString(3, deliveryEmployee.getNiNumber());
        st.setDouble(4, deliveryEmployee.getSalary());
        st.setInt(5, id);

        st.executeUpdate();
    }

    public void deleteDeliveryEmployee(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE FROM DeliveryEmployee WHERE DeliveryEmployeeID = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);
        ;
        st.setInt(1, id);

        st.executeUpdate();
    }
}
