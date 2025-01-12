package fr.umontpellier.iut.trainsJavaFX.vues;

import fr.umontpellier.iut.trainsJavaFX.IJoueur;
import fr.umontpellier.iut.trainsJavaFX.TrainsIHM;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class VueResultats extends Stage {

    private TrainsIHM ihm;
    private BorderPane root;
    private VBox tot;
    private List<PlayerScore> scoresTrie;
    private boolean partieTerminee = false;

    public VueResultats(TrainsIHM ihm) {
        this.ihm = ihm;
        root = new BorderPane();
        tot = new VBox();
        tot.setAlignment(Pos.CENTER);

        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        root.setPrefSize(800, 600);

        Label titre = new Label("Résultats");
        titre.setStyle("-fx-font-size: 30;" + "-fx-font-weight: bold;");
        root.setTop(titre);
        BorderPane.setAlignment(titre, Pos.CENTER);

        if (ihm.getJeu().finDePartieProperty().get()) {
            afficherPodium();
        } else {
            Button fin = new Button("Cliquez pour afficher le podium");
            fin.setOnAction(actionEvent -> {
                ihm.getJeu().finDePartieProperty().set(true);
                afficherPodium();
            });
            tot.getChildren().add(fin);
        }

        this.setTitle("Fin du jeu");
        root.setCenter(tot);
        Scene sc = new Scene(root);
        this.setScene(sc);
    }

    private void afficherPodium() {
        if (!partieTerminee) {
            partieTerminee = true;

            podium();
        }
    }

    public void podium() {
        List<IJoueur> joueurs = (List<IJoueur>) ihm.getJeu().getJoueurs();

        scoresTrie = joueurs.stream()
                .map(joueur -> new PlayerScore(joueur.getNom(), joueur))
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .collect(Collectors.toList());

        TableView<PlayerScore> table = new TableView<>();

        TableColumn<PlayerScore, String> rankColumn = new TableColumn<>("Classement");
        rankColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(scoresTrie.indexOf(data.getValue()) + 1)));

        TableColumn<PlayerScore, String> nameColumn = new TableColumn<>("Joueurs");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<PlayerScore, String> scoreColumn = new TableColumn<>("Scores");
        scoreColumn.setCellValueFactory(data -> new StringBinding() {
            {
                bind(data.getValue().getJoueur().scoreProperty());
            }

            @Override
            protected String computeValue() {
                return Integer.toString(data.getValue().getJoueur().getScoreTotal());
            }
        });

        table.getColumns().add(rankColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(scoreColumn);

        table.getItems().addAll(scoresTrie);

        table.setFixedCellSize(25);
        int numberOfPlayers = scoresTrie.size();
        table.setPrefHeight(numberOfPlayers * table.getFixedCellSize() + 30);

        table.setMaxWidth(400);

        VBox tableContainer = new VBox(table);
        tableContainer.setAlignment(Pos.CENTER);
        tableContainer.setMaxWidth(400);

        tot.getChildren().clear();
        tot.getChildren().add(tableContainer);

        Button again = new Button("Relancer");
        again.setOnAction(actionEvent -> {
            ihm.demarrerPartie();
            hide();
        });
        tot.getChildren().add(again);

        root.setCenter(tot);
    }

    public static class PlayerScore {
        private final StringProperty name;
        private final IJoueur joueur;

        public PlayerScore(String name, IJoueur joueur) {
            this.name = new SimpleStringProperty(name);
            this.joueur = joueur;
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public int getScore() {
            return joueur.getScoreTotal();
        }

        public IJoueur getJoueur() {
            return joueur;
        }
    }
}
