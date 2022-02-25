
package Models;


public class Equipe {
    
    private int id, classement;
    private String nom;
    
    
    public Equipe(){
        
    }
    
    public Equipe(int id, String nom,int classement){
        this.id = id;
        this.nom = nom;
        this.classement = classement;
       
                
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", classement=" + classement + ", nom=" + nom + '}';
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
}
