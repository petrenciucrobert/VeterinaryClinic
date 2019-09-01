package controllers;

import java.net.URL;
import java.util.List;
//import java.util.Observable;
import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import util.DatabaseUtil;
import model.Animal;
import model.Appointment;
import model.Doctor;


public class MainController implements Initializable{
	
	
	
	@FXML private ListView listView;
	@FXML private TitledPane x1;
	@FXML private Label numeDoctorID;
	@FXML private Label dataID;
	@FXML private Label tipID;
	@FXML private Label numeAnimalID;
	@FXML private Label varstaAnimalID;
	@FXML private Label greutateAnimalID;
	
	
	
	public void populateMainListView() {
		DatabaseUtil db = new DatabaseUtil();
		db.setup();
		db.startTransaction();
		
		List<Animal> animalDBList = (List <Animal>) db.getAllAnimals();
		List<Doctor> doctorDBList = (List <Doctor>) db.getAllDoctors();
		List<Appointment> appointmentDBList = (List <Appointment>) db.getAllAppointments();
		
		ObservableList<String> animalNameList = getAnimalName(animalDBList);
		ObservableList<String> doctorNameList = getDoctorName(doctorDBList);
		ObservableList<String> appointmentNameList = getAppointmentType(appointmentDBList);
		ObservableList<Appointment> programari;
		
		List<Appointment> appointment =  db.getAllAppointments();
		programari = FXCollections.observableArrayList(appointment);
		listView.setItems(programari);
		
		listView.setCellFactory(param -> new ListCell<Appointment>() {
			@Override
			protected void updateItem(Appointment item, boolean empty) {
				super.updateItem(item, empty);
				
				if(empty || item == null || item.getType() == null) {
					setText(null);
				} else {
					setText(item.getType());
				}
			}
		});
		
		
		//x1.setText("Yes");
		listView.refresh();
		db.stop();
		
		db.setup();
		Appointment persoana = new Appointment();
		persoana = db.getAppointment();
		System.out.println("Aici: " + persoana.getDoctorName());
		db.stop();
	}
	
	
	public ObservableList<String> getAnimalName(List<Animal> animals) {
		ObservableList<String> names = FXCollections.observableArrayList();
		for(Animal a: animals) {
			names.add(a.getName());
		}
		return names;
	}
	
	
	public ObservableList<String> getDoctorName(List<Doctor> doctors) {
		ObservableList<String> doctorNames = FXCollections.observableArrayList();
		for (Doctor d: doctors)
		{
			doctorNames.add(d.getName());
		}
		return doctorNames;
	}
	
	public ObservableList<String> getAppointmentType(List<Appointment> appointments) {
		ObservableList<String> appointmentDates = FXCollections.observableArrayList();
		for (Appointment ap: appointments)
		{
			appointmentDates.add(ap.getType());
		}
		return appointmentDates;
	}
	
	public void onClickAction(MouseEvent event) {
		Appointment click = (Appointment) listView.getSelectionModel().getSelectedItem();
		numeDoctorID.setText("Nume doctor: " + click.getDoctorName());
		dataID.setText("Data: " + click.getDate());
		tipID.setText("Tipul programarii: " + click.getType());
		
		numeAnimalID.setText("Nume animal: " + click.getAnimalName());
		varstaAnimalID.setText("Varsta animal: " + click.getAnimalAge());
		greutateAnimalID.setText("Greutate animal: " + click.getAnimalWeight());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateMainListView();
	}
	
	

}
