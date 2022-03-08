
package Models;


public class Produit {

       private int id, prix, qte, user_id;
       private String nom, image, description;


     public Produit(){

    }

    public Produit(int id, int prix, int qte, int user_id, String nom, String image, String description) {
        this.id = id;
        this.prix = prix;
        this.qte = qte;
        this.user_id = user_id;
        this.nom = nom;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getPrix() {
        return prix;
    }

    public int getQte() {
        return qte;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", prix=" + prix + ", qte=" + qte + ", user_id=" + user_id + ", nom=" + nom + ", image=" + image + ", description=" + description + '}';
    }


      
}
