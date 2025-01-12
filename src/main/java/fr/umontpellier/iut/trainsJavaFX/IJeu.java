package fr.umontpellier.iut.trainsJavaFX;

import fr.umontpellier.iut.trainsJavaFX.mecanique.cartes.ListeDeCartes;
import fr.umontpellier.iut.trainsJavaFX.mecanique.plateau.Plateau;
import fr.umontpellier.iut.trainsJavaFX.mecanique.plateau.Tuile;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

import java.util.List;
import java.util.Map;

public interface IJeu {

    BooleanProperty finDePartieProperty();
    ObjectProperty<String> instructionProperty();
    ObjectProperty<IJoueur> joueurCourantProperty();
    Map<String, IntegerProperty> getTaillesPilesReserveProperties();
    List<? extends IJoueur> getJoueurs();
    List<Tuile> getTuiles();
    ListeDeCartes getReserve();

    Plateau getPlateau();
    String getNomVille();

    void passerAEteChoisi();
    void uneTuileAEteChoisie(String idTuile);
    void uneCarteDeLaReserveEstAchetee(String carteEnReserve);
    void uneCarteAChoisirChoisie(String carteEnReserve);

}