
package Models;


public class Joueur {

       private int id, tel, equipe_id;
       private String nom, prenom, image, poste;


     public Joueur(){

    }


      public Joueur(int id, String nom,String prenom, String image, String poste, int tel, int equipe_id){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.poste = poste;
        this.tel = tel;
        this.equipe_id = equipe_id;
       
    }

    @Override
    public String toString() {
        return "Joueur{" + "id=" + id + ", tel=" + tel + ", equipe_id=" + equipe_id + ", nom=" + nom + ", prenom=" + prenom + ", image=" + image + ", poste=" + poste + '}';
    }
      
      
      
      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
      
      
      
      
      
      

}
