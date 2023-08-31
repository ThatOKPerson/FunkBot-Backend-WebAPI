package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.db.DeliveryEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {
    private DeliveryEmployeeDao deliveryEmployeeDao = new DeliveryEmployeeDao();
    private DeliveryEmployeeValidator deliveryEmployeeValidator = new DeliveryEmployeeValidator();

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws FailedToGetDeliveryEmployeeException {
        try {
            List<DeliveryEmployee> deliveryEmployeeList = deliveryEmployeeDao.getAllDeliveryEmployees();

            return deliveryEmployeeList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeeException();
        }
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) throws FailedToCreateDeliveryEmployeeException, InvalidDeliveryEmployeeException {
        try {
            String validation = deliveryEmployeeValidator.isValidDeliveryEmployee(deliveryEmployee);

            if (validation != null) {
                throw new InvalidDeliveryEmployeeException(validation);

            }
            int id = deliveryEmployeeDao.createDeliveryEmployee(deliveryEmployee);

            if (id == -1) {
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return -1;
    }

    public void updateDeliveryEmployee (int id, DeliveryEmployeeRequest deliveryEmployee) throws InvalidDeliveryEmployeeException, DeliveryEmployeeDoesNotExistException, FailedToUpdateDeliveryEmployeeException {
        try {
            String validation = deliveryEmployeeValidator.isValidDeliveryEmployee(deliveryEmployee);

            if (validation != null) {
                throw new InvalidDeliveryEmployeeException(validation);
            }
            DeliveryEmployee deliveryEmployeeToUpdate = deliveryEmployeeDao.getDeliveryEmployeeByID(id);

            if (deliveryEmployeeToUpdate == null) {
                throw new DeliveryEmployeeDoesNotExistException();
            }

            deliveryEmployeeDao.updateDeliveryEmployee(id, deliveryEmployee);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateDeliveryEmployeeException();
        }
    }

    public void deleteDeliveryEmployee (int id) throws DeliveryEmployeeDoesNotExistException, FailedToDeleteDeliveryEmployeeException {
        try {
            DeliveryEmployee deliveryEmployeeToDelete = deliveryEmployeeDao.getDeliveryEmployeeByID(id);

            if (deliveryEmployeeToDelete == null) {
                throw new DeliveryEmployeeDoesNotExistException();
            }

            deliveryEmployeeDao.deleteDeliveryEmployee(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteDeliveryEmployeeException();
        }
    }

}
