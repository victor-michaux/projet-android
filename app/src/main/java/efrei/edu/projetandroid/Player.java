package efrei.edu.projetandroid;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Kitchen-PC on 29/04/2018.
 */
@IgnoreExtraProperties
class Player {
    private String id;
    private String nom;
    private String prenom;

    Player(){

    }

    Player(String nom, String prenom) {
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

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
