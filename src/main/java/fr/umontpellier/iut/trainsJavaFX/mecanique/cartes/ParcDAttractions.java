package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixTrainParcAttraction;

import java.util.Set;
import java.util.stream.Collectors;

public class ParcDAttractions extends Carte {

    public ParcDAttractions() {
        super("Parc d'attractions", 4, 1, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);

        Set<String> nomsCartesTrain = joueur.getCartesEnJeu().stream().filter(c -> c.getTypes().contains(TypeCarte.TRAIN)).map(Carte::getNom).collect(Collectors.toSet());
        if (!nomsCartesTrain.isEmpty()) {
            joueur.setEtatCourant(new ChoixTrainParcAttraction(joueur, nomsCartesTrain));
        }
    }
}