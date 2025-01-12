package fr.umontpellier.iut.trainsJavaFX.mecanique.plateau;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.EffetTour;

public class TuileEtoile extends Tuile {
    private final int valeur;

    public TuileEtoile(int valeur) {
        super();
        this.valeur = valeur;
    }

    @Override
    public int getSurcout(Joueur joueur) {
        if (joueur.hasEffet(EffetTour.VOIE_SOUTERRAINE)) {
            return 0;
        }
        return valeur + super.getSurcout(joueur);
    }

    @Override
    public boolean peutEtrePositionDepart() {
        return false;
    }

    @Override
    public int getNbPointsVictoire() {
        return valeur;
    }
}
