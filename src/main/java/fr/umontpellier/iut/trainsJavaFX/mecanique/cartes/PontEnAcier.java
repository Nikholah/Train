package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class PontEnAcier extends CartePoseDeRails {
    public PontEnAcier() {
        super(4, "Pont en acier");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);

        joueur.ajouterEffet(EffetTour.PONT_EN_ACIER);
    }
}
