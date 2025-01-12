package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJeu;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Jeu;

public class PartieEnCours extends EtatJeu {
    public PartieEnCours(Jeu jeu) {
        super(jeu);
    }

    @Override
    public void demarrerPartie() {
        jeu.passeAuJoueurSuivant();
        jeu.getJoueurCourant().jouerTour();
    }
}
