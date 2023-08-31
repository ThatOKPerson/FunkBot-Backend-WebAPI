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

    public List<SalesEmployeeRequest> getAllSalesEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT Name, Salary, BankAccNumber, NINumber, CommissionRate FROM SalesEmployee;");

        List<SalesEmployeeRequest> orderList = new ArrayList<>();

        while (rs.next()) {
            SalesEmployeeRequest salesEmployee = new SalesEmployeeRequest(
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccNumber"),
                    rs.getString("NINumber"),
                    rs.getDouble("CommissionRate")
            );
            orderList.add(salesEmployee);
        }
        return orderList;
    }
}