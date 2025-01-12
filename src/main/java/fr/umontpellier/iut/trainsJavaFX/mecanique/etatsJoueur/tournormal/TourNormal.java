package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;

public class TourNormal extends EtatJoueur {

    public TourNormal(Joueur joueurCourant) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Choisissez une action ou passez");
    }

    public void passer() {
        finDuTour();
    }

    public void tuileChoisie(String nomTuile) {
        prochainEtat = new RailAPoser(joueurCourant);
        prochainEtat.tuileChoisie(nomTuile);
    }

    public void carteEnReserveChoisie(String carteEnReserve) {
        prochainEtat = new AchatCarte(joueurCourant);
        prochainEtat.carteEnReserveChoisie(carteEnReserve);
    }

    public void carteEnMainChoisie(String carteEnMain) {
        prochainEtat = new CarteEnMainChoisie(joueurCourant);
        prochainEtat.carteEnMainChoisie(carteEnMain);
    }
}
