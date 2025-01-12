package fr.umontpellier.iut.trainsJavaFX.vues;

import fr.umontpellier.iut.trainsJavaFX.IJeu;
import fr.umontpellier.iut.trainsJavaFX.IJoueur;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

/**
 * Cette classe présente les éléments des joueurs autres que le joueur courant,
 * en cachant ceux que le joueur courant n'a pas à connaitre.
 * <p>
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueAutresJoueurs extends VBox {
    public VueAutresJoueurs(IJeu jeu) {
        this.setSpacing(10);
        Label text = new Label("Vous jouez contre :");
        this.getChildren().add(text);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-border-color:black; " +
                "-fx-border-radius: 15;" +
                "-fx-border-width: 2;");
        for (IJoueur j : jeu.getJoueurs()) {
            HBox contenaire = new HBox(10);
            contenaire.setBackground(Background.fill(Paint.valueOf(CouleursJoueurs.couleursBackgroundJoueur.get(j.getCouleur()))));

            Label nomJoueur = new Label(j.getNom());
            nomJoueur.setStyle("-fx-font-size: 30");

            Label pv = new Label();
            pv.setStyle("-fx-font-size: 20");
            pv.setTextFill(Paint.valueOf("yellow"));
            Image imgpv= new Image("/images/boutons/score.png");
            ImageView imageViewPv = new ImageView(imgpv);
            imageViewPv.setPreserveRatio(true);
            imageViewPv.setFitHeight(60);
            StackPane pvPane = new StackPane();
            pvPane.getChildren().addAll(imageViewPv, pv);
            pv.textProperty().bind(new StringBinding() {

                {
                    this.bind(j.scoreProperty());
                }
                @Override
                protected String computeValue() {
                    return Integer.toString(j.getScoreTotal());
                }
            });

            Label rail = new Label();
            rail.setStyle("-fx-font-size: 20");
            rail.setTextFill(Paint.valueOf("yellow"));
            Image imgrails = new Image("/images/boutons/rails.png");
            ImageView imageViewRails = new ImageView(imgrails);
            imageViewRails.setPreserveRatio(true);
            imageViewRails.setFitHeight(60);
            StackPane railsPane = new StackPane();
            railsPane.getChildren().addAll(imageViewRails, rail);
            rail.textProperty().bind(new StringBinding() {

                {
                    this.bind(j.nbJetonsRailsProperty());
                }
                @Override
                protected String computeValue() {
                    return Integer.toString(j.nbJetonsRailsProperty().get());
                }
            });

            Label pioche = new Label();
            pioche.setStyle("-fx-font-size: 20");
            pioche.setTextFill(Paint.valueOf("yellow"));
            Image imgpioche = new Image("/images/boutons/deck.png");
            ImageView imageViewPioche = new ImageView(imgpioche);
            imageViewPioche.setPreserveRatio(true);
            imageViewPioche.setFitHeight(60);
            StackPane piochePane = new StackPane();
            piochePane.getChildren().addAll(imageViewPioche, pioche);
            pioche.textProperty().bind(new StringBinding() {

                {
                    this.bind(j.piocheProperty());
                }
                @Override
                protected String computeValue() {
                    return String.valueOf(j.piocheProperty().get().size());
                }
            });


            Label defausse = new Label();
            defausse.setStyle("-fx-font-size: 20");
            defausse.setTextFill(Paint.valueOf("yellow"));
            Image imgdefausse = new Image("/images/boutons/defausse.png");
            ImageView imageViewDefausse = new ImageView(imgdefausse);
            imageViewDefausse.setPreserveRatio(true);
            imageViewDefausse.setFitHeight(60);
            StackPane defaussePane = new StackPane();
            defaussePane.getChildren().addAll(imageViewDefausse, defausse);
            defausse.textProperty().bind(new StringBinding() {

                {
                    this.bind(j.defausseProperty());
                }
                @Override
                protected String computeValue() {
                    return String.valueOf(j.defausseProperty().get().size());
                }
            });

            contenaire.getChildren().addAll(nomJoueur, pvPane, railsPane, piochePane, defaussePane);
            this.getChildren().add(contenaire);
        }
    }
}
