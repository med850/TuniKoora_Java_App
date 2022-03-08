/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author CHAOUCH KHALIL
 */
public class panier1 {
int idPanier  ;
int user_id , produit_id ;

    public panier1(int idPanier, int user_id, int produit_id) {
        this.idPanier = idPanier;
        this.user_id = user_id;
        this.produit_id = produit_id;
    }

    public panier1() {
    }

    public int getIdPanier() {
        return idPanier;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    @Override
    public String toString() {
        return "panier{" + "idPanier=" + idPanier + ", user_id=" + user_id + ", produit_id=" + produit_id + '}';
    }

  
}
