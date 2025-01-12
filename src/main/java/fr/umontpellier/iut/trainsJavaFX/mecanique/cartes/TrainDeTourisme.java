package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

public class TrainDeTourisme extends CarteTrain {

    public TrainDeTourisme() {
        super(4, "Train de tourisme", 1, true);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.incrementerScore();
    }
    
}
