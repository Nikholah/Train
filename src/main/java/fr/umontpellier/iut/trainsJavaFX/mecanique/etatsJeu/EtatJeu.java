package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJeu;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Jeu;
import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class EtatJeu {
    protected final Jeu jeu;
    protected final Joueur joueurCourant;

    public EtatJeu(Jeu jeu) {
        this.jeu = jeu;
        joueurCourant = jeu.getJoueurCourant();
    }

    public void demarrerPartie() {}
    public void prochainTour() {}
    public boolean finPhase() {
        return false;
    }
}
