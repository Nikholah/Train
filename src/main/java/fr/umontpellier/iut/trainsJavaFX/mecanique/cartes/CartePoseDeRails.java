package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public abstract class CartePoseDeRails extends Carte {
    public CartePoseDeRails(int cout, String nom) {
        super(nom, cout, 0, TypeCarte.RAIL);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        // effet de Ferronnerie (pas clair si l'effet doit être dans `jouer` ou dans `Joueur.jouerTour`)
        joueur.incrementerArgent(2 * joueur.nbEffet(EffetTour.FERRONNERIE));
        
        joueur.incrementerRails();
        joueur.recevoirFerraille();
    }
}
