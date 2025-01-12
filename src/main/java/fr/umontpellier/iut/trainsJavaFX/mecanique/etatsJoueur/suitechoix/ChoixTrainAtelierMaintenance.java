package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.Set;

public class ChoixTrainAtelierMaintenance extends EtatJoueur {

    Set<String> nomsCartesTrain;

    public ChoixTrainAtelierMaintenance(Joueur joueurCourant, Set<String> nomsCartesTrain) {
        super(joueurCourant);
        this.nomsCartesTrain = nomsCartesTrain;
        getJeu().instructionProperty().setValue("Choisissez une carte train de votre main");
    }

    public void carteEnMainChoisie(String carteDevoilee) {
        if (nomsCartesTrain.contains(carteDevoilee)) {
            joueurCourant.recevoir(carteDevoilee);
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
            continuerTour();
        }
    }

}
