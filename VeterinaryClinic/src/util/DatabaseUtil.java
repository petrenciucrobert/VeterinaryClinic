package util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import model.Animal;
import model.Appointment;
import model.Doctor;

public class DatabaseUtil {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	// This is a setup method
	public void setup() {
		entityManagerFactory = Persistence.createEntityManagerFactory("VeterinaryClinic");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	// This is the start transaction method
	public void startTransaction() {
		entityManager.getTransaction().begin();
	}
	
	public void commitTransaction() {
		entityManager.getTransaction().commit();
	}
	
	public void stop() {
		entityManager.clear();
	}
	
	public void saveAnimal(Animal animal) {
		entityManager.persist(animal);
	}
	
	public void saveDoctor(Doctor doctor) {
		entityManager.persist(doctor);
	}
	
	public void saveAppointment(Appointment appointment) {
		entityManager.persist(appointment);
	}
	
	// Return all animals from database
	public List<Animal> getAllAnimals() {
		@SuppressWarnings("unchecked")
		List<Animal> animalList = (List<Animal>) entityManager.createNativeQuery("SELECT * FROM veterinaryclinic.animal", Animal.class).getResultList();
		return animalList;
	}
	
	// Return all doctors from database
	public List<Doctor> getAllDoctors() {
		@SuppressWarnings("unchecked")
		List<Doctor> doctorList = (List<Doctor>) entityManager.createNativeQuery("SELECT * FROM veterinaryclinic.doctor", Doctor.class).getResultList();
		return doctorList;
	}
	
	// Return all appointments from database
	public List<Appointment> getAllAppointments() {
		@SuppressWarnings("unchecked")
		List<Appointment> appointmentList = (List<Appointment>) entityManager.createNativeQuery("SELECT * FROM veterinaryclinic.appointment", Appointment.class).getResultList();
		return appointmentList;
	}
	
	public Appointment getAppointment() {
		@SuppressWarnings("unchecked")
		Appointment appointment = (Appointment) entityManager.createNativeQuery("SELECT * FROM veterinaryclinic.appointment where idAppointment = '12' " , Appointment.class).getSingleResult();
		return appointment;
	}
	
}
