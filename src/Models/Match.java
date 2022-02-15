/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


/**
 *
 * @author arfao
 */
public class Match {
    
    public int idM ; 
    public String localisation,arbitrePrincipale,tour  ;

    public Match(){}
    public Match(int idM,String localisation,String arbitrePrincipale,String tour){
        this.idM=idM;
        this.localisation=localisation;
        this.arbitrePrincipale=arbitrePrincipale;
        this.tour=tour;
        
    }

    public int getIdM() {
        return idM;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getArbitrePrincipale() {
        return arbitrePrincipale;
    }

    public String getTour() {
        return tour;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setArbitrePrincipale(String arbitrePrincipale) {
        this.arbitrePrincipale = arbitrePrincipale;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "Match{" + "idM=" + idM + ", localisation=" + localisation + ", arbitrePrincipale=" + arbitrePrincipale + ", tour=" + tour + '}';
    }

    
    
    
    
    
}
