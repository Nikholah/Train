package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class SalleDeControle extends Carte {

    public SalleDeControle() {
        super("Salle de contrôle", 7, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocherEnMain(3);
    }
}
