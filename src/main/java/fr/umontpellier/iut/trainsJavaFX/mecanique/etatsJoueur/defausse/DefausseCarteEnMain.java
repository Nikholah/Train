package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.defausse;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.List;

public abstract class DefausseCarteEnMain extends EtatJoueur {

    List<String> nomsCartesEnMain;

    public DefausseCarteEnMain(Joueur joueurCourant, List<String> nomsCartesEnMain) {
        super(joueurCourant);
        this.nomsCartesEnMain = nomsCartesEnMain;
        getJeu().instructionProperty().setValue("Défaussez une carte de votre main");
    }

    public void carteEnMainChoisie(String carteChoisie) {
        if (nomsCartesEnMain.contains(carteChoisie)) {
            Carte c = joueurCourant.getMain().retirer(carteChoisie);
            nomsCartesEnMain.remove(carteChoisie);
            joueurCourant.defausser(c);
            suiteAction();
        }
        if (nomsCartesEnMain.isEmpty())
            passer();
    }

    public abstract void suiteAction();

    @Override
    public void passer() {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        continuerTour();
    }

}
