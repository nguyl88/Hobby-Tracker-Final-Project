import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;

public class LikedCell extends TableCell<Member, Boolean> {
    private CheckBox likedBox;
    private ObservableValue<Boolean> ov;
    private Member m;
    private boolean b;

    public LikedCell() {
        likedBox = new CheckBox();
        likedBox.setDisable(false);
        likedBox.selectedProperty().addListener(new ChangeListener<Boolean> () {
        
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(isSelected())
                    commitEdit(newValue == null ? true : newValue);
                	try {
						MasterData.addLike(m, newValue);
						MasterData.saveLikes(); //saving the likes value from what the user likes.
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
                System.out.println("isLiked or unlike");
            }
        });
        this.setGraphic(likedBox);
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.setEditable(true);
    }
    @Override
    public void startEdit() {
        super.startEdit();
        if (isEmpty()) {
            return;
        }
        likedBox.setDisable(false);
        likedBox.requestFocus();
    }
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        likedBox.setDisable(true);
    }
    public void commitEdit(Boolean value) {
        super.commitEdit(value);
        likedBox.setDisable(true);
    }
    
    @Override
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(likedBox);
            if (ov instanceof BooleanProperty) {
                likedBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
            }
            ov = getTableColumn().getCellObservableValue(getIndex());
            if (ov instanceof BooleanProperty) {
                likedBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
            }
        }
    }
}