package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

public class ChoixPersonnelDeGare extends EtatJoueur {

    public ChoixPersonnelDeGare(Joueur joueurCourant) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Choisissez deck, argent ou ferraille");
    }

    @Override
    public void carteEnMainChoisie(String carteChoisie) {
        if (carteChoisie.equals("Ferraille")) {
            Carte c = joueurCourant.getMain().retirer(carteChoisie);
            joueurCourant.remettreCarteDansLaReserve(c);
            passer();
        }
    }

    @Override
    public void recevoirArgent() {
        joueurCourant.incrementerArgent(1);
        passer();
    }

    @Override
    public void piocheChoisie() {
        joueurCourant.piocherEnMain();
        passer();
    }

    @Override
    public void passer() {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        continuerTour();
    }

}
