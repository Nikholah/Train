package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

public class ChoixFeuDeSignalisation extends EtatJoueur {

    public ChoixFeuDeSignalisation(Joueur joueur) {
        super(joueur);
        getJeu().instructionProperty().setValue("Choisissez de défausser ou remettez en deck");
    }

    @Override
    public void piocheChoisie() {
        Carte c = joueurCourant.cartesAChoisir().remove(0);
        joueurCourant.getPioche().add(0, c);
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
    }

    @Override
    public void defausser() {
        Carte c = joueurCourant.cartesAChoisir().remove(0);
        joueurCourant.defausser(c);
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
    }

}
