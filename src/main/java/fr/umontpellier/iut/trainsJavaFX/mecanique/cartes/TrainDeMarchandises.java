package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.debuttour.RemiseFerraille;

import java.util.List;
import java.util.stream.Collectors;

public class TrainDeMarchandises extends CarteTrain {

    public TrainDeMarchandises() {
        super(4, "Train de marchandises", 1, true);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        List<String> cartesFerraille = joueur.getMain().stream().filter(c -> c.hasType(TypeCarte.FERRAILLE))
                .map(Carte::getNom).collect(Collectors.toList());
        if (!cartesFerraille.isEmpty()) {
            joueur.setEtatCourant(new RemiseFerraille(joueur, cartesFerraille));
        }
    }
}

