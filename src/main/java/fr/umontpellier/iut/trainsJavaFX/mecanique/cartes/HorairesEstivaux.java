package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.EcarteHorairesEstivaux;

public class HorairesEstivaux extends Carte {

    public HorairesEstivaux() {
        super("Horaires estivaux", 3, 0, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.setEtatCourant(new EcarteHorairesEstivaux(joueur));
    }
}