package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixPersonnelDeGare;

public class PersonnelDeGare extends Carte {

    public PersonnelDeGare() {
        super("Personnel de gare", 2, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.setEtatCourant(new ChoixPersonnelDeGare(joueur));
    }
}
