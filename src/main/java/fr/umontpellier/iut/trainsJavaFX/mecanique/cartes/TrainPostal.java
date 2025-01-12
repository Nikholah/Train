package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.defausse.DefausseCarteEnMainPuisArgent;

import java.util.List;
import java.util.stream.Collectors;

public class TrainPostal extends CarteTrain {

    public TrainPostal() {
        super(4, "Train postal", 1, true);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        List<String> cartesEnMain = joueur.getMain().stream().map(Carte::getNom).collect(Collectors.toList());
        if (!cartesEnMain.isEmpty()) {
            joueur.setEtatCourant(new DefausseCarteEnMainPuisArgent(joueur, cartesEnMain));
        }
    }
}