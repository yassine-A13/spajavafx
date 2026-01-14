package spa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import spa.Navigation;
import spa.RendezVous;
import spa.service.RendezVousManagement;

public class GerantRdvController extends Navigation {

    @FXML private TextField idField;
    @FXML private DatePicker datePicker;
    @FXML private TextField clientField;
    @FXML private TextField employeField;
    @FXML private TextField serviceField;
    @FXML private TextField prixField;

    @FXML private TableView<RendezVous> tableRdv;
    @FXML private TableColumn<RendezVous, Integer> colId;
    @FXML private TableColumn<RendezVous, Object> colDate;
    @FXML private TableColumn<RendezVous, String> colClient;
    @FXML private TableColumn<RendezVous, String> colEmploye;
    @FXML private TableColumn<RendezVous, String> colService;
    @FXML private TableColumn<RendezVous, Double> colPrix;




    private final RendezVousManagement rdvService = new RendezVousManagement();

    // ================= INITIALISATION TABLE =================
    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        colEmploye.setCellValueFactory(new PropertyValueFactory<>("nomEmployee"));
        colService.setCellValueFactory(new PropertyValueFactory<>("nomService"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));





    }

    // ================= AFFICHER =================
    @FXML
    private void afficher() {
        try {
            ObservableList<RendezVous> data =
                    FXCollections.observableArrayList(rdvService.getAll());
            tableRdv.setItems(data);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les rendez-vous");
        }
    }

    // ================= CREER =================
    @FXML
    private void creer() {
        try {
            RendezVous rdv = new RendezVous(
                    Integer.parseInt(idField.getText()),
                    datePicker.getValue(),
                    clientField.getText(),
                    employeField.getText(),
                    serviceField.getText(),
                    Double.parseDouble(prixField.getText())

            );

            rdvService.create(rdv);
            afficher();
            clearFields();

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Rendez-vous créé avec succès");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Vérifiez les champs saisis");
        }
    }

    // ================= MODIFIER =================
    @FXML
    private void modifier() {
        RendezVous selected = tableRdv.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Attention", "Sélectionnez un rendez-vous");
            return;
        }

        try {
            RendezVous updated = new RendezVous(
                    selected.getId(),
                    datePicker.getValue(),
                    clientField.getText(),
                    employeField.getText(),
                    serviceField.getText(),
                    Double.parseDouble(prixField.getText())

            );

            rdvService.updatebyid(selected.getId(), updated);
            afficher();
            clearFields();

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Rendez-vous modifié");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Modification impossible");
        }
    }

    // ================= SUPPRIMER =================
    @FXML
    private void supprimer() {
        RendezVous selected = tableRdv.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Attention", "Sélectionnez un rendez-vous");
            return;
        }

        try {
            rdvService.deletebyid(selected.getId());
            afficher();
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Rendez-vous supprimé");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Suppression impossible");
        }
    }

    // ================= UTILS =================
    private void clearFields() {
        idField.clear();
        datePicker.setValue(null);
        clientField.clear();
        employeField.clear();
        serviceField.clear();
        prixField.clear();
    }

    @FXML
    private void back() throws Exception {
        load("gerant.fxml");
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}
