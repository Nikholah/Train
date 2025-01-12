package fr.umontpellier.iut.trainsJavaFX.vues;

import javafx.scene.image.Image;

public final class DonneesGraphiques {

    public static final double
            pourcentageEcran = .8,
            rayonPion = 18 * pourcentageEcran, // cercle représentant le pion d'un joueur
            posPion = 22.5 * pourcentageEcran;
}

class DonneesPlateau {
    private final double largeurTuile, departX, departY;
    private final Image imageVille;

    public DonneesPlateau(double largeurTuile, double departX, double departY, String nomVille) {
        this.largeurTuile = largeurTuile;
        this.departX = departX;
        this.departY = departY;
        this.imageVille = new Image("images/" + nomVille + ".jpg");
    }

    public double getLargeurTuile() {
        return largeurTuile * DonneesGraphiques.pourcentageEcran;
    }

    public double getDepartX() {
        return departX * DonneesGraphiques.pourcentageEcran;
    }

    public double getDepartY() {
        return departY * DonneesGraphiques.pourcentageEcran;
    }

    public Image getImageVille() {
        return imageVille;
    }

    public double getDepX() {
        return getLargeurTuile() * Math.sqrt(3)/2;
    }

    public double getDepY() {
        return getLargeurTuile() * 1/2;
    }

    public double getLargeurInitialePlateau() {
        return imageVille.getWidth() * DonneesGraphiques.pourcentageEcran; // 1400
    }

    public double getHauteurInitialePlateau() {
        return imageVille.getHeight() * DonneesGraphiques.pourcentageEcran; // 1000
    }

}

class DonneesPlateauBuilder {

    static DonneesPlateau PLATEAU_OSAKA = new DonneesPlateau(79, 11.5, 46.2, "OsakaSansContour");

    static DonneesPlateau PLATEAU_TOKYO = new DonneesPlateau(78, 11.5, 46.2, "TokyoSansContour");

    static DonneesPlateau PLATEAU_OSAKA_CONTOUR = new DonneesPlateau(66.7, 129, 118, "OsakaAvecContour");

    static DonneesPlateau PLATEAU_TOKYO_CONTOUR = new DonneesPlateau(66.2, 125.5, 123, "TokyoAvecContour");

}
