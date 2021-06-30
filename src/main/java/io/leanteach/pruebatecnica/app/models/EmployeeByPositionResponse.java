package io.leanteach.pruebatecnica.app.models;

import io.leanteach.pruebatecnica.app.models.entity.Employee;

public class EmployeeByPositionResponse {

    private Long id;
    private String positionName;
    private Employee employee;

    public EmployeeByPositionResponse(Long id, String positionName, Employee employee) {
        this.id = id;
        this.positionName = positionName;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
