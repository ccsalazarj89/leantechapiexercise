package io.leanteach.pruebatecnica.app.models.dao;

import io.leanteach.pruebatecnica.app.dto.EmployeeByPositionDto;
import io.leanteach.pruebatecnica.app.dto.UpdateEmployeeDto;
import io.leanteach.pruebatecnica.app.models.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.sql.Update;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.geom.Arc2D;
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

        Query<Employee> theQuery = currentSession.createNativeQuery("delete from Employee where id=:id");
        theQuery.setParameter("id",id);
        theQuery.executeUpdate();
    }

    @Override
    @Transactional
    public void update(UpdateEmployeeDto updateEmployeeDto) {

        Session currentSession = em.unwrap(Session.class);
        Query<UpdateEmployeeDto> theQuery;

        Double salary = updateEmployeeDto.getSalary();
        Long positionId = updateEmployeeDto.getPositionId();
        Long id = updateEmployeeDto.getId();

        String sql = "update employee set salary = :salary and position_id=:positionId where id = :id";
        theQuery = currentSession.createNativeQuery(sql)
                .setParameter("salary",salary)
                .setParameter("positionId", positionId)
                .setParameter("id",id)
                .addScalar("salary", DoubleType.INSTANCE)
                .addScalar("position_id", LongType.INSTANCE);

        theQuery.executeUpdate();

    }

    @Override
    public List<EmployeeByPositionDto> findPosition() {

        Session currentSession = em.unwrap(Session.class);
        Query<EmployeeByPositionDto> theQuery;

        String sql = "\n" +
                "select a.id as positionId , a.name as positionName , b.id as employeeId,b.salary ,\n" +
                "c.name as personName , c.last_name as personLastName, c.id as personId,c.address,\n" +
                "c.city_name as cityName, c.cellphone as cellPhone\n" +
                "from position as a\n" +
                "inner join employee as b  on a.id = b.position_id\n" +
                "inner join candidate as c on c.id = b.person_id\n" +
                "order by b.salary desc;";

        theQuery = currentSession.createNativeQuery(sql)
                .addScalar("positionName", StringType.INSTANCE)
                .addScalar("employeeId", LongType.INSTANCE)
                .addScalar("salary", DoubleType.INSTANCE)
                .addScalar("personName", StringType.INSTANCE)
                .addScalar("personLastName", StringType.INSTANCE)
                .addScalar("address",StringType.INSTANCE)
                .addScalar("cityName", StringType.INSTANCE)
                .addScalar("positionId", LongType.INSTANCE)
                .addScalar("personId", LongType.INSTANCE)
                .addScalar("cellPhone",StringType.INSTANCE);

        return theQuery.setResultTransformer(Transformers.aliasToBean(EmployeeByPositionDto.class)).getResultList();
    }


}
