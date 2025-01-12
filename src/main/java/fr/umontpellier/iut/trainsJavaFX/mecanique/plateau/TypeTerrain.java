package fr.umontpellier.iut.trainsJavaFX.mecanique.plateau;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.EffetTour;

public enum TypeTerrain {
    PLAINE, MONTAGNE, FLEUVE;

    public int getSurcout(Joueur joueur) {
        return switch (this) {
            case PLAINE -> 0;
            case FLEUVE -> joueur.hasEffet(EffetTour.PONT_EN_ACIER) ? 0 : 1;
            case MONTAGNE -> joueur.hasEffet(EffetTour.TUNNEL) ? 0 : 2;
        };
    }
}
