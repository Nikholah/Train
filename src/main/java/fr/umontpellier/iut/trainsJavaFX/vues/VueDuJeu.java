package fr.umontpellier.iut.trainsJavaFX.vues;

import fr.umontpellier.iut.trainsJavaFX.IJeu;
import fr.umontpellier.iut.trainsJavaFX.IJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.util.Map;
import java.util.Objects;

/**
 * Cette classe correspond à la fenêtre principale de l'application.
 *
 * Elle est initialisée avec une référence sur la partie en cours (Jeu).
 * <p>
 * On y définit les bindings sur les éléments internes qui peuvent changer
 * (le joueur courant, ses cartes en main, son score, ...)
 * ainsi que les listeners à exécuter lorsque ces éléments changent
 */
public class VueDuJeu extends BorderPane {

    private final IJeu jeu;
    private VuePlateau plateau;
    private VueJoueurCourant vueJoueurCourant;
    private Label instruction;
    private Label nomJoueur;
    private Carte derniereCarteJouer;

    private VBox droite;
    private VueCarte carte1;

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;
        vueJoueurCourant = new VueJoueurCourant(this.jeu);
        droite = new VBox(10);
        FlowPane top = new FlowPane();
        top.setAlignment(Pos.CENTER);
        for (Carte c : jeu.getReserve()){
            StackPane list = new StackPane();
            Map<String, IntegerProperty> pilesReserve = jeu.getTaillesPilesReserveProperties();
            Label nbCarteRestante = new Label();
            nbCarteRestante.setStyle("-fx-font-size: 20;"+ "-fx-font-weight: bold;");
            nbCarteRestante.setTextFill(Paint.valueOf("yellow"));
            nbCarteRestante.setText(String.valueOf(pilesReserve.get(c.getNom()).get()));

            BooleanBinding bool = new BooleanBinding() {

                {
                    this.bind(nbCarteRestante.textProperty());
                }
                @Override
                protected boolean computeValue() {
                    return !Objects.equals(nbCarteRestante.textProperty().get(), "0");
                }
            };

            VueCarte carte = new VueCarte(c);
            carte.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                jeu.uneCarteDeLaReserveEstAchetee(c.getNom());
                nbCarteRestante.setText(String.valueOf(pilesReserve.get(c.getNom()).get()));
            });
            carte.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {

                carte1 = new VueCarte(carte.getCarte());
                carte1.setId("display");
                droite.getChildren().add(carte1);
                carte1.setTranslateX(-500);
                carte1.setScaleX(5);
                carte1.setScaleY(5);
                carte1.setTranslateY(-200);

            });

            carte.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
               droite.getChildren().remove(carte1);
            });
            list.visibleProperty().bind(bool);
            list.getChildren().addAll(carte, nbCarteRestante);
            StackPane.setAlignment(nbCarteRestante, Pos.BOTTOM_CENTER);
            top.getChildren().add(list);
        }

        setTop(top);

        plateau = new VuePlateau();
        StackPane plat = new StackPane();
        plat.getChildren().add(plateau);
        VBox popup = new VBox(10);
        popup.setStyle("-fx-background-color: white;");

        popup.setVisible(false);
        popup.setAlignment(Pos.CENTER);
        ListChangeListener<Carte> choisirListener = change -> {
            while(change.next()){

                if (change.wasAdded()){
                    popup.getChildren().clear();
                    popup.getChildren().add(new javafx.scene.control.Label(instruction.getText()));

                    if (Objects.equals(derniereCarteJouer.getNom(), "Feu de signalisation")){
                        Carte c = change.getAddedSubList().get(0);
                        VueCarte carte = new VueCarte(c);
                        HBox boutons = new HBox(10);
                        boutons.setAlignment(Pos.CENTER);
                        Button defausse = new Button("défaussez");
                        defausse.setOnAction(actionEvent -> {
                            jeu.joueurCourantProperty().get().laDefausseAEteChoisie();
                            popup.setVisible(false);
                        });
                        Button remettre = new Button("remettez");
                        remettre.setOnAction(actionEvent -> {
                            jeu.joueurCourantProperty().get().laPiocheAEteChoisie();
                            popup.setVisible(false);
                        });
                        boutons.getChildren().addAll(defausse, remettre);
                        popup.getChildren().addAll(carte, boutons);
                    } else {
                        FlowPane popupPane = new FlowPane();
                        for (Carte c : change.getAddedSubList()){
                            VueCarte carte = new VueCarte(c);
                            carte.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                                jeu.uneCarteAChoisirChoisie(c.getNom());
                                popup.setVisible(false);
                        });
                            carte.setId(c.getNom());
                            popupPane.getChildren().add(carte);
                        }
                        popup.getChildren().add(popupPane);
                    }
                    popup.setVisible(true);
                }
            }
        };

        ListChangeListener<Carte> changeListener = change1 -> {
            while (change1.next()){
                if (change1.wasAdded()){
                    derniereCarteJouer = change1.getAddedSubList().get(0);
                }
            }
        };

        for(IJoueur j : jeu.getJoueurs()){
            j.cartesAChoisir().get().addListener(choisirListener);
            j.cartesEnJeuProperty().get().addListener(changeListener);

        }
        plat.getChildren().add(popup);
        setCenter(plat);

        HBox forlabel = new HBox();
        VBox bot = new VBox();
        instruction = new Label();
        instruction.setStyle("-fx-font-size: 40");
        nomJoueur = new Label();
        nomJoueur.setStyle("-fx-font-size: 40");
        forlabel.getChildren().addAll(nomJoueur, instruction);
        forlabel.setAlignment(Pos.CENTER);
        this.jeu.joueurCourantProperty().addListener((observable, oldValue, newValue) -> {nomJoueur.setText(newValue.getNom() + " - ");});
        bot.getChildren().addAll(forlabel, vueJoueurCourant);
        setBottom(bot);


        droite.setAlignment(Pos.CENTER);
        VueAutresJoueurs autresJoueurs = new VueAutresJoueurs(jeu);
        Image pass = new Image("/images/boutons/passer.png");
        ImageView passView = new ImageView(pass);
        passView.setPreserveRatio(true);
        passView.setFitHeight(150);
        passView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            jeu.passerAEteChoisi();
            System.out.println("vous avez choisi de passer votre tour");
        });
        passView.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
            passView.setFitHeight(170);
        });
        passView.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
            passView.setFitHeight(150);
        });
        droite.getChildren().addAll(autresJoueurs, passView);
        setRight(droite);
    }

    public void creerBindings() {
        plateau.prefWidthProperty().bind(getScene().widthProperty());
        plateau.prefHeightProperty().bind(getScene().heightProperty());
        plateau.creerBindings();
        instruction.textProperty().bind(getJeu().instructionProperty());
    }

    public IJeu getJeu() {
        return jeu;
    }

    EventHandler<? super MouseEvent> actionPasserParDefaut = (mouseEvent -> System.out.println("Passer a été demandé"));

}
