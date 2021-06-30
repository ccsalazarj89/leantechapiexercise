package io.leanteach.pruebatecnica.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="candidate")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "person")
    private Employee employee;

    private String name;
    private String lastName;
    private String address;
    private String cellphone;
    private String cityName;

    public Person(Long id, Employee employee, String name, String lastName, String address, String cellphone, String cityName) {
        this.id = id;
        this.employee = employee;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.cellphone = cellphone;
        this.cityName = cityName;
    }

    public Person(Long id,  String name, String lastName, String address, String cellphone, String cityName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.cellphone = cellphone;
        this.cityName = cityName;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


}
