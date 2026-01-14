package spa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import spa.Navigation;
import spa.user;
import spa.client;
import spa.employee;
import spa.gerant;
import spa.service.UserManagement;

public class GerantUsersController extends Navigation {

    @FXML private TextField nomField;
    @FXML private TextField cinField;
    @FXML private TextField telField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleBox;

    @FXML private TableView<user> tableUser;
    @FXML private TableColumn<user, String> colNom;
    @FXML private TableColumn<user, String> colCin;
    @FXML private TableColumn<user, Integer> colTel;
    @FXML private TableColumn<user, String> colEmail;
    @FXML private TableColumn<user, String> colRole;

    private final UserManagement userService = new UserManagement();

    // ================= INIT =================
    @FXML
    private void initialize() {
        roleBox.setItems(
                FXCollections.observableArrayList("client", "employee", "gerant")
        );

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Remplir formulaire quand on clique une ligne
        tableUser.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, u) -> {
                    if (u != null) {
                        nomField.setText(u.getNom());
                        cinField.setText(u.getCin());
                        telField.setText(String.valueOf(u.getTel()));
                        emailField.setText(u.getEmail());
                        roleBox.setValue(u.getRole());
                    }
                }
        );
    }

    // ================= AFFICHER =================
    @FXML

    private void afficher() {
        try {
            ObservableList<user> data =
                    FXCollections.observableArrayList(
                            userService.getAll("tous")
                    );
            tableUser.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();   // ← OBLIGATOIRE POUR SAVOIR LA VRAIE ERREUR
            alert("Erreur", "Impossible de charger les utilisateurs");
        }
    }


    // ================= AJOUTER =================
    @FXML
    private void ajouter() {
        try {
            user u;
            String role = roleBox.getValue();

            if (role.equals("client")) {
                u = new client(nomField.getText(), cinField.getText(),
                        Integer.parseInt(telField.getText()),
                        emailField.getText(),
                        passwordField.getText(),
                        role);
                userService.ajouterUser((client) u);

            } else if (role.equals("employee")) {
                u = new employee(nomField.getText(), cinField.getText(),
                        Integer.parseInt(telField.getText()),
                        emailField.getText(),
                        passwordField.getText(),
                        role);
                userService.ajouterUser((employee) u);

            } else {
                u = new gerant(nomField.getText(), cinField.getText(),
                        Integer.parseInt(telField.getText()),
                        emailField.getText(),
                        passwordField.getText(),
                        role);
                userService.ajouterUser((gerant) u);
            }

            afficher();
            clear();
            alert("Succès", "Utilisateur ajouté");

        } catch (Exception e) {
            alert("Erreur", "Vérifiez les champs");
        }
    }

    // ================= MODIFIER =================
    @FXML
    private void modifier() {
        try {
            user updated = new user(
                    nomField.getText(),
                    cinField.getText(),
                    Integer.parseInt(telField.getText()),
                    emailField.getText(),
                    passwordField.getText(),
                    roleBox.getValue()
            );

            userService.updateByCin(updated.getCin(), updated);
            afficher();
            clear();
            alert("Succès", "Utilisateur modifié");

        } catch (Exception e) {
            alert("Erreur", "Modification impossible");
        }
    }

    // ================= SUPPRIMER =================
    @FXML
    private void supprimer() {
        user selected = tableUser.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Attention", "Sélectionnez un utilisateur");
            return;
        }

        try {
            userService.deleteUserByCin(selected.getCin());
            afficher();
            alert("Succès", "Utilisateur supprimé");
        } catch (Exception e) {
            alert("Erreur", "Suppression impossible");
        }
    }

    // ================= UTILS =================
    private void clear() {
        nomField.clear();
        cinField.clear();
        telField.clear();
        emailField.clear();
        passwordField.clear();
        roleBox.setValue(null);
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
