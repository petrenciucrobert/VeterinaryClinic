import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Animal;
import model.Appointment;
import model.Doctor;
import util.DatabaseUtil;

public class Main extends Application{

	public static void main(String[] args) {
		//Initialise an animal
		Animal bob = new Animal();
		bob.setName("Doggo");
		bob.setAge(5);
		bob.setWeight(4);
		bob.setIdAnimal(2);
		
		//Initialise a doctor
		Doctor doc1 = new Doctor();
		doc1.setIdDoctor(10);
		doc1.setName("John Doe");
		
		// Initialise an appoiment
		List<Appointment> bobsAppointment = new ArrayList<>();
		Appointment a = new Appointment();
		a.setIdAppointment(26);
		a.setType("Appointment");
		a.setAnimal(bob);
		a.setDoctor(doc1);
		bobsAppointment.add(a);
		bob.setAppointments(bobsAppointment);
		doc1.setAppointments(bobsAppointment); */
		// This is the database util
		DatabaseUtil dbUtil = new DatabaseUtil();
		dbUtil.setup();
		dbUtil.startTransaction();
			dbUtil.saveAnimal(bob);
		dbUtil.saveDoctor(doc1);
		dbUtil.saveAppointment(a);
		dbUtil.commitTransaction();
		
		for(Animal animal : dbUtil.getAllAnimals()) {
		System.out.println("Animal name: " + animal.getName());
		String appName = animal.getAppointments().get(0).getType();
		System.out.println("This is the type of the appointment: " + appName);
		}
		
		dbUtil.stop();
		
		launch(args);
		
		
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("controllers/MainView.fxml"));
			Scene scene = new Scene(root, 800, 800);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
}
