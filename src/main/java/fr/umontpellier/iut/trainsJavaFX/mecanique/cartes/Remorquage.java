package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixCarteRemorquage;
import javafx.collections.FXCollections;

import java.util.Set;
import java.util.stream.Collectors;

public class Remorquage extends Carte {

    public Remorquage() {
        super("Remorquage", 3, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        Set<Carte> setCartesTrain = joueur.getDefausse()
                .stream()
                .filter(c -> c.getTypes().contains(TypeCarte.TRAIN))
                .collect(Collectors.toSet());
        ListeDeCartes cartesTrain = new ListeDeCartes(FXCollections.observableArrayList(setCartesTrain));
        joueur.setCartesAChoisir(cartesTrain);
        if (!cartesTrain.isEmpty()) {
            joueur.setEtatCourant(new ChoixCarteRemorquage(joueur));
        }
    }
}