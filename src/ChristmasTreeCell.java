
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;

public class ChristmasTreeCell  extends TreeCell<String> {
		String s = "HELLO WORLD"; //placeholder string
		
		CheckBox cb = new CheckBox();
		TextArea ta;
				
		//It will display the articles to the text area when the checkbox is clicked

		public void displayArticles(TextArea ta) {
				cb.selectedProperty().addListener(new ChangeListener<Boolean>() {

			        public void changed(ObservableValue<? extends Boolean> ov,  Boolean old_val, Boolean new_val) {
			        	if(new_val.booleanValue() == true)    	
			        		ta.setText(s);
			              System.out.println("Articles displayed");
			        }			
				});
			
		}
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            
            // If the cell is empty we don't show anything.
            if (isEmpty()) {
                setGraphic(null);
                setText(null);
            } else {
                if (this.getTreeItem().isLeaf()) {

                    HBox cellBox = new HBox(10);

                    CheckBox checkBox = new CheckBox();
                    Label label = new Label(item);
             
                    // Here we bind the pref height of the label to the height of the checkbox. This way the label and the checkbox will have the same size. 
                    label.prefHeightProperty().bind(checkBox.heightProperty());

                    cellBox.getChildren().addAll(checkBox, label);

                    // set the cellBox as the graphic of the cell.
                    setGraphic(cellBox);
                    setText(null);
                } else {
                    // If this is the root we just display the text.
                    setGraphic(null);
                    setText(item);
                }
            }
        }
    
}
