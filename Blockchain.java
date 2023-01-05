package TP_SSD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Blockchain {
    protected List<Bloc> blocs = new ArrayList<>(); //On définit notre blockchain comme une liste de blocs
    protected int difficulte;

    public Blockchain (int difficulte) {
        this.difficulte = difficulte;
        Bloc b = new Bloc (1,null,"Test",new Date().getTime());
        b.mineBloc(difficulte);
        blocs.add(b);
    }

    public int getDifficulte() {
        return difficulte;
    }

    public Bloc getDernierBloc() {
        return blocs.get(blocs.size()-1); //Le dernier bloc de la blockchain est le dernier bloc de l'arraylist
    }

    public void creerNouveauBloc() {
        if (blocs.size() > 0) { //On ne souhaite pas utiliser cette fonction si aucun bloc n'a été créé au préalable -> Plus simple pour récupérer le dernier hash
            Scanner sc = new Scanner(System.in);
            System.out.println("Quelles sont les données que vous souhaitez insérer pour le bloc n°"+(getDernierBloc().getIndex()+1)+" ?");
            String aInserer = sc.nextLine(); //Plus simple pour l'utilisateur d'avoir une interface avec un scanner que de passer par un paramètre - cependant plus long
            Bloc dernierBloc = getDernierBloc(); //Permet de ne pas vérifier la validité d'un bloc à chaque fois - pratique pour l'incrémentation d'index et récupération du hashPrécédent et permet d'éviter l'insertion d'un mauvais bloc dans la blockchain
            Bloc b = new Bloc(dernierBloc.getIndex() + 1, dernierBloc.getHash(), aInserer, new Date().getTime());
            b.mineBloc(difficulte);
            blocs.add(b);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Bloc b : blocs) {
            builder.append(b).append("\n");
        }
        return builder.toString(); //On retourne notre blockchain comme une chaine de caractères
    }

}

