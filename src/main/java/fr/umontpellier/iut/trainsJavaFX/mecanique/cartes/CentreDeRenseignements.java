package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixCarteCentreDeRenseignements;

public class CentreDeRenseignements extends Carte {

    public CentreDeRenseignements() {
        super("Centre de renseignements", 4, 1, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        ListeDeCartes cartesDevoilees = new ListeDeCartes(joueur.piocher(4));
        joueur.setCartesAChoisir(cartesDevoilees);
        joueur.setEtatCourant(new ChoixCarteCentreDeRenseignements(joueur));
    }
}