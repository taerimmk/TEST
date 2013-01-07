package com.june.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.june.app.domain.Person;

@Service("personService")
public class PersonService implements IPersonService {	
	@Resource
	private SessionFactory sessionFactory;
		
	@SuppressWarnings("unchecked")
	public List<Person> getAll() {
		Session session = sessionFactory.openSession();
		List<Person> persons = (List<Person>) session.createCriteria(Person.class).list();
		return persons;
	}
	
	public Person get(Integer id) {
		Session session = sessionFactory.openSession();
		return (Person) session.get(Person.class, id);
	}
	
	public Person add(String firstName, String lastName, Double money) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setMoney(money);
		
		Session session = sessionFactory.openSession();
		session.save(person);
		
		return person;
	}
	
	public Person edit(Integer id, String firstName, String lastName, Double money) {
		Person person = get(id);
		Session session = sessionFactory.openSession();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setMoney(money);		
		session.update(person);
		session.flush();
		
		return person;
	}
	
	public void delete(Integer id) {
		Person person = get(id);
		Session session = sessionFactory.openSession();
		session.delete(person);
		session.flush();
	}
}