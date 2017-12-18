
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CreateNewAccount {

		Stage currentStage;
		private TextField yourFirstName = new TextField();
		private TextField yourLastName = new TextField();
		private TextField yourNickname = new TextField();
		private TextField yourPassword = new TextField();
		private TextField yourEmail = new TextField();

		private Label firstName = new Label ("First Name: ");
		private Label lastName = new Label("Last Name: ");
		private Label nickname = new Label("Nickname: ");
		private Label password = new Label("Password: ");
		private Label email = new Label("Email: ");
		private Label selectHobby = new Label("Select Your Hobby");
		private String[] hobbies = {"Toy", "Voyaging" , "Stone Skipping", "Extreme Ironing", "Tree Shaping"
						, "News Raiding","Train Surfing"};
		private boolean defaultLiked = false;

		ComboBox<String> hobbyBox;
		ListView<String> hobbyList;

		ObservableList<String> items =  FXCollections.observableArrayList(hobbies);
		Button createButton = new Button("Create");
		String checkUser, checkPassword;



		public CreateNewAccount() throws FileNotFoundException, ClassNotFoundException, IOException {
			currentStage = new Stage();
			hobbyBox = new ComboBox<>();
			hobbyList = new ListView<>();
		}
		public VBox createAccountPage() {

			GridPane gridPane = new GridPane();
			gridPane.add(firstName, 0, 0);
			gridPane.add(lastName, 0, 1);
			gridPane.add(email, 0, 2);
			gridPane.add(nickname, 0, 3);
			gridPane.add(password, 0, 4);

			gridPane.add(yourFirstName, 1, 0);
			gridPane.add(yourLastName, 1, 1);
			gridPane.add(yourEmail, 1, 2);
			gridPane.add(yourNickname, 1, 3);
			gridPane.add(yourPassword, 1, 4);

			gridPane.add(selectHobby, 3, 0);
			gridPane.add(hobbyList, 3, 1);

			gridPane.setHgap(20);
			gridPane.setVgap(20);
			gridPane.setPadding(new Insets(50,50,50,50));

			hobbyList.getItems().addAll(hobbies);
			hobbyList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			hobbyList.setPrefWidth(150);
			hobbyList.setPrefHeight(700);



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
			VBox vb = new VBox();
			vb.setAlignment(Pos.CENTER);
			vb.setPadding(new Insets(10,10,10,10));
			vb.setStyle("-fx-background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8mQFXSsRmfNjk8qWJ_PhM7N-QGbOXMK4xQDB4Y1Bi6s0epzK20w')");
			vb.getChildren().addAll(gridPane, createButton);

			return vb;


		}
		public boolean isEmail() throws FileNotFoundException, IOException, ClassNotFoundException {
		Alert emailAlert = new Alert(Alert.AlertType.INFORMATION);
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

			Pattern pattern = Pattern.compile(regex);
			Matcher emailMatch = pattern.matcher(yourEmail.getText());

			if (MasterData.emailExists(yourEmail.getText()) && emailMatch.find()) {
				emailAlert.setTitle("Invalid Email");
				emailAlert.setContentText("Invalid Email");
				emailAlert.showAndWait();
				return false;
			} else {
			//then add email to the account
			return true;
			}
		}
		public boolean isValidPassword() {
			Alert passwordAlert = new Alert(Alert.AlertType.INFORMATION);
			Pattern pattern = Pattern.compile("[0-9]");
			Pattern pattern2 = Pattern.compile("[A-Z]");

			Matcher numberMatch = pattern.matcher(yourPassword.getText());
			Matcher upperMatch = pattern2.matcher(yourPassword.getText());

			 if (yourPassword.getText().length() < 8) {
				passwordAlert.setTitle("Must have at least 8 characters long");
				passwordAlert.setContentText("Password must be 8 characters long");
				passwordAlert.showAndWait();
				return false;
			}
			else if (!numberMatch.find()){
	        	passwordAlert.setTitle("Digit");
				passwordAlert.setContentText("Password must have at least have a number");
				passwordAlert.showAndWait();
	        	return false;
	        }
			else if (!upperMatch.find()) {
	        		passwordAlert.setTitle("Uppercase");
					passwordAlert.setContentText("Password must have at least have an uppercase");
					passwordAlert.showAndWait();
	             return false;
			}
			return true;
		}
		public void createAccount() throws FileNotFoundException, ClassNotFoundException, IOException{
			Member newUser;
			//Member(String name, String lastName, String nickname, String hobby, String email, String password

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			if (hobbyList.getSelectionModel().selectedItemProperty().getValue()== null){
				alert.setTitle("No Items Selected");
				alert.setContentText("No items were selected from hobby");
				alert.showAndWait();
			} else if (!isValidPassword()) {
				System.out.println("Invalid password");
			} else if (!isEmail()) {
				System.out.println("Invalid email");
			}
			else {
				hobbyList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
				System.out.println("You selected: " + newVal);
				});
				//Creates a new user
				newUser = new Member(yourFirstName.getText(), yourLastName.getText(), yourNickname.getText(),
						hobbyList.getSelectionModel().getSelectedItem(), yourEmail.getText(), yourPassword.getText(), defaultLiked);

				
				MasterData.add(newUser); //adding user to the linked list
				MasterData.SaveMembers(); //save the object into the file
				
				//Showing stage
				Homepage homepage = new Homepage();
				homepage.userAccount();
	

			}
		


		}



}
