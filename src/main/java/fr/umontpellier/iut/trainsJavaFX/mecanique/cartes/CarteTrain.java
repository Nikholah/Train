package fr.umontpellier.iut.trainsJavaFX.mecanique.cartes;

public class CarteTrain extends Carte {

    public CarteTrain(int cout, String nom, int valeur) {
        this(cout, nom, valeur, false);
    }

    public CarteTrain(int cout, String nom, int valeur, boolean estAction) {
        super(
                nom,
                cout,
                valeur,
                estAction ? new TypeCarte[] { TypeCarte.TRAIN, TypeCarte.ACTION }
                        : new TypeCarte[] { TypeCarte.TRAIN });
    }
}
