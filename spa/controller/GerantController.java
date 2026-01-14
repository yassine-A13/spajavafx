package spa.controller;

import javafx.fxml.FXML;
import spa.Navigation;

public class GerantController extends Navigation {

    @FXML
    private void users() throws Exception {
        load("gerant_users.fxml");
    }

    @FXML
    private void services() throws Exception {
        load("gerant_services.fxml");
    }

    @FXML
    private void rdv() throws Exception {
        load("gerant_rdv.fxml");
    }

    @FXML
    private void back() throws Exception {
        System.exit(1);
    }
}
