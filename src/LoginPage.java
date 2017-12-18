import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class LoginPage extends Application{

	String checkUser, checkPassword;
	Stage stage;
	TextField username = new TextField("");
	TextField password = new TextField("");
	MasterData data;


	@Override
	public void start(Stage primaryStage) throws Exception {
		MasterData.loadMembers(); //loading all the members
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,50,50,50));
		  //Adding HBox
		HBox hb = new HBox();
		hb.setPadding(new Insets(20,20,20,30));
		GridPane loginBox = new GridPane();
		Label lblusername = new Label("Your Email: ");
		Label lblpassword = new Label ("Password: ");

		Button loginButton = new Button("Login");
		Button createButton = new Button("Create an Account");
		Label createinfo = new Label("If you haven't created one");
		loginBox.add(lblusername, 0, 0);
		loginBox.add(username, 1, 0);
		loginBox.add(lblpassword, 0, 1);
		loginBox.add(password, 1, 1);
		loginBox.add(loginButton, 2, 2);
		loginBox.add(createButton, 2, 3);
		loginBox.add(createinfo, 1, 3);

		loginBox.setHgap(20);
		loginBox.setVgap(20);
		loginBox.setPadding(new Insets(50,50,50,50));


		 Text text = new Text("Hobby Tracker Login");
	     text.setFont(Font.font("Courier New", FontWeight.BOLD, 30));


	     hb.getChildren().add(text);
	     bp.setId("bp");
	       	loginBox.setId("root");
	        loginButton.setId("Login");
	        text.setId("text");

	     //button action
	    loginButton.setOnAction( e -> {
			try {
				login();
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	    createButton.setOnAction(e -> {
	    
			try {
				createAccount();	
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	     bp.setTop(hb);
	     bp.setCenter(loginBox);
	     bp.setStyle("-fx-background-image: url('http://ghostfinder101.weebly.com/uploads/1/9/7/3/19737887/winter-varen_orig.png')");

	    stage = primaryStage;
	    Scene loginPage = new Scene(bp, 600, 400);
		primaryStage.setTitle("Hobby Tracker");
		primaryStage.setScene(loginPage);
		primaryStage.show();

	}
	
	public Stage getMainStage() {
		return stage;
	}

	public void createAccount() throws FileNotFoundException, ClassNotFoundException, IOException {
		Stage newStage = new Stage();
		CreateNewAccount newAccount = new CreateNewAccount();
		Scene createAccountScene = new Scene(newAccount.createAccountPage(), 700, 400);
		newStage.setTitle("Create Account");
		newStage.setScene(createAccountScene);
		newStage.show();
		stage.close();

	}
	public void login() throws ClassNotFoundException, IOException {

		String userString = username.getText();
		String passString = password.getText();
		if (!MasterData.getUserLogin(userString, passString)) {
			System.out.println("Invalid login");
		} else {
		System.out.println("Login success");
		
		
	
		Homepage alreadyLogin = new Homepage();
		alreadyLogin.userAccount();

		stage.close();
		}
	 }

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		launch(args);
		//MasterData.loadData();
	}
}
