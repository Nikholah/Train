package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixCarteCentreDeControle;

import java.util.Collection;

public class CentreDeControle extends Carte {

    public CentreDeControle() {
        super("Centre de contrôle", 3, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocherEnMain();
        Collection<String> nomsCartes = joueur.getJeu().getListeNomsCartes();
        ListeDeCartes cartesPossibles = new ListeDeCartes();
        for (String nom : nomsCartes)
            cartesPossibles.add(FabriqueListeDeCartes.creerCarte(nom));
        joueur.setCartesAChoisir(cartesPossibles);
        joueur.setEtatCourant(new ChoixCarteCentreDeControle(joueur));
    }
}