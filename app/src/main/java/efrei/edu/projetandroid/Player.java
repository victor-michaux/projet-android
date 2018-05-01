package efrei.edu.projetandroid;

/**
 * Created by Kitchen-PC on 29/04/2018.
 */

class Player {
    private static int NB_PLAYER = 0;
    private int id;
    private String nom;
    private String prenom;

    public Player(String nom, String prenom) {
        this.id = ++NB_PLAYER;
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

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
