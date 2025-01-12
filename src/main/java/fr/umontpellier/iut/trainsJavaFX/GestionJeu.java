package fr.umontpellier.iut.trainsJavaFX;

public class GestionJeu {

    private static IJeu jeu;

    public static void setJeu(IJeu jeu) {
        if (jeu != null)
            GestionJeu.jeu = jeu;
    }

    public static IJeu getJeu() {
        return jeu;
    }
}
