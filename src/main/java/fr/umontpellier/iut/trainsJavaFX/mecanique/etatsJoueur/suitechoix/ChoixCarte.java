package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.List;

public abstract class ChoixCarte extends EtatJoueur {

    List<String> nomsCartesAChoisir;

    public ChoixCarte(Joueur joueurCourant) {
        super(joueurCourant);
        this.nomsCartesAChoisir = joueurCourant.cartesAChoisir().stream().map(Carte::getNom).toList();
    }

    public void carteAChoisirChoisie(String carteChoisie) {
        if (nomsCartesAChoisir.contains(carteChoisie)) {
            actionCarteChoisie(carteChoisie);
        if (joueurCourant.cartesAChoisir().isEmpty())
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        continuerTour();
        }
    }

    public abstract void actionCarteChoisie(String carteChoisie);

}
