import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ChangeInformation {
	TextField yourFirstName = new TextField();
	TextField yourLastName = new TextField();
	TextField yourNickname = new TextField();
	TextField yourPassword = new TextField("Must enter your password to save your settings");
	TextField yourEmail = new TextField("Must enter your email to save your settings");
	TextField yourNewEmail = new TextField();
	TextField yourNewPassword = new TextField();

	Label firstName = new Label ("First Name: ");
	Label lastName = new Label("Last Name: ");
	Label nickname = new Label("Nickname: ");
	Label password = new Label("Old Password: ");
	Label newPassword = new Label("New Password: ");
	Label email = new Label("Email: ");
	Label changeEmail = new Label("Change Your Email: " );
	Label selectHobby = new Label("Select Your Hobby");

	String[] hobbies = {"Toy", "Voyaging" , "Stone Skipping", "Extreme Ironing", "Tree Shaping"
					, "News Raiding","Train Surfing"};

	ComboBox<String> hobbyBox = new ComboBox<>();
	ListView<String> hobbyList = new ListView<>();

    ObservableList<String> items =  FXCollections.observableArrayList(hobbies);
	Button changeButton = new Button("Change");
	String checkUser, checkPassword;

	public ChangeInformation() {

	}
	public VBox changePage() {
		GridPane gridPane = new GridPane();
		gridPane.add(firstName, 0, 0);
		gridPane.add(lastName, 0, 1);
		gridPane.add(email, 0, 2);
		gridPane.add(nickname, 0, 3);
		gridPane.add(password, 0, 4);
		gridPane.add(newPassword, 0, 5);
		gridPane.add(changeEmail, 0, 6);

		gridPane.add(yourFirstName, 1, 0);
		gridPane.add(yourLastName, 1, 1);
		gridPane.add(yourEmail, 1, 2);
		gridPane.add(yourNickname, 1, 3);
		gridPane.add(yourPassword, 1, 4);
		gridPane.add(yourNewPassword, 1, 5);
		gridPane.add(yourNewEmail, 1, 6);

		gridPane.add(selectHobby, 3, 0);
		gridPane.add(hobbyList, 3, 1);

		gridPane.setHgap(20);
		gridPane.setVgap(20);
		gridPane.setPadding(new Insets(50,50,50,50));



		hobbyList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		System.out.println("You selected: " + newVal);

				});


		hobbyList.getItems().addAll(hobbies);
		hobbyList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		hobbyList.setPrefWidth(150);
		hobbyList.setPrefHeight(700);


		changeButton.setOnAction(e -> {
			try {
				changeSettings();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(10,10,10,10));
		vb.setStyle("-fx-background-image: url('https://bloximages.chicago2.vip.townnews.com/missoulian.com/content/tncms/assets/v3/editorial/5/aa/5aa9642f-e19b-5cb8-91b8-e07165f99a3b/59a05d725869b.image.jpg?resize=1200%2C800')");
		vb.getChildren().addAll(gridPane, changeButton);

		return vb;

	}
	private void changeSettings() throws FileNotFoundException, IOException {
		Alert changeAlert = new Alert(Alert.AlertType.INFORMATION);
		String changeFirstName = yourFirstName.getText();
		String changeLastName = yourLastName.getText();
		String changeEmail = yourNewEmail.getText();
		String theNewPassword = yourNewPassword.getText();
		String changeNickname = yourNickname.getText();
		LinkedList<Member> temp = MasterData.getLinkedList();

			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getEmail().equals(yourEmail.getText()) && temp.get(i).getPassword().equals(yourPassword.getText())){

					if (changeFirstName != null) {
						temp.get(i).setName(changeFirstName);
					}
					else if (changeLastName != null) {
						temp.get(i).setLastName(changeLastName);
					}
					else if (isEmail() || changeEmail != null) {
						temp.get(i).setEmail(changeEmail);
					}
					//Only for new password
					else if (isValidPassword() &&  theNewPassword != null) {
						temp.get(i).setPassword(theNewPassword);

					}else if (yourNickname.getText() != null) {
						temp.get(i).setNickname(changeNickname);
					}

				}

					}

				MasterData.SaveMembers();

					changeAlert.setHeaderText("Change Successful");
					changeAlert.setContentText("All changes are made");
					System.out.println("change settings are made");

		};




	public boolean isEmail() {
		Alert emailAlert = new Alert(Alert.AlertType.INFORMATION);
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
			Pattern pattern = Pattern.compile(regex);
			Matcher emailMatch = pattern.matcher(yourEmail.getText());

			if (!emailMatch.find()) {
				emailAlert.setTitle("Invalid Email");
				emailAlert.setContentText("Invalid Email");
				emailAlert.showAndWait();
				return false;
			}
			//then change email to the account
			return true;
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

		 //then change the linked list for the user's password
		return true;
	}

}
