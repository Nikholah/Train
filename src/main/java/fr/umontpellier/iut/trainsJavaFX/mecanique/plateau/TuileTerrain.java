package fr.umontpellier.iut.trainsJavaFX.mecanique.plateau;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.EffetTour;

public class TuileTerrain extends Tuile {
    private final TypeTerrain type;

    public TuileTerrain(TypeTerrain type) {
        super();
        this.type = type;
    }

    @Override
    public int getSurcout(Joueur joueur) {
        if (joueur.hasEffet(EffetTour.VOIE_SOUTERRAINE)) {
            return 0;
        }
        return type.getSurcout(joueur) + super.getSurcout(joueur);
    }
}
