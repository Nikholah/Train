package fr.umontpellier.iut.trainsJavaFX.mecanique;

import fr.umontpellier.iut.trainsJavaFX.IJeu;
import fr.umontpellier.iut.trainsJavaFX.IJoueur;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.Carte;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.FabriqueListeDeCartes;
import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.ListeDeCartes;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJeu.EtatJeu;
import fr.umontpellier.iut.trainsJavaFX.mecanique.etatsJeu.InitialisationJoueurs;
import fr.umontpellier.iut.trainsJavaFX.mecanique.plateau.Plateau;
import fr.umontpellier.iut.trainsJavaFX.mecanique.plateau.Tuile;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;

import java.util.*;

public class Jeu implements IJeu {
    /**
     * Liste des joueurs
     */
    private final ArrayList<Joueur> joueurs;
    /**
     * Joueur qui joue le tour courant
     */
    private final ObjectProperty<IJoueur> joueurCourant;
    /**
     * Dictionnaire des piles de réserve.
     *
     * Associe à un nom de carte la liste des cartes correspondantes disponibles
     * dans la réserve
     */
    private final Map<String, ListeDeCartes> reserve;
    /**
     * Liste des cartes écartées par les joueurs
     */
    private final ListeDeCartes cartesEcartees;
    /**
     * Nom de la ville du plateau (pour afficher le plateau dans l'interface
     * graphique)
     */
    private final String nomVille;
    /**
     * Tuiles du plateau de jeu (indexées dans l'ordre de lecture)
     */
    private final List<Tuile> tuiles;
    /**
     * Nombre de jetons Gare restant (non placés sur les tuiles)
     */
    private int nbJetonsGare;

    /**
     * Instruction affichée au joueur courant
     */
    private final ObjectProperty<String> instruction;

    /**
     * Permet de savoir si la partie est terminée
     */
    private final BooleanProperty finDePartie;

    Map<String, BooleanProperty> piochesReserveVidesProperties;
    Map<String, IntegerProperty> taillesPiochesReserveProperties;

    private final Plateau plateau;

    /**
     * Constructeur de la classe Jeu
     *
     * @param nomsJoueurs       noms des joueurs de la partie
     * @param cartesPreparation noms des cartes à utiliser pour créer les piles de
     *                          réserve (autres que les piles de cartes communes)
     * @param plateau           choix du plateau ({@code Plateau.OSAKA} ou
     *                          {@code Plateau.TOKYO})
     */
    public Jeu(String[] nomsJoueurs, String[] cartesPreparation, Plateau plateau) {

        this.plateau = plateau;
        // préparation du plateau
        this.nomVille = plateau.getNomVille();
        this.tuiles = plateau.makeTuiles();

        this.nbJetonsGare = 30;
        this.cartesEcartees = new ListeDeCartes();

        // construction des piles de réserve
        this.reserve = new HashMap<>();

        // ajouter les cartes communes et les cartes de préparation
        creerCartesCommunes();
        for (String nomCarte : cartesPreparation) {
            reserve.put(nomCarte, FabriqueListeDeCartes.creerListeDeCartes(nomCarte, 10));
        }

        // création des joueurs
        this.joueurs = new ArrayList<>();
        ArrayList<CouleurJoueur> couleurs = new ArrayList<>(List.of(CouleurJoueur.values()));
        Collections.shuffle(couleurs);
        for (String nomJoueur : nomsJoueurs) {
            this.joueurs.add(new Joueur(this, nomJoueur, couleurs.remove(0)));
        }

        instruction = new SimpleObjectProperty<>("Début de partie");
        joueurCourant = new SimpleObjectProperty<>();
        finDePartie = new SimpleBooleanProperty(false);
        piochesReserveVidesProperties = new HashMap<>();
        for (String nomCarte : reserve.keySet()) {
            BooleanProperty piocheVideProperty = new SimpleBooleanProperty();
            piocheVideProperty.bind(Bindings.isEmpty(reserve.get(nomCarte)));
            piochesReserveVidesProperties.put(nomCarte, piocheVideProperty);
        }
        taillesPiochesReserveProperties = new HashMap<>();
        for (String nomCarte : reserve.keySet()) {
            IntegerProperty taillePiocheProperty = new SimpleIntegerProperty();
            taillePiocheProperty.bind(reserve.get(nomCarte).sizeProperty());
            taillesPiochesReserveProperties.put(nomCarte, taillePiocheProperty);
        }
        joueurCourant.setValue(joueurs.get(joueurs.size() - 1));
    }

    public Joueur getJoueurCourant() {
        return (Joueur) joueurCourant.getValue();
    }

    public Tuile getTuile(int index) {
        return tuiles.get(index);
    }

    public int getNbJetonsGare() {
        return nbJetonsGare;
    }

    /**
     * Renvoie un ensemble de tous les noms des cartes en jeu.
     *
     * Cette liste contient les noms des cartes qui étaient disponibles dans les
     * piles la réserve et "Train omnibus" que les joueurs ont en main en début de
     * partie mais ne correspond pas à une pile de la réserve.
     */
    public Set<String> getListeNomsCartes() {
        Set<String> noms = new HashSet<>(reserve.keySet());
        noms.add("Train omnibus");
        return noms;
    }

    /**
     * Renvoie une liste contenant la carte du dessus de chacune des piles non vides
     * de la réserve
     */
    public List<Carte> getCartesDisponiblesEnReserve() {
        List<Carte> cartes = new ArrayList<>();
        for (ListeDeCartes pileReserve : reserve.values()) {
            if (!pileReserve.isEmpty()) {
                cartes.add(pileReserve.get(0));
            }
        }
        return cartes;
    }

    /**
     * Construit les piles de réserve pour les cartes communes
     */
    private void creerCartesCommunes() {
        reserve.put("Train express", FabriqueListeDeCartes.creerListeDeCartes("Train express", 1));
        reserve.put("Train direct", FabriqueListeDeCartes.creerListeDeCartes("Train direct", 1));
        reserve.put("Pose de rails", FabriqueListeDeCartes.creerListeDeCartes("Pose de rails", 20));
        reserve.put("Gare", FabriqueListeDeCartes.creerListeDeCartes("Gare", 20));
        reserve.put("Appartement", FabriqueListeDeCartes.creerListeDeCartes("Appartement", 1));
        reserve.put("Immeuble", FabriqueListeDeCartes.creerListeDeCartes("Immeuble", 1));
        reserve.put("Gratte-ciel", FabriqueListeDeCartes.creerListeDeCartes("Gratte-ciel", 1));
        reserve.put("Ferraille", FabriqueListeDeCartes.creerListeDeCartes("Ferraille", 70));
        reserve.put("Feu de signalisation", FabriqueListeDeCartes.creerListeDeCartes("Feu de signalisation", 1));
        reserve.put("Passage en gare", FabriqueListeDeCartes.creerListeDeCartes("Passage en gare", 1));
        reserve.put("Cabine du conducteur", FabriqueListeDeCartes.creerListeDeCartes("Cabine du conducteur", 1));
    }

    /**
     * Renvoie une carte prise dans la réserve
     *
     * @param nomCarte nom de la carte à prendre
     * @return la carte retirée de la réserve ou `null` si aucune disponible (ou si
     *         le nom de carte n'existe pas dans la réserve)
     */
    public Carte prendreDansLaReserve(String nomCarte) {
        if (!reserve.containsKey(nomCarte)) {
            return null;
        }

        ListeDeCartes pile = reserve.get(nomCarte);
        if (pile.isEmpty()) {
            return null;
        }
        return pile.remove(0);
    }

    /**
     * Modifie l'attribut {@code joueurCourant} pour passer au joueur suivant dans
     * l'ordre du tableau {@code joueurs} (le tableau est considéré circulairement)
     */
    public void passeAuJoueurSuivant() {
        int i = joueurs.indexOf(joueurCourant.getValue());
        i = (i + 1) % joueurs.size();
        joueurCourant.setValue(joueurs.get(i));
    }

    /**
     * @return {@code true} si la partie est finie, {@code false} sinon
     */
    public void verifieSiFinDePartie() {
        // condition 1: 4 piles de cartes de la réserve sont vides - à l'exception des
        // Ferraille
        int nbPilesVides = 0;
        for (String nomCarte : reserve.keySet()) {
            if (!nomCarte.equals("Ferraille") && reserve.get(nomCarte).isEmpty()) {
                nbPilesVides += 1;
            }
        }
        if (nbPilesVides >= 4) {
            finDePartie.setValue(true);
        }
        // condition 2: un joueur a utilisé tous ses jetons Rails
        for (Joueur joueur : joueurs) {
            if (joueur.getNbJetonsRails() == 0) {
                finDePartie.setValue(true);
            }
        }
        // condition 3: tous les jetons Gare ont été placés sur le plateau
        if (nbJetonsGare == 0) {
            finDePartie.setValue(true);
        }
    }

    /**
     * Démarre la partie et exécute les tours des joueurs jusqu'à ce que la partie
     * soit terminée
     */
    public void run() {
        joueurCourant.setValue(joueurs.get(0));
        etatCourantDuJeu = new InitialisationJoueurs(this);
    }

    public void joueurSuivant() {
        passeAuJoueurSuivant();
        getJoueurCourant().jouerTour();
    }

    public void initialiserJoueurSuivant() {
        getEtatCourant().prochainTour();
        passeAuJoueurSuivant();
        getJoueurCourant().choisirPositionDepart();
    }

    public void remettreCarteDansLaReserve(Carte c) {
        reserve.get(c.getNom()).add(c);
    }

    public void ecarterCarte(Carte c) {
        cartesEcartees.add(c);
    }

    public List<String> getPositionsDepartDisponibles() {
        List<String> positions = new ArrayList<>();
        for (int i = 0; i < tuiles.size(); i++) {
            if (tuiles.get(i).peutEtrePositionDepart() && tuiles.get(i).estVide()) {
                positions.add(String.valueOf(i));
            }
        }
        return positions;
    }

    public List<String> getPositionsGareDisponibles() {
        List<String> positions = new ArrayList<>();
        if (nbJetonsGare <= 0) {
            // plus aucun jeton Gare disponible
            return positions;
        }

        for (int i = 0; i < tuiles.size(); i++) {
            if (tuiles.get(i).peutPlacerGare()) {
                positions.add(String.valueOf(i));
            }
        }
        return positions;
    }

    public void ajouterGare(int i) {
        tuiles.get(i).ajouterGare();
        nbJetonsGare--;
    }

    public Collection<String> getPositionsRailDisponibles(Joueur joueur) {
        Collection<String> positions = new HashSet<>();
        for (int i = 0; i < tuiles.size(); i++) {
            if (tuiles.get(i).peutPlacerRail(joueur)) {
                positions.add(String.valueOf(i));
            }
        }
        return positions;
    }

    /**
     * Gestion des phases (états) du jeu
     */
    private EtatJeu etatCourantDuJeu;

    public void setEtatCourant(EtatJeu etatCourantDuJeu) {
        this.etatCourantDuJeu = etatCourantDuJeu;
    }

    public EtatJeu getEtatCourant() {
        return etatCourantDuJeu;
    }


    /**
     * Publication de données du jeu
     */

    @Override
    public String getNomVille() {
        return nomVille;
    }

    @Override
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Publication des propriétés
     */
    @Override
    public BooleanProperty finDePartieProperty() {
        return finDePartie;
    }

    @Override
    public ObjectProperty<String> instructionProperty() {
        return instruction;
    }

    @Override
    public ObjectProperty<IJoueur> joueurCourantProperty() {
        return joueurCourant;
    }

    @Override
    public Map<String, IntegerProperty> getTaillesPilesReserveProperties() {
        return taillesPiochesReserveProperties;
    }

    @Override
    public List<? extends IJoueur> getJoueurs() {
        return joueurs;
    }

    @Override
    public List<Tuile> getTuiles() {
        return tuiles;
    }

    @Override
    public ListeDeCartes getReserve() {
        ListeDeCartes cartesDeHautDePile = new ListeDeCartes();
        for (ListeDeCartes l: reserve.values())
            cartesDeHautDePile.add(l.get(0));
        return cartesDeHautDePile;
    }

    /**
     * Gestionnaires des demandes du joueur
     */
    @Override
    public void passerAEteChoisi() {
        Joueur leJoueur = ((Joueur) joueurCourant.getValue());
        leJoueur.getEtatCourant().passer();
    }

    @Override
    public void uneTuileAEteChoisie(String numTuile) {
        Joueur leJoueur = ((Joueur) joueurCourant.getValue());
        leJoueur.getEtatCourant().tuileChoisie(numTuile);
    }

    @Override
    public void uneCarteDeLaReserveEstAchetee(String carteEnReserve) {
        Joueur leJoueur = ((Joueur) joueurCourant.getValue());
        leJoueur.getEtatCourant().carteEnReserveChoisie(carteEnReserve);
    }

    @Override
    public void uneCarteAChoisirChoisie(String carteEnReserve) {
        Joueur leJoueur = ((Joueur) joueurCourant.getValue());
        leJoueur.getEtatCourant().carteAChoisirChoisie(carteEnReserve);
    }
}