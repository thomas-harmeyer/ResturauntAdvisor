package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Button loginButton;
	@FXML
	private Button newButton;
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField passwordTextField;

	public void loginButtonClick() throws ClassNotFoundException, IOException {

		if (LoginController.check(usernameTextField.getText(), passwordTextField.getText(),
				LoginController.getUsers())) {
			System.out.println("123...");
			goHome(loginButton);
		} else {
			System.out.println("FAILED");
		}
	}

	public void newButtonClick() throws ClassNotFoundException, IOException {
			LoginController.addUser(new User(usernameTextField.getText(), passwordTextField.getText()));
	}
	@FXML
	public void goHome(Button button) throws IOException {
		System.out.println("TRYING");
		Stage firstStage = (Stage) button.getScene().getWindow();
		firstStage.close();
		String fxmlResource = "Test.fxml";
		Parent panel;
		panel = FXMLLoader.load(getClass().getResource(fxmlResource));
		Scene scene = new Scene(panel);
		Stage stage = new Stage();
		stage.setTitle("RestaurantAdvisor");
		stage.setScene(scene);
		stage.show();
		System.out.println("TRIED MY BEST");
	}

	public static ArrayList<User> getUsers() throws IOException, ClassNotFoundException {
		File usersFile = new File("src/usersFile.txt");
		ArrayList<User> users = new ArrayList<User>();
		if (!usersFile.exists()) {
			System.out.println("New file");
			usersFile.createNewFile();
		}
		if (usersFile.length() != 0) {
			System.out.println("GO");
			FileInputStream usersFileInputStream = new FileInputStream(usersFile);
			ObjectInputStream usersObjectInputStream = new ObjectInputStream(usersFileInputStream);
			@SuppressWarnings("unchecked")
			final ArrayList<User> currentUsers = (ArrayList<User>) usersObjectInputStream.readObject();
			for (User user : currentUsers) {
				users.add(user);
			}
			usersObjectInputStream.close();
			usersFileInputStream.close();
		} else {
			System.out.println("Empty");
		}
		return users;
	}

	private static void addUser(User user) throws ClassNotFoundException, IOException {
		ArrayList<User> users = LoginController.getUsers();
		users.add(user);
		File usersFile = new File("src/usersFile.txt");
		FileOutputStream usersFileOutputStream = new FileOutputStream(usersFile);
		ObjectOutputStream usersObjectOutputStream = new ObjectOutputStream(usersFileOutputStream);
		usersObjectOutputStream.writeObject(users);
		System.out.println(users);
	}

	private static boolean check(String usern, String pass, ArrayList<User> users) {
		for (User user : users) {
			if (user.getUsername().equals(usern) && user.getPassword().equals(pass)) {
				return true;
			}
		}
		return false;
	}
}