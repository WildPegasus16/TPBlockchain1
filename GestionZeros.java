package TP_SSD;

public class GestionZeros { //Création de cette classe pour utiliser le toString() et non celui qui a été redéfini dans bloc
    public static String gererZeros(int longueur) {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<longueur; i++) builder.append('0');
        return builder.toString();
    }

}


