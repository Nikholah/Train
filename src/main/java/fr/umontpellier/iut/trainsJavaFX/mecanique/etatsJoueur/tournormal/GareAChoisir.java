package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;

import java.util.List;

public class GareAChoisir extends EtatJoueur {

    public GareAChoisir(Joueur joueurCourant) {
        super(joueurCourant);
        getJeu().instructionProperty().setValue("Choisissez une gare");
    }

    public void tuileChoisie(String numTuile) {
        List<String> choixPossibles = joueurCourant.getPositionsGareDisponibles().stream().toList();
        if (!choixPossibles.isEmpty() && choixPossibles.contains(numTuile)) {
            joueurCourant.ajouterGare(Integer.parseInt(numTuile));
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
            continuerTour();
        }
    }
}
