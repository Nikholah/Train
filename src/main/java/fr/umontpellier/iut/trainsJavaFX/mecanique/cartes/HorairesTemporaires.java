package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

import java.util.ArrayList;
import java.util.List;

public class HorairesTemporaires extends Carte {

    public HorairesTemporaires() {
        super("Horaires temporaires", 5, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);

        List<Carte> cartesDevoilees = new ArrayList<>();
        List<Carte> cartesTrain = new ArrayList<>();
        while (cartesTrain.size() < 2) {
            Carte c = joueur.piocher();
            if (c == null) {
                break;
            }
            if (c.getTypes().contains(TypeCarte.TRAIN)) {
                cartesTrain.add(c);
            } else {
                cartesDevoilees.add(c);
            }
        }
        joueur.defausser(cartesDevoilees);
        joueur.getMain().addAll(cartesTrain);
    }
}
