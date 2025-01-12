package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.defausse.DefausseCarteEnMainPuisPioche;

import java.util.List;
import java.util.stream.Collectors;

public class CabineDuConducteur extends Carte {

    public CabineDuConducteur() {
        super("Cabine du conducteur", 2, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);

        List<String> cartesEnMain = joueur.getMain().stream().map(Carte::getNom).collect(Collectors.toList());
        if (!cartesEnMain.isEmpty()) {
            joueur.setEtatCourant(new DefausseCarteEnMainPuisPioche(joueur, cartesEnMain));
        }
    }
}
