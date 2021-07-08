package io.leanteach.pruebatecnica.app.controllers;


import io.leanteach.pruebatecnica.app.dto.EmployeeByPositionDto;
import io.leanteach.pruebatecnica.app.dto.UpdateEmployeeDto;
import io.leanteach.pruebatecnica.app.models.EmployeeByPositionResponse;
import io.leanteach.pruebatecnica.app.models.entity.Employee;
import io.leanteach.pruebatecnica.app.models.entity.Person;
import io.leanteach.pruebatecnica.app.models.entity.Position;
import io.leanteach.pruebatecnica.app.models.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping(value = "/listEmployee")
    public List<Employee> listEmployee(@RequestParam(defaultValue = "") String name , @RequestParam(defaultValue = "") String position){

        return employeeService.findAll(name,position);

    }

    @GetMapping(value = "/listPosition")
    public List<EmployeeByPositionResponse> listPosition(){

        EmployeeByPositionDto employeeByPositionDto = new EmployeeByPositionDto();
        List<EmployeeByPositionResponse> employeeByPositionResponseList= new ArrayList<EmployeeByPositionResponse>();

        List<EmployeeByPositionDto> result =  employeeService.findPosition();

        result.forEach(
                list -> employeeByPositionResponseList
                        .add(new EmployeeByPositionResponse(list.getPositionId(),
                                                            list.getPositionName(),
                                                            new Employee(list.getEmployeeId(),
                                                                         new Person(list.getPersonId(),
                                                                                    list.getPersonName(),
                                                                                    list.getPersonLastName(),
                                                                                    list.getAddress(),
                                                                                    list.getCellPhone(),
                                                                                    list.getCityName()),
                                                                    new Position(list.getPositionId()),
                                                                    list.getSalary())
                                ))
        );

        return employeeByPositionResponseList;

    }
    @GetMapping("/deleteEmployee/{id}")
    public ResponseEntity deleteEmployee (@PathVariable(value = "id") Long id){

        try{
            if (id> 0){
                employeeService.delete(id);
                return new ResponseEntity("Delete successful", HttpStatus.OK);
            }else{
                return new ResponseEntity("Id no valid",HttpStatus.OK);
            }
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping(value = "/saveEmployee",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody Employee employee){
        try{
            Employee employeetoSave  = new Employee();
            employeetoSave.setSalary(employee.getSalary());
            employeetoSave.setPerson(employee.getPerson());
            employeetoSave.setPosition(employee.getPosition());
            employeeService.save(employeetoSave);
            return new ResponseEntity("ID employee "+ employeetoSave.getId().toString(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/updateEmployee",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEmployee(@RequestBody UpdateEmployeeDto updateEmployeeDto){
        try{
            employeeService.update(updateEmployeeDto);
            return new ResponseEntity("Employee "+ updateEmployeeDto.getId() + "updated successfully",HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
