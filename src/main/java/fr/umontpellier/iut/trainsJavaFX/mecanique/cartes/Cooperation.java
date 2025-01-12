package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class Cooperation extends CartePoseDeRails {
    public Cooperation() {
        super(5, "Coopération");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.ajouterEffet(EffetTour.COOPERATION);
    }
}
