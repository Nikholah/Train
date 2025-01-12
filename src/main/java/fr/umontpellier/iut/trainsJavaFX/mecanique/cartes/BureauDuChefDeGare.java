package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.suitechoix.ChoixCarteActionEnMain;

import java.util.List;
import java.util.stream.Collectors;

public class BureauDuChefDeGare extends Carte {

    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4, 0, TypeCarte.ACTION);
    }

    /**
     * Il est nécessaire d'autoriser le joueur à passer sans choisir de carte action
     * car sinon dans un scénario où il n'a que deux copies de "Bureau du chef de
     * gare" en main il est obligé de copier indéfiniment l'effet de la seconde
     * carte en main.
     */
    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        
        List<String> nomsCartesAction = joueur.getMain().stream().filter(c -> c.hasType(TypeCarte.ACTION))
                .map(Carte::getNom).collect(Collectors.toList());
        if (!nomsCartesAction.isEmpty()) {
            joueur.setEtatCourant(new ChoixCarteActionEnMain(joueur, nomsCartesAction));
        }
    }
}
