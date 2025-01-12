package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;

public class ChoixCarteRemorquage extends ChoixCarte {

    public ChoixCarteRemorquage(Joueur joueurCourant) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Choisissez une carte train de votre défausse");
    }

    public void actionCarteChoisie(String carteChoisie) {
        Carte c = joueurCourant.cartesAChoisir().retirer(carteChoisie);
        joueurCourant.getDefausse().retirer(carteChoisie);
        joueurCourant.ajouterMain(c);
        joueurCourant.cartesAChoisir().clear();
    }

}
