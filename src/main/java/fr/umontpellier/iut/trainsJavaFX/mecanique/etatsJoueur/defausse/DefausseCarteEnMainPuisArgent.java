package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.defausse;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

import java.util.List;

public class DefausseCarteEnMainPuisArgent extends DefausseCarteEnMain {

    public DefausseCarteEnMainPuisArgent(Joueur joueurCourant, List<String> nomsCartesEnMain) {
        super(joueurCourant, nomsCartesEnMain);
        getJeu().instructionProperty().setValue("Défaussez une carte de votre main");
    }

    @Override
    public void suiteAction() {
        joueurCourant.incrementerArgent(1);
    }

}
