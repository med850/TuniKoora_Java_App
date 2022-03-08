/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author jerby
 */
public class Livreur {

    private int id,tel,livraison_id ;
    private String nom,prenom ;

    public Livreur(){
        
    }
    
    public Livreur(int id, String nom,String prenom,int tel,int livraison_id){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel ; 
        this.livraison_id = livraison_id ;
       
                
    }

    @Override
    public String toString() {
        return "Livreur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", livraison_id=" + livraison_id + '}';
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    public int getLivraisonID() {
        return livraison_id;
    }

    public void setLivraisonID(int livraison_id) {
        this.livraison_id = livraison_id;
    }




}
