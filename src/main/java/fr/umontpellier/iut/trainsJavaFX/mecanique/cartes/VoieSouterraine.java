package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class VoieSouterraine extends CartePoseDeRails {
    public VoieSouterraine() {
        super(7, "Voie souterraine");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.ajouterEffet(EffetTour.VOIE_SOUTERRAINE);
    }
}
