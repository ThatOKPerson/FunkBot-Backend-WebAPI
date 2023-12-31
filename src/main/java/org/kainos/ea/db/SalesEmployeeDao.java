package org.kainos.ea.db;

import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();
    public int createSalesEmployee(SalesEmployeeRequest salesEmployee) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO SalesEmployee (Name, Salary, BankAccNumber, NINumber, CommissionRate) VALUES (?,?,?,?,?);";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, salesEmployee.getName());
        st.setDouble(2, salesEmployee.getSalary());
        st.setString(3, salesEmployee.getBankAccNumber());
        st.setString(4, salesEmployee.getNiNumber());
        st.setDouble(5, salesEmployee.getCommRate());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()){
            return rs.getInt(1);
        }

        return -1;
    }

    public List<SalesEmployee> getAllSalesEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT SalesEmployeeID, Name, Salary, BankAccNumber, NINumber, CommissionRate FROM SalesEmployee;");

        List<SalesEmployee> list = new ArrayList<>();

        while (rs.next()) {
            SalesEmployee salesEmployee = new SalesEmployee(
                    rs.getInt("SalesEmployeeID"),
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccNumber"),
                    rs.getString("NINumber"),
                    rs.getDouble("CommissionRate")
            );
            list.add(salesEmployee);
        }
        return list;
    }

    public SalesEmployee getSalesEmployeeByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT SalesEmployeeID, Name, Salary, BankAccNumber, NINumber, CommissionRate FROM SalesEmployee WHERE SalesEmployeeID = " + id + ";");

        while (rs.next()) {
            return new SalesEmployee(
                    rs.getInt("SalesEmployeeID"),
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccNumber"),
                    rs.getString("NINumber"),
                    rs.getDouble("CommissionRate")
            );
        }
        return null;
    }

    public void updateSalesEmployee(int id, SalesEmployeeRequest salesEmployee) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String updateStatement = "UPDATE salesEmployee SET Name = ?, Salary = ?, BankAccNumber = ?, NINumber = ?, CommissionRate = ? WHERE SalesEmployeeID = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, salesEmployee.getName());
        st.setDouble(2, salesEmployee.getSalary());
        st.setString(3, salesEmployee.getBankAccNumber());
        st.setString(4, salesEmployee.getNiNumber());
        st.setDouble(5, salesEmployee.getCommRate());
        st.setInt(6, id);

        st.executeUpdate();
    }
}