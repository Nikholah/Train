package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.debuttour;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.List;

public class RemiseFerraille extends EtatJoueur {

    List<String> nomsCartesFerraille;

    public RemiseFerraille(Joueur joueurCourant, List<String> nomsCartesFerraille) {
        super(joueurCourant);
        this.nomsCartesFerraille = nomsCartesFerraille;
        getJeu().instructionProperty().setValue("Choisissez une carte ferraille de votre main");
    }

    @Override
    public void carteEnMainChoisie(String carteChoisie) {
        if (nomsCartesFerraille.contains(carteChoisie)) {
            Carte c = joueurCourant.getMain().getCarte(carteChoisie);
            joueurCourant.getMain().remove(c);
            nomsCartesFerraille.remove(0);
            joueurCourant.remettreCarteDansLaReserve(c);
            joueurCourant.incrementerArgent(1);
        }
        if (nomsCartesFerraille.isEmpty())
            passer();
    }

    @Override
    public void passer() {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        continuerTour();
    }

}
