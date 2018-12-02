package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RestauntView extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox Container = new VBox();
		ArrayList<HBox> restHB = new ArrayList<HBox>();
		ArrayList<Restaraunt> rests = new ArrayList<Restaraunt>();
		restHB.add(new HBox());
		restHB.get(0).getChildren().add(new Text("All resturaunts"));
		restHB.get(0).setAlignment(Pos.CENTER);
		restHB.get(0).setSpacing(10);
		Container.getChildren().add(restHB.get(0));
		//Putting in resturaunts
		restHB.add(new HBox());
		rests.add(new Restaraunt("Papa Johns","$$",3,"Fast Food","Pizza"));
		
		restHB.add(new HBox());
		rests.add(new Restaraunt("Pizza hub","$",3,"Fast Food","Pizza"));
		
		restHB.add(new HBox());
		rests.add(new Restaraunt("GLasss Nickel","$$$$",5,"Fast Food","Pizza"));
		
		
		//
		String[] restStr = new String[5];
		int x=1;
		for(Restaraunt rest : rests){
			String[] info = rest.toStringArray();
			for(String infoStr : info){
				Text infoText = new Text(infoStr);
				restHB.get(x).getChildren().add(infoText);
				System.out.println("Did it:"+x+" | "+infoText);
				}
			restHB.get(x).setAlignment(Pos.CENTER);
			restHB.get(x).setSpacing(10);
			Container.getChildren().add(restHB.get(x));
			x++;
		}
		StackPane root = new StackPane();
		root.getChildren().add(Container);
		Container.setAlignment(Pos.CENTER);
		Container.setSpacing(10);
		primaryStage.setScene(new Scene(root, 600, 500));
		primaryStage.show();
	}
}

