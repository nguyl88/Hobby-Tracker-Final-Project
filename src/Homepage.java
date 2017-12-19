import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// This is for after when the user login. It sees account setting

public class Homepage {

	private TextArea writeBlog = new TextArea();
	private TextField titleField = new TextField("Enter your blog's title");
	private Label title = new Label("Title");
	private Label tagLabel = new Label ("Tags: ");
	private Button submitButton = new Button("Submit Your Blog");
	private Button changeButton = new Button("Change Your Information");
	private Button deleteButton = new Button("Delete Your Account");
	private Button logoutButton = new Button("Log Out");
	private Button seeUsers = new Button("Search Users");
	private Stage newStage = new Stage();

	private ImageView gear = new ImageView("https://cdn.pixabay.com/photo/2014/09/30/15/15/gears-467261_960_720.png");


	private BorderPane bp = new BorderPane();
	private String[] hobbies = {"Toy", "Voyaging" , "Stone Skipping", "Extreme Ironing", "Tree Shaping"
			, "News Raiding","Train Surfing"};
	private CheckBox[] tags = new CheckBox[hobbies.length];

	public Homepage () {

	}

	public Stage userAccount() {
	VBox vb = new VBox();
	VBox vb2 = new VBox();
	HBox saveWrite = new HBox();
	VBox vButtons = new VBox();
	GridPane grid = new GridPane();
	
		for (int i = 0; i < hobbies.length; i++) {
			 final CheckBox tag = tags[i] = new CheckBox(hobbies[i]);
		}
		writeBlog.setWrapText(true);
		HBox titleBar = new HBox();
		titleBar.getChildren().addAll(title, titleField);
		titleBar.setSpacing(50);
		gear.setFitHeight(100);
		gear.setFitWidth(100);
		vb.getChildren().addAll(tags);
		vb2.getChildren().addAll(tagLabel,vb);
		saveWrite.getChildren().addAll(submitButton, vb2);
		vButtons.getChildren().addAll(gear, changeButton, deleteButton, seeUsers, logoutButton);
		grid.add(titleBar, 0,0);

		grid.add(writeBlog, 0, 1);
		grid.add(saveWrite, 0, 2);
		//grid.add(vb2, 2, 2);


		vButtons.setPadding(new Insets(20,20,20,20));
		vButtons.setSpacing(10);
		saveWrite.setPadding(new Insets(20,20,20,20));
		saveWrite.setSpacing(10);

		grid.setHgap(20);
		grid.setVgap(20);
		grid.setAlignment(Pos.CENTER);
		bp.setStyle("-fx-background-image: url('http://ghostfinder101.weebly.com/uploads/1/9/7/3/19737887/published/syntax-cover.png?1513360134')");
		
		
		EventHandler<ActionEvent> handler = e -> {
			for(int i = 0; i < hobbies.length; i++) {
				if(tags[i].isSelected()) {
				}
 			}
		};
		//Buttons
		
		DeleteAccountPage deletePage = new DeleteAccountPage();
		submitButton.setOnAction(e -> {
			try {
				saveBlog();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		changeButton.setOnAction(e -> change());
		deleteButton.setOnAction(e -> deletePage.deleteAccountStage()); //brings up the deleteAccount page

		seeUsers.setOnAction(e -> {
			try {
				searchUsers();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		logoutButton.setOnAction(e -> logout());

		bp.setCenter(grid);
		bp.setLeft(vButtons);
		
		Scene userLoginScene = new Scene(bp, 800,500);
		newStage.setTitle("Home");
		newStage.setScene(userLoginScene);
		newStage.show();
		return newStage;
	}
	
	public Stage getNewStage() {
		return newStage;
	}
	public void logout() {
		newStage.getOnCloseRequest();
	}

	public void searchUsers() throws FileNotFoundException, ClassNotFoundException, IOException {
		UserList view = new UserList();
		view.viewUsers();
	
	}
	public ArrayList<CheckBox> getTagValues() {
		ArrayList<CheckBox> checkList = new ArrayList<>();
		for(int i = 0; i < hobbies.length; i++) {
				checkList.add(tags[i]);
	
		}
		return checkList;
	}
	public void saveBlog() throws FileNotFoundException, IOException {
			//need to add tags to the writeBlog when saving it to the linked list
			System.out.println("Save blogs success");
			MasterData.addArticles(writeBlog.getText(), getTagValues());
			MasterData.saveArticles(); //saving user articles
			writeBlog.clear();
	}


	public void change() {

		Stage newStage = new Stage();
		ChangeInformation changeSettings = new ChangeInformation();
		Scene scene = new Scene(changeSettings.changePage(), 700,600);
		newStage.setScene(scene);
		newStage.setTitle("Settings");
		newStage.show();
	}


}
