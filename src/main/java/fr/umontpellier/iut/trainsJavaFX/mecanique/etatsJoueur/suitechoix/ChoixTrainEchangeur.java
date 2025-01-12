package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.Set;

public class ChoixTrainEchangeur extends EtatJoueur {

    Set<String> nomsCartesTrain;

    public ChoixTrainEchangeur(Joueur joueurCourant, Set<String> nomsCartesTrain) {
        super(joueurCourant);
        this.nomsCartesTrain = nomsCartesTrain;
        getJeu().instructionProperty().setValue("Remettez une carte train sur votre deck");
    }

    public void carteEnJeuChoisie(String carteChoisie) {
        if (nomsCartesTrain.contains(carteChoisie)) {
            Carte c = joueurCourant.getCartesEnJeu().retirer(carteChoisie);
            joueurCourant.getPioche().add(0, c);
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
            continuerTour();
        }
    }

}
