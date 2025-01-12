package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.defausse.DefausseExactementNCartesEnMain;

import java.util.List;
import java.util.stream.Collectors;

public class Depot extends Carte {
    public Depot() {
        super("Dépôt", 3, 1, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocherEnMain(2);
        List<String> cartesEnMain = joueur.getMain().stream().map(Carte::getNom).collect(Collectors.toList());
        if (!cartesEnMain.isEmpty()) {
            joueur.setEtatCourant(new DefausseExactementNCartesEnMain(joueur, cartesEnMain, 2));
        }
    }
}

