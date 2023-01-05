package TP_SSD;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date; //Nécessaire pour que notre timestamp corresponde à la date de création du bloc

public class Bloc {
    protected int index;
    protected String hash;
    protected String hashPrec;
    protected String données;
    protected long timeStamp;
    protected int nonce;

    public Bloc(int index, String hashPrec, String données, long timeStamp) {
        this.index = index;
        this.hashPrec = hashPrec;
        this.données = données;
        this.timeStamp = timeStamp;
        this.hash = calcHash(this);
        nonce = 0;
    }

    public int getIndex() {
        return index;
    }

    public String getHash() {
        return hash;
    }

    public String getHashPrec() {
        return hashPrec;
    }

    public String getDonnées() {
        return données;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String calcHash(Bloc bloc) {
        if (bloc!=null) {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
            String aHasher = hashPrec + timeStamp + nonce + données;
            byte[] bytes = digest.digest(aHasher.getBytes(StandardCharsets.UTF_8));
            StringBuilder aRetourner = new StringBuilder(); //On utilise StringBuilder plutôt que StringBuffer car plus rapide
            for (byte b: bytes) aRetourner.append(String.format("%02x",b)); //Nous donne à chaque fois 0 et le bit qu'on souhaite insérer (exemple: '02')
            return aRetourner.toString();
        }
        return null;
    }

    public String toString() { //Permet de retourner notre bloc comme une chaine de caractères - Redéfinition de la fonction toString
        return "N° de bloc: "+index+"\nHash: "+hash+"\nHash precedent: "+hashPrec+"\nDate: "+(new Date(timeStamp))+"\nContenu du bloc: "+données+"\n";
    }


    public void mineBloc (int i) {
        nonce = 0;
        while (!(getHash().substring(0,i).equals(GestionZeros.gererZeros(i)))) {
            nonce++;
            hash = calcHash(this);
        }
    }
}
