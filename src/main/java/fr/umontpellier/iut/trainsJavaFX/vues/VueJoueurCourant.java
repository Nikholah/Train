package fr.umontpellier.iut.trainsJavaFX.vues;

import fr.umontpellier.iut.trainsJavaFX.IJeu;
import fr.umontpellier.iut.trainsJavaFX.IJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.Objects;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 * <p>
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends HBox {

    private final IJeu jeu;

    @FXML
    public FlowPane main;

    @FXML
    public FlowPane recu;

    @FXML
    public FlowPane defausse;
    @FXML
    public VBox ico;

    private Label argentLab;
    private Label railLab;
    private VueCarte carte1;

    public VueJoueurCourant(IJeu jeu) {
        this.jeu = jeu;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vuejoueur.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        ListChangeListener<Carte> changementCarte = change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    main.getChildren().remove(trouverCarte(change.getRemoved().get(0), "main"));
                }
                if (change.wasAdded()) {
                    ajouterCarteMain();
                }
            }
        };
        this.setBackground(Background.fill(Paint.valueOf("GRAY")));

        ListChangeListener<Carte> changeRecu = change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    recu.getChildren().clear();
                    for (Carte c : jeu.joueurCourantProperty().get().cartesRecuesProperty()) {
                        VueCarte carte = new VueCarte(c);
                        carte.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                            carte.setCarteChoisieListener();
                        });
                        carte.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
                            carte1 = new VueCarte(carte.getCarte());
                            carte1.setId("display");
                            defausse.getChildren().add(carte1);
                            carte1.setTranslateX(-500);
                            carte1.setScaleX(5);
                            carte1.setScaleY(5);
                            carte1.setTranslateY(-300);

                        });

                        carte.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
                            defausse.getChildren().remove(carte1);
                        });
                        recu.getChildren().add(carte);
                    }
                }
                if (change.wasRemoved()) {
                    for (Carte c : change.getRemoved()) {
                        recu.getChildren().remove(trouverCarte(c, "recu"));
                    }

                }
            }
        };

        StackPane argentPane = new StackPane();
        Image argentico = new Image("/images/boutons/coins.png");
        ImageView imageViewArgent = new ImageView(argentico);
        imageViewArgent.setPreserveRatio(true);
        imageViewArgent.setFitHeight(50);
        argentLab = new Label("0");
        argentPane.getChildren().addAll(imageViewArgent, argentLab);

        StackPane railPane = new StackPane();
        Image railico = new Image("/images/boutons/rail.png");
        ImageView railView = new ImageView(railico);
        railView.setPreserveRatio(true);
        railView.setFitHeight(50);
        railLab = new Label("0");
        railPane.getChildren().addAll(railView, railLab);
        ico.getChildren().addAll(argentPane, railPane);

        for (IJoueur j : jeu.getJoueurs()) {
            j.mainProperty().get().addListener(changementCarte);
            j.cartesRecuesProperty().get().addListener(changeRecu);
            j.argentProperty().addListener((observableValue, number, t1) -> {
                argentLab.setText(t1.toString());
            });
            j.pointsRailsProperty().addListener((observableValue, number, t1) -> {
                railLab.setText(t1.toString());
            });
        }

        jeu.joueurCourantProperty().addListener(((observableValue, old, newValue) -> {
            ajouterCarteMain();
        }));

        Image pioche = new Image("/images/boutons/deck.png");
        ImageView piocheView = new ImageView(pioche);
        piocheView.setPreserveRatio(true);
        piocheView.setFitHeight(100);
        Image defausse = new Image("/images/boutons/defausse.png");
        ImageView defausseView = new ImageView(defausse);
        defausseView.setPreserveRatio(true);
        defausseView.setFitHeight(100);
        defausseView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("cliquer");
        });
        this.defausse.getChildren().addAll(piocheView, defausseView);
        this.defausse.setPrefHeight(20);

        main.prefWidthProperty().bind(this.widthProperty().divide(3));
        recu.prefWidthProperty().bind(this.widthProperty().divide(3));

    }

    public ImageView trouverCarte(Carte carteATrouver, String lieu) {
        ImageView carteTrouver = new ImageView();
        if (Objects.equals(lieu, "main")) {
            for (int i = 0; i < main.getChildren().size(); i++) {
                if (Objects.equals(main.getChildren().get(i).getId(), carteATrouver.getNom())) {
                    return (ImageView) main.getChildren().get(i);
                }
            }
        } else if (Objects.equals(lieu, "recu")) {
            for (int i = 0; i < recu.getChildren().size(); i++) {
                if (Objects.equals(recu.getChildren().get(i).getId(), carteATrouver.getNom())) {
                    return (ImageView) recu.getChildren().get(i);
                }
            }
        } else if (Objects.equals(lieu, "display")) {
            for (int i = 0; i < main.getChildren().size(); i++) {
                if (Objects.equals(main.getChildren().get(i).getId(), "display")) {
                    return (ImageView) main.getChildren().get(i);
                }
            }
        }
        System.out.println("pas trouver");
        return carteTrouver;
    }

    public void ajouterCarteMain(){
        main.getChildren().clear();
        for (Carte c : jeu.joueurCourantProperty().get().mainProperty()) {
            VueCarte carte = new VueCarte(c);
            carte.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                carte.setCarteChoisieListener();
            });
            carte.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {

                carte1 = new VueCarte(carte.getCarte());
                carte1.setId("display");
                defausse.getChildren().add(carte1);
                carte1.setTranslateX(-500);
                carte1.setScaleX(5);
                carte1.setScaleY(5);
                carte1.setTranslateY(-300);

            });

            carte.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
                defausse.getChildren().remove(carte1);
            });
            main.getChildren().add(carte);
        }
    }
}
