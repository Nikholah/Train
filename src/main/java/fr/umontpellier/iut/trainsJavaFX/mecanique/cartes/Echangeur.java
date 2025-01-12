package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixTrainEchangeur;

import java.util.Set;
import java.util.stream.Collectors;

public class Echangeur extends Carte {

    public Echangeur() {
        super("Échangeur", 3, 1, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        Set<String> nomsCartesTrain = joueur.getCartesEnJeu().stream().filter(c -> c.getTypes().contains(TypeCarte.TRAIN)).map(Carte::getNom).collect(Collectors.toSet());
        if (!nomsCartesTrain.isEmpty()) {
            joueur.setEtatCourant(new ChoixTrainEchangeur(joueur, nomsCartesTrain));
        }
    }
}
