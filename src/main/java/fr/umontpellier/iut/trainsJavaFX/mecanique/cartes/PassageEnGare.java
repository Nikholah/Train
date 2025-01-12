package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class PassageEnGare extends Carte {
    public PassageEnGare() {
        super("Passage en gare", 3, 1, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocherEnMain();
    }
}
