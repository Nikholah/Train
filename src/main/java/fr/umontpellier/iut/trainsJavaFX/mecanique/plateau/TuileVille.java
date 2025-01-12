package fr.umontpellier.iut.trainsJavaFX.mecanique.plateau;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.EffetTour;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TuileVille extends Tuile {
    private final int nbGaresMax;
    private final IntegerProperty nbGaresPosees;

    public TuileVille(int taille) {
        super();
        this.nbGaresMax = taille;
        this.nbGaresPosees = new SimpleIntegerProperty(0);
    }

    @Override
    public boolean peutPlacerGare() {
        return nbGaresPosees.getValue() < nbGaresMax;
    }

    @Override
    public void ajouterGare() {
        nbGaresPosees.setValue(nbGaresPosees.getValue() + 1);
    }

    @Override
    public int getSurcout(Joueur joueur) {
        if (joueur.hasEffet(EffetTour.VOIE_SOUTERRAINE)) {
            return 0;
        }
        if (joueur.hasEffet(EffetTour.VIADUC)) {
            return super.getSurcout(joueur);
        }
        return nbGaresPosees.getValue() + 1 + super.getSurcout(joueur);
    }

    @Override
    public int getNbGares() {
        return nbGaresPosees.getValue();
    }

    @Override
    public int getNbPointsVictoire() {
        return switch (nbGaresPosees.getValue()) {
            case 1 -> 2;
            case 2 -> 4;
            case 3 -> 8;
            default -> 0;
        };
    }

    public IntegerProperty nbGaresPoseesProperty() {
        return nbGaresPosees;
    }

    public int getNbGaresMax() {
        return nbGaresMax;
    }
}
