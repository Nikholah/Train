package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;

import java.util.List;

public class RailAPoser extends EtatJoueur {

    public RailAPoser(Joueur joueurCourant) {
        super(joueurCourant);
    }

    public void tuileChoisie(String numTuile) {
        List<String> choixPossibles = joueurCourant.getRailsJouables().stream().toList();
        if (!choixPossibles.isEmpty() && choixPossibles.contains(numTuile)) {
            joueurCourant.construireRail(Integer.parseInt(numTuile));
            continuerTour();
        }
    }
}
