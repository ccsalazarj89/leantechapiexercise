package io.leanteach.pruebatecnica.app.models.dao;

import io.leanteach.pruebatecnica.app.models.entity.Person;
import java.util.List;

public interface IPersonDao {

    public void save(Person person);
    public List<Person> findAll();

}
