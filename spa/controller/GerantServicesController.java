package spa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import spa.Navigation;
import spa.Services;
import spa.service.ServiceManagement;

public class GerantServicesController extends Navigation {

    @FXML private TextField nomField;
    @FXML private TextField prixField;

    @FXML private TableView<Services> tableService;
    @FXML private TableColumn<Services, String> colNom;
    @FXML private TableColumn<Services, Double> colPrix;

    private final ServiceManagement serviceMgmt = new ServiceManagement();

    // ================= INIT =================
    @FXML
    private void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tableService.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, s) -> {
                    if (s != null) {
                        nomField.setText(s.getNom());
                        prixField.setText(String.valueOf(s.getPrix()));
                    }
                }
        );
    }

    // ================= AFFICHER =================
    @FXML
    private void afficher() {
        try {
            ObservableList<Services> data =
                    FXCollections.observableArrayList(serviceMgmt.getAll());
            tableService.setItems(data);
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les services");
        }
    }

    // ================= AJOUTER =================
    @FXML
    private void ajouter() {
        try {
            Services s = new Services(
                    nomField.getText(),
                    Double.parseDouble(prixField.getText())
            );

            serviceMgmt.ajouterService(s);
            afficher();
            clear();
            alert("Succès", "Service ajouté");
        } catch (Exception e) {
            alert("Erreur", "Vérifiez les champs");
        }
    }

    // ================= SUPPRIMER =================
    @FXML
    private void supprimer() {
        Services selected = tableService.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Attention", "Sélectionnez un service");
            return;
        }

        try {
            serviceMgmt.supprimerService(selected.getNom());
            afficher();
            alert("Succès", "Service supprimé");
        } catch (Exception e) {
            alert("Erreur", "Suppression impossible");
        }
    }

    // ================= UTILS =================
    private void clear() {
        nomField.clear();
        prixField.clear();
    }

    @FXML
    private void back() throws Exception {
        load("gerant.fxml");
    }

    private void alert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }
}
