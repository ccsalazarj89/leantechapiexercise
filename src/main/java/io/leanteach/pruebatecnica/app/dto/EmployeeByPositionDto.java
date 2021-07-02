package io.leanteach.pruebatecnica.app.dto;


public class EmployeeByPositionDto {

        private String positionName;
        private Long employeeId;
        private Double salary;
        private Long personId;
        private Long positionId;
        private String personName;
        private String personLastName;
        private String address;
        private String cityName;
        private String cellPhone;

    public EmployeeByPositionDto() {
    }

    public EmployeeByPositionDto(String positionName, Long employeeId, Double salary,
                                 Long personId, Long positionId, String personName,
                                 String personLastName, String address, String cityName, String cellPhone) {
        this.positionName = positionName;
        this.employeeId = employeeId;
        this.salary = salary;
        this.personId = personId;
        this.positionId = positionId;
        this.personName = personName;
        this.personLastName = personLastName;
        this.address = address;
        this.cityName = cityName;
        this.cellPhone = cellPhone;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
}
