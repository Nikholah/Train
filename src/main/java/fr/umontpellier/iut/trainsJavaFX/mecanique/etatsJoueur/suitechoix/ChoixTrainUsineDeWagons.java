package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.TypeCarte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

import java.util.Set;
import java.util.stream.Collectors;

public class ChoixTrainUsineDeWagons extends EtatJoueur {

    Set<String> nomsCartesTrain;

    public ChoixTrainUsineDeWagons(Joueur joueurCourant, Set<String> nomsCartesTrain) {
        super(joueurCourant);
        this.nomsCartesTrain = nomsCartesTrain;
        getJeu().instructionProperty().setValue("Ecartez une carte train de votre main");
    }

    public void carteEnMainChoisie(String carteChoisie) {
        if (nomsCartesTrain.contains(carteChoisie)) {
            Carte carteEcartee = joueurCourant.getMain().retirer(carteChoisie);
            int coutCarte = carteEcartee.getCout();
            joueurCourant.ecarterCarte(carteEcartee);
            Set<String> cartesTrainPossibles = joueurCourant.getCartesDisponiblesEnReserve().stream().filter(c -> c.getTypes().contains(TypeCarte.TRAIN) && c.getCout() <= (coutCarte + 3)).map(Carte::getNom).collect(Collectors.toSet());
            if (!cartesTrainPossibles.isEmpty())
                joueurCourant.setEtatCourant(new ChoixNouvelleCarteTrain(joueurCourant, cartesTrainPossibles, coutCarte + 3));
            else
                joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
            continuerTour();
        }
    }

}
