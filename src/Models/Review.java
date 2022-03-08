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
    int idReview;
    String commentaire;
    int idArticle;
    int idUser;

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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Review{" + "idReview=" + idReview + ", commentaire=" + commentaire + ", idArticle=" + idArticle + ", idUser=" + idUser + '}';
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }


       
}
