package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;

public class CarteEnMainChoisie extends EtatJoueur {
    public CarteEnMainChoisie(Joueur joueurCourant) {
        super(joueurCourant);
    }

    public void carteEnMainChoisie(String carteEnMain) {
        joueurCourant.jouerCarte(carteEnMain);
        continuerTour();
    }
}