package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class Tunnel extends Carte {
    public Tunnel() {
        super("Tunnel", 5, 0, TypeCarte.RAIL);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.incrementerRails();
        joueur.recevoirFerraille();
        joueur.ajouterEffet(EffetTour.TUNNEL);
    }
}
