package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class PoseDeRails extends CartePoseDeRails {
    public PoseDeRails() {
        super(3, "Pose de rails");
    }

    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.diminueNbjetonsRails();
    }
}
