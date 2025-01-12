package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.EffetTour;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.AchatAvecEffetTrainMatinal;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;

import java.util.List;

public class AchatCarte extends EtatJoueur {

    public AchatCarte(Joueur joueurCourant) {
        super(joueurCourant);
    }

    public void carteEnReserveChoisie(String carteDemandee) {
        List<String> choixPossibles = joueurCourant.getCartesAchatPossibles().stream().toList();
        if (!choixPossibles.isEmpty() && choixPossibles.contains(carteDemandee)) {
            Carte carteAchetee = joueurCourant.acheterCarte(carteDemandee);
            joueurCourant.incrementerScore(carteAchetee.getNbPointsVictoire());
            if (joueurCourant.hasEffet(EffetTour.TRAIN_MATINAL))
                joueurCourant.setEtatCourant(new AchatAvecEffetTrainMatinal(joueurCourant, carteAchetee));
            else {
                joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
                continuerTour();
            }
        } else {
            getJeu().instructionProperty().setValue("Vous n'avez pas assez d'argent pour acheter cette carte");
            if (!getJeu().getCartesDisponiblesEnReserve().stream()
                    .map(Carte::getNom)
                    .toList().contains(carteDemandee))
                getJeu().instructionProperty().setValue("La réserve de cette carte est vide");
        }
    }
}
