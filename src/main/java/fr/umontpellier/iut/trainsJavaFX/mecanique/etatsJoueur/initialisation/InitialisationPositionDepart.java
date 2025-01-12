package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.initialisation;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;

public class InitialisationPositionDepart extends EtatJoueur {

    public InitialisationPositionDepart(Joueur joueurCourant) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Choisissez la position de départ");
    }

    public void choisirTuile() {
        joueurCourant.setEtatCourant(new TuileInitiale(joueurCourant));
    }

}
