package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class TGV extends CarteTrain {

    public TGV() {
        super(2, "TGV", 1, true);

    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        for (Carte c : joueur.getCartesEnJeu()) {
            if (c.getNom().equals("Train omnibus")) {
                joueur.incrementerArgent(1);
                break;
            }
        }
    }
}
