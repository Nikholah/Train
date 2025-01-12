package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

public class AchatAvecEffetTrainMatinal extends EtatJoueur {
    Carte carteAchetee;

    public AchatAvecEffetTrainMatinal(Joueur joueurCourant, Carte carteAchetee) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Vous pouvez mettre cette carte sur votre deck");
        this.carteAchetee = carteAchetee;
    }

    public void piocheChoisie() {
       joueurCourant.placerCarteAcheteeSurDeck(carteAchetee);
       joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
       continuerTour();
    }

    public void passer() {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        joueurCourant.getEtatCourant().passer();
    }

    public void tuileChoisie(String nomTuile) {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        joueurCourant.getEtatCourant().tuileChoisie(nomTuile);
    }

    public void carteEnReserveChoisie(String carteEnReserve) {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        joueurCourant.getEtatCourant().carteEnReserveChoisie(carteEnReserve);
    }

    public void carteEnMainChoisie(String carteEnMain) {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        joueurCourant.getEtatCourant().carteEnMainChoisie(carteEnMain);
    }
}
