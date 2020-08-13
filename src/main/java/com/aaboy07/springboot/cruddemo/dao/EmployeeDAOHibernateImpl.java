package com.aaboy07.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aaboy07.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	
	// define the field for entitymanger
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl (EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		// get the current hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		return employees; 
		
	}

	@Override
	public Employee findById(int theId) {
		
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// get the employee
		Employee theEmployee = currentSession.get(Employee.class, theId); 
		
		// return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// get the employee
		currentSession.saveOrUpdate(theEmployee); 
				
				
	}

	@Override
	public void deleteById(int theId) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Query<Employee> theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

	

}
