package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

public class ChoixCarteCentreDeControle extends EtatJoueur {
    public ChoixCarteCentreDeControle(Joueur joueur) {
        super(joueur);
        getJeu().instructionProperty().setValue("Nommez la première carte de votre deck");
    }

    public void carteAChoisirChoisie(String carteChoisie) {
        Carte carteDevoilee = joueurCourant.piocher();
        if (carteDevoilee.getNom().equals(carteChoisie)) {
            joueurCourant.ajouterMain(carteDevoilee);
        } else {
            joueurCourant.mettreSurPioche(carteDevoilee);
        }
        joueurCourant.cartesAChoisir().clear();
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
    }

}
