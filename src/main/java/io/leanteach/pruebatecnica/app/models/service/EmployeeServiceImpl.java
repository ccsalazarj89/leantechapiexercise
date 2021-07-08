package io.leanteach.pruebatecnica.app.models.service;

import io.leanteach.pruebatecnica.app.dto.EmployeeByPositionDto;
import io.leanteach.pruebatecnica.app.dto.UpdateEmployeeDto;
import io.leanteach.pruebatecnica.app.models.dao.IEmployeeDao;
import io.leanteach.pruebatecnica.app.models.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    @Transactional
    public Long save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Employee> findAll(String name, String position) {
        return employeeDao.findAll(name,position);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        employeeDao.delete(id);
    }

    @Override
    @Transactional
    public void update(UpdateEmployeeDto updateEmployeeDto) {

        employeeDao.update(updateEmployeeDto);
    }

    @Override
    public List<EmployeeByPositionDto> findPosition() {
        return employeeDao.findPosition();
    }
}
