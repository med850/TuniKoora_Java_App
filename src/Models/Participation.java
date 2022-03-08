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
  public int match_id ,equipe_id ;	
  
  public Participation(){}
  public Participation(int id,int match_id,int equipe_id){
      this.id=id;
      this.match_id=match_id;
      this.equipe_id=equipe_id;
  }

    public int getId() {
        return id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", match_id=" + match_id + ", equipe_id=" + equipe_id + '}';
    }

 
    
    
}
