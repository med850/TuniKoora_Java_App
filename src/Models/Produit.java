
package Models;


public class Produit {

       private int id, prix, qte, user_id;
       private String nom, image, description;


     public Produit(){

    }


      public Produit(int id, String nom,int prix,int qte, String image, String description, int user_id){
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.image = image;
        this.description = description;
        this.user_id = user_id;
       
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom +  ", prix=" + prix + ", qte=" + qte + ", description=" + description + ", image=" + image +  ", user_id=" + user_id +  '}';
    }
      
      
      
      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getprix() {
        return prix;
    }

    public void setprix(int prix) {
        this.prix = prix;
    }

    public int getuser_id() {
        return user_id;
    }
    
    public int getqte() {
        return qte;
    }

    public void setqte(int qte) {
        this.qte = qte;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }
      
      
      
      
      
      

}
