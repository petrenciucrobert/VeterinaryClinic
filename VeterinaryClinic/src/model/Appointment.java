package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the appointment database table.
 * 
 */
@Entity
@NamedQuery(name="Appointment.findAll", query="SELECT a FROM Appointment a")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAppointment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String type;

	//bi-directional many-to-one association to Animal
	@ManyToOne
	@JoinColumn(name="idanimal")
	private Animal animal;

	//bi-directional many-to-one association to Doctor
	@ManyToOne
	@JoinColumn(name="iddoctor")
	private Doctor doctor;

	public Appointment() {
	}

	public int getIdAppointment() {
		return this.idAppointment;
	}

	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Animal getAnimal() {
		return this.animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public String getDoctorName() {
		Doctor numeDoctor = getDoctor();
		return numeDoctor.getName();
	}
	
	public String getAnimalName() {
		Animal numeAnimal = getAnimal();
		return numeAnimal.getName();
	}
	
	public int getAnimalAge() {
		Animal ageAnimal = getAnimal();
		return ageAnimal.getAge();
	}
	
	public int getAnimalWeight() {
		Animal weightAnimal = getAnimal();
		return weightAnimal.getWeight();
	}
	
	

}