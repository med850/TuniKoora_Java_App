/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author jerby
 */
public class Livraison {
    
    private int id, user_id ;
    private String ref, localisation, etat ;
    
    
    
    
    public Livraison(){
        
    }

    public Livraison(int id, int user_id, String ref, String localisation, String etat) {
        this.id = id;
        this.user_id = user_id;
        this.ref = ref;
        this.localisation = localisation;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getRef() {
        return ref;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
    
}
