package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.List;

public class ChoixCarteActionEnMain extends EtatJoueur {

    List<String> nomsCartesAction;

    public ChoixCarteActionEnMain(Joueur joueurCourant, List<String> nomsCartesAction) {
        super(joueurCourant);
        this.nomsCartesAction = nomsCartesAction;
        getJeu().instructionProperty().setValue("Choisissez une carte action de votre main");
    }

    public void carteEnMainChoisie(String carteChoisie) {
        if (nomsCartesAction.contains(carteChoisie)) {
            Carte c = joueurCourant.getMain().getCarte(carteChoisie);
            c.jouer(joueurCourant);
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
            continuerTour();
        }
    }

}
