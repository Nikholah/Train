package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.debuttour;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

public class DebutTour extends EtatJoueur {

    public DebutTour(Joueur joueurCourant) {
        super(joueurCourant);
        String debutInstruction = "C";
        if (!joueurCourant.getMain().stream().filter(s -> s.getNom().equals("Ferraille")).toList().isEmpty())
            debutInstruction = "Recyclez votre ferraille, c";
        getJeu().instructionProperty().setValue(debutInstruction + "hoisissez une action ou passez");
    }

    public void passer() {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        joueurCourant.getEtatCourant().passer();
    }

    public void tuileChoisie(String nomTuile) {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        joueurCourant.getEtatCourant().tuileChoisie(nomTuile);
    }

    public void carteEnReserveChoisie(String carteEnReserve) {
        joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
        joueurCourant.getEtatCourant().carteEnReserveChoisie(carteEnReserve);
    }

    public void carteEnMainChoisie(String carteEnMain) {
        if (carteEnMain.equals("Ferraille")) {
            joueurCourant.recyclerFerraille();
            finDuTour();
        } else {
            joueurCourant.setEtatCourant(new TourNormal(joueurCourant));
            joueurCourant.getEtatCourant().carteEnMainChoisie(carteEnMain);
        }
    }

}
