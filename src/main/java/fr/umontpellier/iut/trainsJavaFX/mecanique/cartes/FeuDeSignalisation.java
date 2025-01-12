package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixFeuDeSignalisation;
import javafx.collections.FXCollections;

public class FeuDeSignalisation extends Carte {

    public FeuDeSignalisation() {
        super("Feu de signalisation", 2, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocherEnMain();
        Carte c = joueur.piocher();
        if (c != null) {
            joueur.setCartesAChoisir(new ListeDeCartes(FXCollections.observableArrayList(c)));
            joueur.setEtatCourant(new ChoixFeuDeSignalisation(joueur));
        }
    }
}