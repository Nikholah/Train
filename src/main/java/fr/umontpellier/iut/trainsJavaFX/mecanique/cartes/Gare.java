package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.GareAChoisir;

public class Gare extends Carte {

    public Gare() {
        super("Gare", 3, 0, TypeCarte.GARE);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        if (joueur.getNbJetonsGare() > 0) {
            joueur.setEtatCourant(new GareAChoisir(joueur));
        }
        joueur.recevoirFerraille();
    }
}
