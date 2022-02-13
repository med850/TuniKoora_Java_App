
package Models;


public class Equipe {
    
    private int id, classement;
    private String nom, logo;
    
    
    public Equipe(){
        
    }
    
    public Equipe(int id, String nom,int classement, String logo){
        this.id = id;
        this.nom = nom;
        this.classement = classement;
        this.logo = logo;
                
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", classement=" + classement + ", nom=" + nom + ", logo=" + logo + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

        
    
    
    
    
}
