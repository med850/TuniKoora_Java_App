
package Models;


public class Article {
    int idArticle;
    String titreArticle;
    String descriptionArticle;
    int idUser;
 

    public Article(){};
    
    public Article(int idArticle, String titreArticle, String descriptionArticle, int idUser ) {
        this.idArticle = idArticle;
        this.titreArticle = titreArticle;
        this.descriptionArticle = descriptionArticle;
        this.idUser = idUser;
       
        
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", titreArticle=" + titreArticle + ", descriptionArticle=" + descriptionArticle + ", idUser=" + idUser + '}';
    }
    
    
    
    
    

}