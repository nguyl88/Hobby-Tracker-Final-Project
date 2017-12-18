
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


public class UserList {
	private TableView<Member> table;
	private HBox hPane = new HBox();
	private CheckBoxTreeItem<String> santaRoot = new CheckBoxTreeItem<>("Articles");
	//TreeItem<String> userArticles = new TreeItem<>("User published Articles");
	private ChristmasTreeCell christmasCell = new ChristmasTreeCell();
	private Button sortUserButton = new Button("Sort By Likes");
	private Button sortUserHobbiesButton = new Button("Sort By Hobbies");

	public UserList() {

	}

	@SuppressWarnings("unchecked")
	public Stage viewUsers() throws FileNotFoundException, ClassNotFoundException, IOException {
		Stage newStage = new Stage();

		//Name column
		TableColumn<Member, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		//Nickname Column
		TableColumn<Member, String> nicknameColumn = new TableColumn<>("Nickname");
		nicknameColumn.setMinWidth(100);
		nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));

		//Hobbies Column
		TableColumn<Member, String> hobbiesColumn = new TableColumn<>("Hobby");
		hobbiesColumn.setMinWidth(200);
		hobbiesColumn.setCellValueFactory(new PropertyValueFactory<>("hobby"));

		//Checkbox column
		TableColumn<Member,Boolean> likedColumn = new TableColumn<Member,Boolean>("Liked");
		likedColumn.setCellValueFactory(new PropertyValueFactory<Member,Boolean>("isLiked"));

		 likedColumn.setCellValueFactory(new Callback<CellDataFeatures<Member, Boolean>, ObservableValue<Boolean>>() {
			 
	            @Override
	            public ObservableValue<Boolean> call(CellDataFeatures< Member, Boolean> param) {
	                Member m = param.getValue();
	 
	                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(m.isLiked());
	 
	                // When the user liked the user
	                booleanProp.addListener(new ChangeListener<Boolean>() {
	 
	                    @Override
	                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
	                            Boolean newValue) {
	                        m.setLiked(newValue);
	                        System.out.println("Value changed");
	                        try { //saving values to the objects
								MasterData.SaveMembers();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
	                        
	                    }
	                });
	                return booleanProp;
	            }
	        }); 
	 
		
		Callback<TableColumn<Member, Boolean>, TableCell<Member, Boolean>> booleanCellFactory =
	            new Callback<TableColumn<Member, Boolean>, TableCell<Member, Boolean>>() {
	            @Override
	                public TableCell<Member, Boolean> call(TableColumn<Member, Boolean> param) {
	                    return new LikedCell();
	            }
	        };
	     likedColumn.setCellFactory(booleanCellFactory);
		likedColumn.setMinWidth(50);

		//Article Column
		TableColumn<Member, MemberArticles> articleColumn = new TableColumn<>("Article");
		articleColumn.setMinWidth(50);
	//	articleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));

	/*	 articleColumn.setCellValueFactory(new Callback<CellDataFeatures<Member, MemberArticles>, ObservableValue<MemberArticles>>() {
		     public ObservableValue<MemberArticles> call(CellDataFeatures<Member,MemberArticles> p) {
	
		         return p.getValue().getListOfUserArticles();
		     }
		  });
		 
		 articleColumn.setCellFactory(ComboBoxTableCell.forTableColumn(getUserArticles()));*/
		 
	/*	Callback<TableColumn<Member, MemberArticles>, TableCell<Member, MemberArticles>> treeCellFactory =
				new Callback<TableColumn<Member, MemberArticles>, TableCell<Member, MemberArticles>>() {
			   @Override
               public TableCell<Member, MemberArticles> call(TableColumn<Member, MemberArticles> param) {
				   return;
	            }
	        };

	       articleColumn.setCellFactory(treeCellFactory);*/
/////////////////////////////////////////// End of Article Column///////////////////////////////////
	 
	       santaRoot.setExpanded(true);
	       santaRoot.setIndependent(true);
	   //    treeView.setCellFactory(e -> christmasCell);

		table = new TableView<>();
		table.setItems(getUserItems());
		table.getColumns().addAll(nameColumn, nicknameColumn, hobbiesColumn,likedColumn,articleColumn);
		
		VBox buttonPane = new VBox();
		buttonPane.getChildren().addAll(sortUserButton, sortUserHobbiesButton);
		hPane.getChildren().addAll(table, buttonPane);

		Scene scene = new Scene(hPane, 800,700);
		newStage.setScene(scene);
		newStage.setTitle("Hobby Tracker List");
		newStage.show();

		return newStage;

	}
	//Placeholder where we get the items from the list
	public ObservableList<Member> getUserItems() throws FileNotFoundException, ClassNotFoundException, IOException {
		
		ObservableList<Member> list = FXCollections.observableArrayList(MasterData.getLinkedList());
		list.add(new Member ("Placeholder", "Place", "News Raiding, does nothing",  true, null));

		return list;
	}

	//Opens a new window to display articles
	public Stage displayArticles() throws FileNotFoundException, IOException {
		Stage newStage = new Stage();
		TextArea articleDisplay = new TextArea();
		HBox p = new HBox();
		p.getChildren().add(articleDisplay);
		articleDisplay.setEditable(false);
		MasterData.loadArticles();//load all the articles from the linked list
		
		
	///	articleDisplay.appendText(); (get the text from the checkbox value)
		Scene scene = new Scene(p, 600,500);
		newStage.setTitle("UserArticles");
		newStage.setScene(scene);
		newStage.show();
		return newStage;
	}
	//get the user articles from the checkbox next to the user's name.
		public ObservableList<MemberArticles> getUserArticles() {
		ObservableList<MemberArticles> list = FXCollections.observableArrayList(MasterData.getArticleList());
		return list;
		
	}
}
