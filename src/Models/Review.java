/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author wassim
 */
public class Review {
    private int idReview;
    private String commentaire;
    private int idArticle;
    private int idUser;

    public Review(){};
    
    public Review(int idReview, String commentaire, int idArticle, int idUser) {
        this.idReview = idReview;
        this.commentaire = commentaire;
        this.idArticle = idArticle;
        this.idUser = idUser;
    }

    public int getIdReview() {
        return idReview;
    }


    public String getcommentaire() {
        return commentaire;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public void setcommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Review{" + "idReview=" + idReview + ", commentaire=" + commentaire + ", idArticle=" + idArticle + ", idUser=" + idUser + '}';
    }


    
}
