package io.leanteach.pruebatecnica.app.models.dao;

import io.leanteach.pruebatecnica.app.dto.EmployeeByPositionDto;
import io.leanteach.pruebatecnica.app.models.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Long save(Employee employee) {

        Session currentSession = em.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);

        return employee.getId();

    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Employee> findAll(String name, String position) {

        Session currentSession = em.unwrap(Session.class);
        Query<Employee> theQuery;

        if (name.isEmpty() && position.isEmpty()) {

            theQuery = currentSession.createQuery("from Employee", Employee.class);
        }else
        {

            String sql = "select a.* from Employee as a inner join Candidate as b on  a.person_id = b.id \n" +
                    "inner join Position as c on a.position_id = c.id\n" +
                    "where b.name=:name or c.name = :position";

            theQuery = currentSession.createNativeQuery(sql,Employee.class);
            theQuery.setParameter("name",name);
            theQuery.setParameter("position",position);
        }

        return theQuery.getResultList();

    }

    @Override
    @Transactional
    public void delete(Long id) {

        Session currentSession = em.unwrap(Session.class);

        Query<Employee> theQuery = currentSession.createQuery("delete from Employee where id:id",Employee.class);
        theQuery.setParameter("id",id);
        theQuery.executeUpdate();
    }

    @Override
    public List<EmployeeByPositionDto> findPosition() {

        Session currentSession = em.unwrap(Session.class);
        Query<EmployeeByPositionDto> theQuery;

        String sql = "\n" +
                "select a.name , b.id id_employee , b.salary , c.name as person_name , c.last_name, c.address, c.city_name\n" +
                " from position as a\n" +
                "inner join employee as b  on a.id = b.position_id\n" +
                "inner join candidate as c on c.id = b.person_id\n" +
                "order by b.salary desc;";

        theQuery = currentSession.createNativeQuery(sql);

        System.out.println(theQuery);

        return theQuery.setResultTransformer(Transformers.aliasToBean(EmployeeByPositionDto.class)).getResultList();
    }


}
