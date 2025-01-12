package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixTrainUsineDeWagons;

import java.util.Set;
import java.util.stream.Collectors;

public class UsineDeWagons extends Carte {

    public UsineDeWagons() {
        super("Usine de wagons", 5, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        Set<String> nomsCartesTrain = joueur.getMain().stream().filter(c -> c.getTypes().contains(TypeCarte.TRAIN)).map(Carte::getNom).collect(Collectors.toSet());
        if (!nomsCartesTrain.isEmpty()) {
            joueur.setEtatCourant(new ChoixTrainUsineDeWagons(joueur, nomsCartesTrain));
        }
    }
}