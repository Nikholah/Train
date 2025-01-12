package fr.umontpellier.iut.trainsJavaFX.vues;

import fr.umontpellier.iut.trainsJavaFX.GestionJeu;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Cette classe représente la vue d'une carte.
 * <p>
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueCarte extends ImageView {

    private final Carte carte;

    public VueCarte(Carte carte) {
        this.carte = carte;
        Image image = new Image("/images/cartes/" + carte.getNom().toLowerCase().replace("é", "e").replace(" ", "_").replace("ô", "o") + ".jpg");
        this.setImage(image);
        this.setFitHeight(100);
        this.setPreserveRatio(true);
        this.setId(carte.getNom());
    }

    public void setCarteChoisieListener() {
        GestionJeu.getJeu().joueurCourantProperty().get().uneCarteDeLaMainAEteChoisie(carte.getNom());
    }

    public Carte getCarte() {
        return this.carte;
    }
}
