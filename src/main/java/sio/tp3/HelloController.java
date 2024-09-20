package sio.tp3;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sio.tp3.module.Tache;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class HelloController implements Initializable {
    @FXML
    private TextField txtTache;
    @FXML
    private ComboBox cboAssignation;
    @FXML
    private TreeView tvTaches;
    @FXML
    private ListView lvThemes;
    @FXML
    private ListView lvProjets;
    @FXML
    private Button btnValider;
    private HashMap<String, HashMap<String, ArrayList<Tache>>> mesTaches;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mesTaches = new HashMap<>(); //key : String nom du thème. Valeur : autre hashmap qui a pour key : nom du projet, valeur : Arraylist de taches
        lvThemes.getItems().addAll("Mobile", "Web", "Réseau");
        lvProjets.getItems().addAll("Projet n°1", "Projet n°2", "Projet n°3", "Projet n°4", "Projet n°5", "Projet n°6");
        cboAssignation.getItems().addAll("Enzo", "Lilou", "Noa");
        cboAssignation.getSelectionModel().selectFirst();
        }


    @FXML
    public void cboAssignationClicked(Event event) {
    }

    @FXML
    public void btnValider(Event event) {
        if (lvThemes.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Choix du thème");
            alert.setContentText("Sélectionner un thème");
            alert.showAndWait();
        } else if (lvProjets.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Choix du projet");
            alert.setContentText("Sélectionner un projet");
            alert.showAndWait();
        } else if (txtTache.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Saisir une tâche");
            alert.showAndWait();
        } else {
            TreeItem<String> root = new TreeItem<>("Mes tâches");
            String nomDeveloppeur = cboAssignation.getSelectionModel().getSelectedItem().toString();
            String nomDuTheme = lvThemes.getSelectionModel().getSelectedItem().toString();
            String nomDuProjet = lvProjets.getSelectionModel().getSelectedItem().toString();
            Tache maTache = new Tache(txtTache.getText(), nomDeveloppeur, false);

            // Récupérer ou créer la HashMap du projet pour le thème sélectionné
            HashMap<String, ArrayList<Tache>> nomDuProjetHashMap = mesTaches.getOrDefault(nomDuTheme, new HashMap<>());

            // Récupérer ou créer la liste de tâches pour le projet sélectionné
            ArrayList<Tache> lesTaches = nomDuProjetHashMap.getOrDefault(nomDuProjet, new ArrayList<>());

            // Ajouter la nouvelle tâche
            lesTaches.add(maTache);

            // Mettre à jour la HashMap pour le projet
            nomDuProjetHashMap.put(nomDuProjet, lesTaches);

            // Mettre à jour la HashMap principale
            mesTaches.put(nomDuTheme, nomDuProjetHashMap);

            // Mise à jour de l'arbre
            for (String nomTheme : mesTaches.keySet()) {
                TreeItem<String> noeudTheme = new TreeItem<>(nomTheme);
                noeudTheme.setExpanded(true);
                root.getChildren().add(noeudTheme);
                for (String projetSelectionne : mesTaches.get(nomTheme).keySet()) {
                    TreeItem<String> noeudProjet = new TreeItem<>(projetSelectionne);
                    noeudProjet.setExpanded(true);
                    noeudTheme.getChildren().add(noeudProjet);
                    for (Tache tache : mesTaches.get(nomTheme).get(projetSelectionne)) {
                        TreeItem<String> noeudDeveloppeur = new TreeItem<>(tache.getNomDeveloppeur() + " : " + tache.getNomtache() + " : " + tache.isEstTerminee());
                        noeudProjet.getChildren().add(noeudDeveloppeur);
                        noeudDeveloppeur.setExpanded(true);
//                        if (tvTaches.getSelectionModel().getSelectedItem() == noeudDeveloppeur && tache.isEstTerminee() == false)
//                        {
//                           tache.setEstTerminee(true);
//                           noeudDeveloppeur = tache.getNomDeveloppeur() + " : " + tache.getNomtache() + " : " + tache.isEstTerminee();
//                        }
                    }
                }
            }

            root.setExpanded(true);
            tvTaches.setRoot(root);
        }
    }

}