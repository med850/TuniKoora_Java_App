/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author arfao
 */
public class Participation {
    
  public int id;	
  public String match_id ,equipe_id ;	
  public Date dateP;
  
  public Participation(){}
  public Participation(int id,String match_id,String equipe_id,Date dateP){
      this.id=id;
      this.match_id=match_id;
      this.equipe_id=equipe_id;
      this.dateP=dateP;    
  }

    public int getId() {
        return id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public String getEquipe_id() {
        return equipe_id;
    }

    public Date getDateP() {
        return dateP;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public void setEquipe_id(String equipe_id) {
        this.equipe_id = equipe_id;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }
  
  
    
}
