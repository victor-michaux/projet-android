package efrei.edu.projetandroid.game;

import java.io.Serializable;

/**
 * Created by Kitchen-PC on 29/04/2018.
 */
public class Player implements Serializable {
    private String nom;
    private String prenom;

    public Player(){

    }

    public Player(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
