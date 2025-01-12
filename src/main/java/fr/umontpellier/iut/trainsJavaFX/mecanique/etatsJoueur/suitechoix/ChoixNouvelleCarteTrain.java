package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.Set;

public class ChoixNouvelleCarteTrain extends EtatJoueur {

    Set<String> nomsCartesTrain;

    public ChoixNouvelleCarteTrain(Joueur joueurCourant, Set<String> nomsCartesTrain, int coutMaximal) {
        super(joueurCourant);
        this.nomsCartesTrain = nomsCartesTrain;
        getJeu().instructionProperty().setValue("Recevez une carte train de valeur <= " + coutMaximal);
    }

    @Override
    public void carteEnReserveChoisie(String nomCarteTrain) {
        if (nomsCartesTrain.contains(nomCarteTrain)) {
            joueurCourant.recevoirEnMain(nomCarteTrain);
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        }
    }

}



