package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

public class ChoixCarteCentreDeRenseignements extends ChoixCarte {

    public ChoixCarteCentreDeRenseignements(Joueur joueurCourant) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Vous pouvez prendre une carte en main");
    }

    public void actionCarteChoisie(String carteChoisie) {
        Carte c = joueurCourant.cartesAChoisir().retirer(carteChoisie);
        joueurCourant.ajouterMain(c);
        joueurCourant.setEtatCourant(new ChoixRemiseCartesCentreDeRenseignements(joueurCourant));
    }

    @Override
    public void passer() {
        joueurCourant.reposerCartesChoisiesDansLOrdreInitial();
        joueurCourant.cartesAChoisir().clear();
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
    }

}
