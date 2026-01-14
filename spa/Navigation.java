package spa;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Navigation {

    protected void load(String fxml) throws Exception {
        Stage stage = (Stage) Stage.getWindows().filtered(w -> w.isShowing()).get(0);
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/spa/view/" + fxml))
        ));
    }
}
