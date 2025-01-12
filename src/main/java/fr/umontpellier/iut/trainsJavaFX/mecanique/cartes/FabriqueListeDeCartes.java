package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Classe de fabrication de listes de cartes
 */
public abstract class FabriqueListeDeCartes {

    /**
     * Dictionnaire de cartes
     */
    private static final HashMap<String, Class<? extends Carte>> dicoCards = new HashMap<>();

    static {
        dicoCards.put("Aiguillage", Aiguillage.class);
        dicoCards.put("Appartement", Appartement.class);
        dicoCards.put("Atelier de maintenance", AtelierDeMaintenance.class);
        dicoCards.put("Bureau du chef de gare", BureauDuChefDeGare.class);
        dicoCards.put("Cabine du conducteur", CabineDuConducteur.class);
        dicoCards.put("Centre de contrôle", CentreDeControle.class);
        dicoCards.put("Centre de renseignements", CentreDeRenseignements.class);
        dicoCards.put("Coopération", Cooperation.class);
        dicoCards.put("Décharge", Decharge.class);
        dicoCards.put("Dépôt", Depot.class);
        dicoCards.put("Dépotoir", Depotoir.class);
        dicoCards.put("Échangeur", Echangeur.class);
        dicoCards.put("Ferraille", Ferraille.class);
        dicoCards.put("Ferronnerie", Ferronnerie.class);
        dicoCards.put("Feu de signalisation", FeuDeSignalisation.class);
        dicoCards.put("Gare", Gare.class);
        dicoCards.put("Gratte-ciel", GratteCiel.class);
        dicoCards.put("Horaires estivaux", HorairesEstivaux.class);
        dicoCards.put("Horaires temporaires", HorairesTemporaires.class);
        dicoCards.put("Immeuble", Immeuble.class);
        dicoCards.put("Parc d'attractions", ParcDAttractions.class);
        dicoCards.put("Passage en gare", PassageEnGare.class);
        dicoCards.put("Personnel de gare", PersonnelDeGare.class);
        dicoCards.put("Pont en acier", PontEnAcier.class);
        dicoCards.put("Pose de rails", PoseDeRails.class);
        dicoCards.put("Remorquage", Remorquage.class);
        dicoCards.put("Salle de contrôle", SalleDeControle.class);
        dicoCards.put("TGV", TGV.class);
        dicoCards.put("Train de marchandises", TrainDeMarchandises.class);
        dicoCards.put("Train de tourisme", TrainDeTourisme.class);
        dicoCards.put("Train direct", TrainDirect.class);
        dicoCards.put("Train express", TrainExpress.class);
        dicoCards.put("Train matinal", TrainMatinal.class);
        dicoCards.put("Train omnibus", TrainOmnibus.class);
        dicoCards.put("Train postal", TrainPostal.class);
        dicoCards.put("Tunnel", Tunnel.class);
        dicoCards.put("Usine de wagons", UsineDeWagons.class);
        dicoCards.put("Viaduc", Viaduc.class);
        dicoCards.put("Voie souterraine", VoieSouterraine.class);
    }

    /**
     * Renvoie une liste de cartes
     * 
     * @param nomCarte le nom de la carte à mettre dans la liste
     * @param nombre   le nombre de cartes de la liste
     *
     * @return une liste de cartes de même type
     */
    public static ListeDeCartes creerListeDeCartes(String nomCarte, int nombre) {
        ListeDeCartes pile = new ListeDeCartes();
        for (int i = 0; i < nombre; i++) {
            pile.add(creerCarte(nomCarte));
        }
        return pile;
    }

    public static Carte creerCarte(String nomCarte) {
        try {
            return dicoCards.get(nomCarte).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            System.err.println("Creation de cartes impossible");
            return null;
        }
    }

    public static List<String> getNomsCartesCommunes() {
        List<String> nomsCartes = new ArrayList<>();
        nomsCartes.add("Train express");
        nomsCartes.add("Train direct");
        nomsCartes.add("Pose de rails");
        nomsCartes.add("Gare");
        nomsCartes.add("Appartement");
        nomsCartes.add("Immeuble");
        nomsCartes.add("Gratte-ciel");
        nomsCartes.add("Ferraille");
        return nomsCartes;
    }

    public static Set<String> getNomsCartesPreparation() {
        Set<String> nomsCartes = new HashSet<>(dicoCards.keySet());
        getNomsCartesCommunes().forEach(nomsCartes::remove);
        nomsCartes.remove("Train omnibus");
        return nomsCartes;
    }
}
