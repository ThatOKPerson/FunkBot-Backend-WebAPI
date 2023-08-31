package org.kainos.ea.api;

import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.FailedToCreateSalesEmployeeException;
import org.kainos.ea.client.FailedToGetSalesEmployeeException;
import org.kainos.ea.client.InvalidSalesEmployeeException;
import org.kainos.ea.core.SalesEmployeeValidator;
import org.kainos.ea.db.SalesEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {
    private SalesEmployeeDao dao = new SalesEmployeeDao();
    public int createSalesEmployee(SalesEmployeeRequest salesEmployee) throws FailedToCreateSalesEmployeeException, InvalidSalesEmployeeException {
        try {
            String validation = SalesEmployeeValidator.isValidSalesEmployee(salesEmployee);

            if(validation != null){
                throw new InvalidSalesEmployeeException(validation);
            }

            int id = dao.createSalesEmployee(salesEmployee);

            if(id == -1){
                throw new FailedToCreateSalesEmployeeException();
            }
            return id;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToCreateSalesEmployeeException();
        }
    }
    public List<SalesEmployeeRequest> getAllSalesEmployees() throws FailedToGetSalesEmployeeException {

        List<SalesEmployeeRequest> salesEmployeeList = null;
        try {
            salesEmployeeList = dao.getAllSalesEmployees();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetSalesEmployeeException();
        }
        return salesEmployeeList;
    }
}
