package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryEmployeeRequest {
    private String name;
    private Double salary;
    private String bankAccNumber;
    private String niNumber;

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
    @JsonCreator
    public DeliveryEmployeeRequest(
            @JsonProperty("name") String name,
            @JsonProperty("bankAccNumber") String bankAccNumber,
            @JsonProperty("niNumber") String niNumber,
            @JsonProperty("salary") Double salary) {
        this.name = name;
        this.bankAccNumber = bankAccNumber;
        this.niNumber = niNumber;
        this.salary = salary;
    }
}
