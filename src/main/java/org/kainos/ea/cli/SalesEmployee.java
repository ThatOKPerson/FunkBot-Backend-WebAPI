package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesEmployee {
    private int salesEmployeeId;
    private String name;
    private Double salary;
    private String bankAccNumber;
    private String niNumber;
    private Double commRate;

    public SalesEmployee(int salesEmployeeId, String name, Double salary, String bankAccNumber, String niNumber, Double commRate) {
        this.salesEmployeeId = salesEmployeeId;
        this.name = name;
        this.salary = salary;
        this.bankAccNumber = bankAccNumber;
        this.niNumber = niNumber;
        this.commRate = commRate;
    }

    public int getSalesEmployeeId() {
        return salesEmployeeId;
    }

    public void setSalesEmployeeId(int salesEmployeeId) {
        this.salesEmployeeId = salesEmployeeId;
    }

    public Double getCommRate() {
        return commRate;
    }

    public void setCommRate(Double commRate) {
        this.commRate = commRate;
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