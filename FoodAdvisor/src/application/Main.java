package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application implements Initializable {
	public static User user = null;
	@FXML
	private MenuBar searchMenuBar;
	@FXML
	private VBox DominoStrings;
	@FXML
	private Button reviewSubmit;
	@FXML
	private TextArea reviewText;
	private int cheesePizza, pepperoniPizza, sausagePizza, cheeseburgerPizza, theworksPizza, bbqmeatsPizza;
	private int s1topping, s2topping, s3topping, s4topping, m1topping, m2topping, m3topping, m4topping, l1topping,
			l2topping, l3topping, l4topping;
	private double grandTotal;
	private boolean loggedIn = false;

	@Override
	public void start(Stage stage) {
		try {
			VBox root = new VBox();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("HomePageView.fxml"));
			root = (VBox) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	// TODO: Will be called on mouseclick event on the Dominos button in the
	// SearchResultsView.
	@FXML
	public void reviewSubmit(ActionEvent event) throws ClassNotFoundException, IOException {
		if (Main.user != null) {
			addReview(reviewText.getText());
			addReview(Main.user.getUsername());
			final Stage stage = (Stage) searchMenuBar.getScene().getWindow();
			try {
				VBox root = new VBox();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("DominosView.fxml"));
				root = (VBox) loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) searchMenuBar.getScene().getWindow();;
				stage.setScene(scene);
				stage.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			//Ishan put that login code
			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
		}
	}

	@FXML
	public void handleEventDominos(ActionEvent event) throws ClassNotFoundException, IOException {
		final Stage stage = (Stage) searchMenuBar.getScene().getWindow();
		VBox root = new VBox();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("DominosView.fxml"));
		root = (VBox) loader.load();
		Scene scene = new Scene(root);
		VBox reviewsVox = (VBox) root.getChildren().get(6);
		DominoStrings = (VBox) reviewsVox.getChildren().get(2);
		stage.setScene(scene);
		addReview(("This food sucks"));
		ArrayList<String> dominosStrings = getDominosReivews();
		for (String String : dominosStrings) {
			System.out.println("A: " + String);
			if (DominoStrings == null) {
				System.out.println("Null");
			}
			DominoStrings.getChildren().add(new Text(String));
			stage.show();
		}
	}

	public static ArrayList<String> getDominosReivews() throws IOException, ClassNotFoundException {
		File usersFile = new File("src/dominosString.txt");
		ArrayList<String> users = new ArrayList<String>();
		if (!usersFile.exists()) {
			System.out.println("New file");
			usersFile.createNewFile();
		}
		if (usersFile.length() != 0) {
			System.out.println("GO");
			FileInputStream usersFileInputStream = new FileInputStream(usersFile);
			ObjectInputStream usersObjectInputStream = new ObjectInputStream(usersFileInputStream);
			@SuppressWarnings("unchecked")
			final ArrayList<String> currentUsers = (ArrayList<String>) usersObjectInputStream.readObject();
			for (String String : currentUsers) {
				users.add(String);
			}
			usersObjectInputStream.close();
			usersFileInputStream.close();
		} else {
			System.out.println("Empty");
		}
		return users;
	}

	private static void addReview(String String) throws ClassNotFoundException, IOException {
		ArrayList<String> users = Main.getDominosReivews();
		users.add(String);
		File usersFile = new File("src/dominosString.txt");
		FileOutputStream usersFileOutputStream = new FileOutputStream(usersFile);
		ObjectOutputStream usersObjectOutputStream = new ObjectOutputStream(usersFileOutputStream);
		usersObjectOutputStream.writeObject(users);
		System.out.println(users);
	}

	// TODO: Will be called on mouseclick event on the PapaJohns button in the
	// SearchResultsView.
	@FXML
	public void handleEventPapaJohns() {
		final Stage stage = (Stage) searchMenuBar.getScene().getWindow();
		try {
			VBox root = new VBox();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("PapaJohnsView.fxml"));
			root = (VBox) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleEventLogin() {
		final Stage stage = (Stage) searchMenuBar.getScene().getWindow();
		try {
			AnchorPane root = new AnchorPane();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Login.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handlePizzaSelection(ActionEvent event) {
		Button button = (Button) event.getSource();
		DropShadow drop_shadow1 = new DropShadow(BlurType.values()[0], Color.STEELBLUE, 5, 4.0f, 3.0f, 3.0f);
		drop_shadow1.setOffsetY(0);
		drop_shadow1.setOffsetX(0);
		button.setEffect(drop_shadow1);

		if (button.getId().compareTo("sausage") == 0) {
			sausagePizza++;
		} else if (button.getId().compareTo("cheese") == 0) {
			cheesePizza++;
		} else if (button.getId().compareTo("bbqmeats") == 0) {
			bbqmeatsPizza++;
		} else if (button.getId().compareTo("pepperoni") == 0) {
			pepperoniPizza++;
		} else if (button.getId().compareTo("theworks") == 0) {
			theworksPizza++;
		} else if (button.getId().compareTo("cheeseburger") == 0) {
			cheeseburgerPizza++;
		} else if (button.getId().compareTo("largefourtopping") == 0) {
			l4topping++;
		} else if (button.getId().compareTo("largethreetopping") == 0) {
			l3topping++;
		} else if (button.getId().compareTo("largetwotopping") == 0) {
			l2topping++;
		} else if (button.getId().compareTo("largeonetopping") == 0) {
			l1topping++;
		} else if (button.getId().compareTo("mediumfourtopping") == 0) {
			m4topping++;
		} else if (button.getId().compareTo("mediumthreetopping") == 0) {
			m3topping++;
		} else if (button.getId().compareTo("mediumtwotopping") == 0) {
			m2topping++;
		} else if (button.getId().compareTo("mediumonetopping") == 0) {
			m1topping++;
		} else if (button.getId().compareTo("smallfourtopping") == 0) {
			s4topping++;
		} else if (button.getId().compareTo("smallthreetopping") == 0) {
			s3topping++;
		} else if (button.getId().compareTo("smalltwotopping") == 0) {
			s2topping++;
		} else if (button.getId().compareTo("smallonetopping") == 0) {
			s1topping++;
		}

	}

	@FXML
	public void handleOrder(ActionEvent event) {
		ButtonType pay = new ButtonType("PAY", ButtonData.OK_DONE);
		String s = "Items \t\t\t Cost\n";
		if (sausagePizza > 0) {
			s = s + handleSausagePizzaOrder() + "\n";
		}
		if (pepperoniPizza > 0) {
			s = s + handlePepperoniPizzaOrder() + "\n";
		}
		if (cheesePizza > 0) {
			s = s + handleCheesePizzaOrder() + "\n";
		}
		if (theworksPizza > 0) {
			s = s + handleTheWorksPizzaOrder() + "\n";
		}
		if (bbqmeatsPizza > 0) {
			s = s + handleBBQMeatsPizzaOrder() + "\n";
		}
		if (cheeseburgerPizza > 0) {
			s = s + handleCheeseBurgerPizzaOrder() + "\n";
		}
		if (l4topping > 0) {
			s = s + handleLarge4ToppingPizzaOrder() + "\n";
		}
		if (l3topping > 0) {
			s = s + handleLarge3ToppingPizzaOrder() + "\n";
		}
		if (l2topping > 0) {
			s = s + handleLarge2ToppingPizzaOrder() + "\n";
		}
		if (l1topping > 0) {
			s = s + handleLarge1ToppingPizzaOrder() + "\n";
		}
		if (m4topping > 0) {
			s = s + handleMedium4ToppingPizzaOrder() + "\n";
		}
		if (m3topping > 0) {
			s = s + handleMedium3ToppingPizzaOrder() + "\n";
		}
		if (m2topping > 0) {
			s = s + handleMedium2ToppingPizzaOrder() + "\n";
		}
		if (m1topping > 0) {
			s = s + handleMedium1ToppingPizzaOrder() + "\n";
		}
		if (s4topping > 0) {
			s = s + handleSmall4ToppingPizzaOrder() + "\n";
		}
		if (s3topping > 0) {
			s = s + handleSmall3ToppingPizzaOrder() + "\n";
		}
		if (s2topping > 0) {
			s = s + handleSmall2ToppingPizzaOrder() + "\n";
		}
		if (s1topping > 0) {
			s = s + handleSmall1ToppingPizzaOrder() + "\n";
		}

		s = s + "Total \t\t\t " + grandTotal;
		Alert alert = new Alert(AlertType.NONE, s, pay);
		alert.setTitle("Order Details");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent()) {
			if (loggedIn) {
				Alert orderStatus = new Alert(AlertType.INFORMATION, " Order Successful", ButtonType.CLOSE);
				orderStatus.show();
			} else {
				Alert orderStatus = new Alert(AlertType.ERROR, "Please login to place an order", ButtonType.CLOSE);
				orderStatus.show();
			}

		}

	}

	private String handleSausagePizzaOrder() {
		double total = sausagePizza * 10.24;
		grandTotal += total;
		return "Sauage Pizza \t\t " + total;
	}

	private String handlePepperoniPizzaOrder() {
		double total = pepperoniPizza * 15.99;
		grandTotal += total;
		return "Pepperoni Pizza \t " + total;
	}

	private String handleTheWorksPizzaOrder() {
		double total = theworksPizza * 15.99;
		grandTotal += total;
		return "TheWorks Pizza \t " + total;
	}

	private String handleBBQMeatsPizzaOrder() {
		double total = bbqmeatsPizza * 15.99;
		grandTotal += total;
		return "BBQMeats Pizza \t " + total;
	}

	private String handleCheesePizzaOrder() {
		double total = cheesePizza * 15.99;
		grandTotal += total;
		return "Cheese Pizza \t " + total;
	}

	private String handleCheeseBurgerPizzaOrder() {
		double total = cheeseburgerPizza * 15.99;
		grandTotal += total;
		return "Cheese Pizza \t " + total;
	}

	private String handleLarge4ToppingPizzaOrder() {
		double total = l4topping * 5.60;
		grandTotal += total;
		return "Large 4 Topping Pizza \t " + total;
	}

	private String handleLarge3ToppingPizzaOrder() {
		double total = l3topping * 5.50;
		grandTotal += total;
		return "Large 3 Topping Pizza \t " + total;
	}

	private String handleLarge2ToppingPizzaOrder() {
		double total = l2topping * 5.30;
		grandTotal += total;
		return "Large 2 Topping Pizza \t " + total;
	}

	private String handleLarge1ToppingPizzaOrder() {
		double total = l1topping * 5.00;
		grandTotal += total;
		return "Large 2 Topping Pizza \t " + total;
	}

	private String handleMedium4ToppingPizzaOrder() {
		double total = m4topping * 4.60;
		grandTotal += total;
		return "Medium 4 Topping Pizza \t " + total;
	}

	private String handleMedium3ToppingPizzaOrder() {
		double total = m3topping * 4.50;
		grandTotal += total;
		return "Medium 3 Topping Pizza \t " + total;
	}

	private String handleMedium2ToppingPizzaOrder() {
		double total = m2topping * 4.30;
		grandTotal += total;
		return "Medium 2 Topping Pizza \t " + total;
	}

	private String handleMedium1ToppingPizzaOrder() {
		double total = m1topping * 4.00;
		grandTotal += total;
		return "Medium 1 Topping Pizza \t " + total;
	}

	private String handleSmall4ToppingPizzaOrder() {
		double total = s4topping * 3.60;
		grandTotal += total;
		return "Small 4 Topping Pizza \t " + total;
	}

	private String handleSmall3ToppingPizzaOrder() {
		double total = s3topping * 3.50;
		grandTotal += total;
		return "Small 3 Topping Pizza \t " + total;
	}

	private String handleSmall2ToppingPizzaOrder() {
		double total = s2topping * 3.30;
		grandTotal += total;
		return "Small 2 Topping Pizza \t " + total;
	}

	private String handleSmall1ToppingPizzaOrder() {
		double total = s1topping * 3.00;
		grandTotal += total;
		return "Small 1 Topping Pizza \t " + total;
	}

}
