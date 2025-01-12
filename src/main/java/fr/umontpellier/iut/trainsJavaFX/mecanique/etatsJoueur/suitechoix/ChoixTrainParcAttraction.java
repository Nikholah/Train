package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.Set;

public class ChoixTrainParcAttraction extends EtatJoueur {

    Set<String> nomsCartesTrain;

    public ChoixTrainParcAttraction(Joueur joueurCourant, Set<String> nomsCartesTrain) {
        super(joueurCourant);
        this.nomsCartesTrain = nomsCartesTrain;
        getJeu().instructionProperty().setValue("Choisissez une carte train en jeu");
    }

    public void carteEnJeuChoisie(String carteChoisie) {
        if (nomsCartesTrain.contains(carteChoisie)) {
             Carte c = joueurCourant.getCartesEnJeu().getCarte(carteChoisie);
            joueurCourant.incrementerArgent(c.getValeur());
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
            continuerTour();
        }
    }

}
