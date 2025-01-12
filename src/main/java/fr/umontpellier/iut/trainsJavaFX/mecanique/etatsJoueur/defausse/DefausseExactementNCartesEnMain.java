package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.defausse;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

import java.util.List;

public class DefausseExactementNCartesEnMain extends DefausseCarteEnMain {

    private int nbCartesRestantADefausser;

    public DefausseExactementNCartesEnMain(Joueur joueurCourant, List<String> nomsCartesEnMain, int nbCartesADefausser) {
        super(joueurCourant, nomsCartesEnMain);
        nbCartesRestantADefausser = nbCartesADefausser;
        getJeu().instructionProperty().setValue("Défaussez deux cartes de votre main");
    }

    public void carteEnMainChoisie(String carteChoisie) {
       super.carteEnMainChoisie(carteChoisie);
       if (nbCartesRestantADefausser == 0)
           super.passer();
        else
            getJeu().instructionProperty().setValue("Défaussez une carte de votre main");
    }

    @Override
    public void suiteAction() {
        nbCartesRestantADefausser--;
    }

    @Override
    public void passer() {}

}