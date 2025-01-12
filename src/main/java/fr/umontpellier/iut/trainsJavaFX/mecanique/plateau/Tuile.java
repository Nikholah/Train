package fr.umontpellier.iut.trainsJavaFX.mecanique.plateau;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.EffetTour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class Tuile {
    private final ArrayList<Tuile> voisines;
    private final ObservableSet<Joueur> rails;

    public Tuile() {
        this.voisines = new ArrayList<>();
        this.rails = FXCollections.observableSet(new HashSet<>());
    }

    // Utilisée dans les tests
    public boolean estVide() {
        return rails.isEmpty();
    }

    // Utilisée dans les tests
    public boolean hasRail(Joueur joueur) {
        return rails.contains(joueur);
    }

    public void ajouterRail(Joueur joueur) {
        rails.add(joueur);
    }

    public void ajouterGare() {
        throw new UnsupportedOperationException("Impossible d'ajouter une gare sur cette tuile");
    }

    public boolean peutPlacerGare() {
        return false;
    }

    public void ajouterVoisine(Tuile tuile) {
        voisines.add(tuile);
        tuile.voisines.add(this);
    }

    public void supprimerVoisine(Tuile tuile) {
        voisines.remove(tuile);
        tuile.voisines.remove(this);
    }

    public int getSurcout(Joueur joueur) {
        if (joueur.hasEffet(EffetTour.VOIE_SOUTERRAINE)) {
            return 0;
        }
        if (joueur.hasEffet(EffetTour.COOPERATION)) {
            return 0;
        }
        return rails.size();
    }

    /**
     * @return le nombre de points de victoire que rapporte la tuile si un joueur a
     *         posé un rail dessus
     */
    public int getNbPointsVictoire() {
        return 0;
    }

    // utilisé par les tests et l'UI
    public int getNbGares() {
        return 0;
    }

    public boolean peutPlacerRail(Joueur joueur) {
        if (rails.contains(joueur))
            return false;
        for (Tuile tuile : voisines) {
            if (tuile.rails.contains(joueur)) {
                return joueur.getArgent() >= getSurcout(joueur);
            }
        }
        return false;
    }

    public boolean peutEtrePositionDepart() {
        return true;
    }

    public Map<String, Object> dataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("rails", rails.stream().map(Joueur::getCouleur).toArray());
        int nbGares = getNbGares();
        if (nbGares > 0) {
            map.put("nbGares", nbGares);
        }
        return map;
    }

    public void onConstruitRail(Joueur joueur) {
        if (!rails.isEmpty() && !joueur.hasEffet(EffetTour.COOPERATION)) {
            joueur.recevoirFerraille();
        }
    }

    public ObservableSet<Joueur> getRails() {
        return rails;
    }
}
