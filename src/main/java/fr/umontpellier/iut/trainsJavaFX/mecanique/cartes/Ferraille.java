package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class Ferraille extends Carte {

    public Ferraille() {
        super("Ferraille", 0, 0, TypeCarte.FERRAILLE);
    }

    @Override
    public boolean peutEtreJouee(Joueur joueur) {
        return false;
    }

    @Override
    public boolean peutEtreAchetee(Joueur joueur) {
        return false;
    }
}
