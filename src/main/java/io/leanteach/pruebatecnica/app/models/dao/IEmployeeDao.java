package io.leanteach.pruebatecnica.app.models.dao;

import io.leanteach.pruebatecnica.app.dto.EmployeeByPositionDto;
import io.leanteach.pruebatecnica.app.models.entity.Employee;


import java.util.List;

public interface IEmployeeDao {

    public Long save(Employee employee);
    public List<Employee> findAll(String name, String position);
    public void delete(Long id);
    public List<EmployeeByPositionDto> findPosition();
}
