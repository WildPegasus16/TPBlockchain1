package TP_SSD;

public class Main {
    public static void main (String[] args) {
        Blockchain blockchain = new Blockchain(5);
        for (int i=1; i<blockchain.difficulte; i++) blockchain.creerNouveauBloc();
        System.out.println(blockchain);
    }
}
