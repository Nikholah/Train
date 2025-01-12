package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.initialisation;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJeu.PartieEnCours;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;

import java.util.List;

public class TuileInitiale extends EtatJoueur {

    public TuileInitiale(Joueur joueurCourant) {
        super(joueurCourant);
    }

    public void tuileChoisie(String numTuile) {
        List<String> choixPossibles = getJeu().getPositionsDepartDisponibles().stream().toList();
        if (!choixPossibles.isEmpty() && choixPossibles.contains(numTuile)) {
            joueurCourant.nbJetonsRailsProperty().setValue(joueurCourant.nbJetonsRailsProperty().getValue() - 1);
            getJeu().getTuile(Integer.parseInt(numTuile)).ajouterRail(joueurCourant);
            finDuTour();
        }
    }

    @Override
    public void finDuTour() {
        if (getJeu().getEtatCourant().finPhase()) {
            getJeu().setEtatCourant(new PartieEnCours(getJeu()));
            getJeu().getEtatCourant().demarrerPartie();
        } else {
            getJeu().initialiserJoueurSuivant();
        }
    }

}
