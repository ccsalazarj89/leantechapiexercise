package io.leanteach.pruebatecnica.app.models.dao;

import io.leanteach.pruebatecnica.app.dto.EmployeeByPositionDto;
import io.leanteach.pruebatecnica.app.dto.UpdateEmployeeDto;
import io.leanteach.pruebatecnica.app.models.entity.Employee;
import org.hibernate.sql.Update;


import java.util.List;

public interface IEmployeeDao {

    public Long save(Employee employee);
    public List<Employee> findAll(String name, String position);
    public void delete(Long id);
    public void update(UpdateEmployeeDto updateEmployeeDto);
    public List<EmployeeByPositionDto> findPosition();
}
