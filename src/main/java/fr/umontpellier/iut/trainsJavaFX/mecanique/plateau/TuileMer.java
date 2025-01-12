package fr.umontpellier.iut.trainsJavaFX.mecanique.plateau;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class TuileMer extends Tuile {
    public TuileMer() {
        super();
    }

    @Override
    public boolean peutPlacerRail(Joueur joueur) {
        return false;
    }

    @Override
    public boolean peutEtrePositionDepart() {
        return false;
    }
}
