package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJeu;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Jeu;

public class InitialisationJoueurs extends EtatJeu {

    private int numeroProchainJoueurAInitialiser = 0;

    public InitialisationJoueurs(Jeu jeu) {
        super(jeu);
        joueurCourant.choisirPositionDepart();
    }

    public void prochainTour() {
        numeroProchainJoueurAInitialiser++;
    }

    @Override
    public boolean finPhase() {
        return numeroProchainJoueurAInitialiser == jeu.getJoueurs().size() - 1;
    }
}
