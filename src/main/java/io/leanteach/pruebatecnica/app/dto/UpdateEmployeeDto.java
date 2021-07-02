package io.leanteach.pruebatecnica.app.dto;

public class UpdateEmployeeDto {

    private Long id;
    private Double salary;
    private Long positionId;

    public UpdateEmployeeDto() {
    }

    public UpdateEmployeeDto(Long id, Double salary, Long positionId) {
        this.id = id;
        this.salary = salary;
        this.positionId = positionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}
