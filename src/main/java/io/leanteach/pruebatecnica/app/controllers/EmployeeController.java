package io.leanteach.pruebatecnica.app.controllers;


import io.leanteach.pruebatecnica.app.dto.EmployeeByPositionDto;
import io.leanteach.pruebatecnica.app.models.EmployeeByPositionResponse;
import io.leanteach.pruebatecnica.app.models.dao.IEmployeeDao;
import io.leanteach.pruebatecnica.app.models.entity.Employee;
import io.leanteach.pruebatecnica.app.models.entity.Person;
import io.leanteach.pruebatecnica.app.models.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private IEmployeeDao employeeDao;

    @GetMapping(value = "/listEmployee")
    public List<Employee> listEmployee(@RequestParam(defaultValue = "") String name , @RequestParam(defaultValue = "") String position){

        return employeeDao.findAll(name,position);

    }

    @GetMapping(value = "/listPosition")
    public List<EmployeeByPositionResponse> listPosition(){

        EmployeeByPositionDto employeeByPositionDto = new EmployeeByPositionDto();
        List<EmployeeByPositionResponse> employeeByPositionResponseList= new ArrayList<EmployeeByPositionResponse>();

        List<EmployeeByPositionDto> result =  employeeDao.findPosition();

        result.forEach(
                list -> employeeByPositionResponseList
                        .add(new EmployeeByPositionResponse(list.getPositionId(),
                                                            list.getName(),
                                                            new Employee(list.getEmployeeId(),
                                                                         new Person(list.getPersonId(),
                                                                                    list.getPersonName(),
                                                                                    list.getPersonLastName(),
                                                                                    list.getAddress(),
                                                                                    list.getCellphone(),
                                                                                    list.getCity_name()),
                                                                    new Position(list.getPositionId()),
                                                                    list.getSalary())
                                ))
        );

        System.out.println(employeeByPositionResponseList);
        return employeeByPositionResponseList;

       /* result.stream().forEach( position ->
               employeeByPositionDtoList.add(employeeByPositionDto.setPositionName(position);) );*/

    }



}
