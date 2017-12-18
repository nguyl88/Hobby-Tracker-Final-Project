import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeleteAccountPage {
	private TextField deleteEmail = new TextField();
	private Label emailLabel = new Label("Your Email");
	private TextField passwordConfirm = new TextField();
	private Label passwordLabel = new Label("Your Password");
	private Button deleteButton = new Button("Delete");
	private Stage deleteStage;
	public DeleteAccountPage() {
	
	
	}
	public Stage deleteAccountStage() {
		deleteStage = new Stage();
		GridPane gp = new GridPane();
		gp.add(emailLabel,0, 0);
		gp.add(deleteEmail, 1, 0);
		gp.add(passwordLabel, 0,1);
		gp.add(passwordConfirm, 1, 1);
		gp.add(deleteButton, 0, 2);
		gp.setStyle("-fx-background-image: url('http://ghostfinder101.weebly.com/uploads/1/9/7/3/19737887/published/java.png?1511624853')");
		gp.setHgap(20);
		gp.setVgap(20);
		gp.setPadding(new Insets(50,50,50,50));
		deleteButton.setOnAction( e-> {
			try {
				deleteAccount(deleteEmail.getText(), passwordConfirm.getText());
			} catch (FileNotFoundException e1) {
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		Scene scene = new Scene(gp, 400, 200);
		deleteStage.setTitle("Delete Account");
		deleteStage.setScene(scene);
		deleteStage.show();
		return deleteStage;
	}
	public void deleteAccount(String email, String password) throws FileNotFoundException, ClassNotFoundException, IOException {
		LinkedList<Member> temp = MasterData.getLinkedList();
		for (int i = 0; i < temp.size(); i++) {
			//If the linked list found a member, remove it
			if(temp.get(i).getEmail().equals(email) && temp.get(i).getPassword().equals(password)) {
				temp.remove(i);
			}  
		}
		
		MasterData.SaveMembers();
		
		System.out.println("Account Deleted");
		
		deleteStage.close();
		
	}
	
}



