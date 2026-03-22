package GUI.views;

import Database.User;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class VaultEntryCell extends ListCell<User> {
    private final Label serviceName = new Label();
    private final Label userName = new Label();
    private final VBox layout = new VBox(2, serviceName, userName);

    public VaultEntryCell() {
        serviceName.setStyle("-fx-font-weight: bold; -fx-font-size: 13");
        userName.setStyle("-fx-text-fill: gray; -fx-font-size: 11");
        layout.setPadding(new Insets(5, 10, 5, 10));
    }

    @Override
    protected void updateItem(User entry, boolean empty) {
        super.updateItem(entry, empty);
        if (empty || entry == null) {
            setGraphic(null);
            setText(null);
        } else {
            serviceName.setText(entry.getServiceName());
            userName.setText(entry.getUserName());
            setGraphic(layout);
            setText(null);
        }
    }
}
