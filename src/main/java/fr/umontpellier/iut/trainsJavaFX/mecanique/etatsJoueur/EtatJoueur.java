package fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur;

import fr.umontpellier.iut.trainsJavaFX.mecanique.Jeu;
import fr.umontpellier.iut.trainsJavaFX.mecanique.Joueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJoueur.tournormal.TourNormal;

public abstract class EtatJoueur {
    protected final Joueur joueurCourant;
    protected EtatJoueur prochainEtat;

    public EtatJoueur(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
        prochainEtat = this;
    }

    public void passer() {}
    public void defausser() {}
    public void piocheChoisie() {}
    public void recevoirArgent() {}
    public void choisirTuile() {}
    public void tuileChoisie(String numTuile) {}
    public void carteEnMainChoisie(String nomCarte) {}
    public void carteEnReserveChoisie(String nomCarte) {}
    public void carteEnJeuChoisie(String carteChoisie) {}
    public void carteAChoisirChoisie(String carteChoisie) {}
    public void continuerTour() {
        if (joueurCourant.getEtatCourant() instanceof TourNormal && joueurCourant.actionsRestantAJouer().isEmpty()) { // revoir ce test
            finDuTour();
        }
    }

    public void finDuTour() {
        joueurCourant.finaliserLeTour();
        getJeu().verifieSiFinDePartie();
        getJeu().joueurSuivant();
    }

    protected Jeu getJeu() {
        return joueurCourant.getJeu();
    }
}