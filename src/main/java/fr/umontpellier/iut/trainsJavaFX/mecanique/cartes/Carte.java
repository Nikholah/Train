package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import fr.umontpellier.iut.trainsJavaFX.ICarte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class Carte implements Comparable<Carte>, ICarte {
    private final int cout;
    private final String nom;
    private final int valeur;
    private final List<TypeCarte> types;

    public Carte(String nom, int cout, int valeur, TypeCarte... types) {
        this.cout = cout;
        this.nom = nom;
        this.valeur = valeur;
        this.types = Arrays.asList(types);
    }

    @Override
    public int getCout() {
        return cout;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public int getValeur() {
        return valeur;
    }

    public Collection<TypeCarte> getTypes() {
        return types;
    }

    public boolean hasType(TypeCarte type) {
        return types.contains(type);
    }

    /**
     * Toutes les cartes ont une méthode jouer, mais elle ne fait rien par défaut.
     * Par exemple les cartes VICTOIRE n'ont pas d'effet quand elles sont jouées.
     * Les cartes FERRAILLE n'ont un effet que si elles sont jouées au début du tour
     * pour être remise dans la réserve.
     * 
     * @param joueur
     */
    public void jouer(Joueur joueur) {
    }

    public void onAchat(Joueur joueur) {
    }

    public int getNbPointsVictoire() {
        return 0;
    }

    public boolean peutEtreJouee(Joueur joueur) {
        return true;
    }

    public boolean peutEtreAchetee(Joueur joueur) {
        return joueur.getArgent() >= cout;
    }

    @Override
    public int compareTo(Carte o) {
        if (this.cout == o.cout) {
            return this.nom.compareTo(o.nom);
        }
        return this.cout - o.cout;
    }
}