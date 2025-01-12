package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;

public class ChoixRemiseCartesCentreDeRenseignements extends ChoixCarte {

    public ChoixRemiseCartesCentreDeRenseignements(Joueur joueurCourant) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Remettez les cartes sur le deck");
    }

    public void actionCarteChoisie(String carteChoisie) {
        Carte c = joueurCourant.cartesAChoisir().retirer(carteChoisie);
        joueurCourant.getPioche().add(0, c);
     }

}
