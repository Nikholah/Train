package fr.umontpellier.iut.trainsJavaFX.vues;

import fr.umontpellier.iut.trainsJavaFX.mecanique.plateau.Plateau;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Cette classe correspond à une nouvelle fenêtre permettant de choisir le nombre et les noms des joueurs de la partie.
 * <p>
 * Sa présentation graphique peut automatiquement être actualisée chaque fois que le nombre de joueurs change.
 * Lorsque l'utilisateur a fini de saisir les noms de joueurs, il demandera à démarrer la partie.
 */
public class VueChoixJoueurs extends Stage {
    private final ObservableList<String> nomsJoueurs;
    private Plateau plateauChoisi;
    private VBox choix;
    private VBox vbox;
    private ComboBox<Integer> nbJoueurs;

    private ComboBox<String> cbplateau;

    public VueChoixJoueurs() {
        nomsJoueurs = FXCollections.observableArrayList();
        vbox = new VBox(10);
        choix = new VBox(10);
        choix.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);

        Text playerCountText = new Text("Combien de joueurs ?");
        choix.getChildren().add(playerCountText);

        nbJoueurs = new ComboBox<>();
        nbJoueurs.getItems().addAll(2, 3, 4);
        choix.getChildren().add(nbJoueurs);
        nbJoueurs.valueProperty().addListener((observableValue, integer, t1) -> {
            vbox.getChildren().clear();
            for (int i = 0; i < t1; i++) {
                TextField setNomJoueur = new TextField();
                setNomJoueur.setText("Joueur_" + (i + 1));
                setNomJoueur.setMaxWidth(100);
                vbox.getChildren().add(setNomJoueur);
            }

        });
        nbJoueurs.setValue(2);

        choix.getChildren().add(vbox);

        Label choixPlateau = new Label("Quelle plateau choisissez vous ?");
        cbplateau = new ComboBox<>();
        cbplateau.getItems().addAll("OSAKA", "TOKYO");
        cbplateau.setValue("OSAKA");
        choix.getChildren().addAll(choixPlateau, cbplateau);

        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(actionEvent -> {
            setListeDesNomsDeJoueurs();
        });

        choix.getChildren().add(confirmButton);

        Scene sc = new Scene(choix, 500, 500);
        this.setScene(sc);

    }

    public List<String> getNomsJoueurs() {
        return nomsJoueurs;
    }

    /**
     * Définit l'action à exécuter lorsque la liste des participants est correctement initialisée
     */
    public void setNomsDesJoueursDefinisListener(ListChangeListener<String> quandLesNomsDesJoueursSontDefinis) {
        nomsJoueurs.addListener(quandLesNomsDesJoueursSontDefinis);
    }

    /**
     * Vérifie que tous les noms des participants sont renseignés
     * et affecte la liste définitive des participants
     */
    protected void setListeDesNomsDeJoueurs() {
        ArrayList<String> tempNamesList = new ArrayList<>();
        for (int i = 0; i < getNombreDeJoueurs(); i++) {
            String name = getJoueurParNumero(i);
            if (name == null || name.equals("")) {
                tempNamesList.clear();
                break;
            } else
                tempNamesList.add(name);
        }
        if (!tempNamesList.isEmpty()) {
            hide();
            nomsJoueurs.clear();
            nomsJoueurs.addAll(tempNamesList);
        }
    }

    /**
     * Retourne le nombre de participants à la partie que l'utilisateur a renseigné
     */
    protected int getNombreDeJoueurs() {
        return nbJoueurs.getSelectionModel().getSelectedItem();
    }

    /**
     * Retourne le nom que l'utilisateur a renseigné pour le ième participant à la partie
     *
     * @param playerNumber : le numéro du participant
     */
    protected String getJoueurParNumero(int playerNumber) {
        return ((TextField) vbox.getChildren().get(playerNumber)).getText();
    }

    public Plateau getPlateau() {
        if (Objects.equals(cbplateau.getValue(), "OSAKA"))
            return Plateau.OSAKA;
        if (Objects.equals(cbplateau.getValue(), "TOKYO"))
            return Plateau.TOKYO;
        return plateauChoisi;
    }
}
