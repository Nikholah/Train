package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class Depotoir extends Carte {

    public Depotoir() {
        super("Dépotoir", 5, 1, TypeCarte.ACTION);
    }

    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.ajouterEffet(EffetTour.DEPOTOIR);
    }
}
