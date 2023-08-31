package org.kainos.ea.db;

import org.kainos.ea.cli.SalesEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesEmployeeDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();
    public int createSalesEmployee(SalesEmployee salesEmployee) throws SQLException {
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
}