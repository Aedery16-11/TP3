package sio.tp3.module;

public class Tache {
    private String nomtache;
    private String nomDeveloppeur;
    private boolean estTerminee;

    public Tache(String nomtache, String nomDeveloppeur, boolean estTerminee) {
        this.nomtache = nomtache;
        this.nomDeveloppeur = nomDeveloppeur;
        this.estTerminee = estTerminee;
    }

    public String getNomtache() {
        return nomtache;
    }

    public void setNomtache(String nomtache) {
        this.nomtache = nomtache;
    }

    public String getNomDeveloppeur() {
        return nomDeveloppeur;
    }

    public void setNomDeveloppeur(String nomDeveloppeur) {
        this.nomDeveloppeur = nomDeveloppeur;
    }

    public boolean isEstTerminee() {
        return estTerminee;
    }

    public void setEstTerminee(boolean estTerminee) {
        this.estTerminee = estTerminee;
    }
}
