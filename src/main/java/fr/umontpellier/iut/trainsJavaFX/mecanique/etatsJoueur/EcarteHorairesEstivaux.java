package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

public class EcarteHorairesEstivaux extends EtatJoueur {

    public EcarteHorairesEstivaux(Joueur joueurCourant) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Cliquez sur la carte en jeu horaires estivaux pour l'écarter, ou bien passez");
    }

    @Override
    public void carteEnJeuChoisie(String carteChoisie) {
        if (carteChoisie.equals("Horaires estivaux")) {
            Carte c = joueurCourant.getCartesEnJeu().retirer(carteChoisie);
            joueurCourant.ecarterCarte(c);
            joueurCourant.incrementerArgent(3);
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
            continuerTour();
        }
    }

    @Override
    public void passer() {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        continuerTour();
    }
}


