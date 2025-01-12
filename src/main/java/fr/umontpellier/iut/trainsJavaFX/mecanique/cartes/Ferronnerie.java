package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class Ferronnerie extends Carte {

    public Ferronnerie() {
        super("Ferronnerie", 4, 1, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.ajouterEffet(EffetTour.FERRONNERIE);
    }
}
