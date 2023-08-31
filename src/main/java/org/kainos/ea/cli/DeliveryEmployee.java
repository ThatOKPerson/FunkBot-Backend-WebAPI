package org.kainos.ea.cli;

public class DeliveryEmployee {
    private int deliveryEmployeeId;
    private String name;
    private Double salary;
    private String bankAccNumber;
    private String niNumber;

    public DeliveryEmployee(int deliveryEmployeeId, String name, Double salary, String bankAccNumber, String niNumber) {
        setDeliveryEmployeeId(deliveryEmployeeId);
        setName(name);
        setSalary(salary);
        setBankAccNumber(bankAccNumber);
        setNiNumber(niNumber);
    }

    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(int deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getBankAccNumber() {
        return bankAccNumber;
    }

    public void setBankAccNumber(String bankAccNumber) {
        this.bankAccNumber = bankAccNumber;
    }

    public String getNiNumber() {
        return niNumber;
    }

    public void setNiNumber(String niNumber) {
        this.niNumber = niNumber;
    }
}
