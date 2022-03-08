
package Models;


public class Ticket {

       private int id, prix, match_id, user_id;
       private String equipeA, equipeB;


     public Ticket(){

    }

    public Ticket(int id, int prix, int match_id, int user_id, String equipeA, String equipeB) {
        this.id = id;
        this.prix = prix;
        this.match_id = match_id;
        this.user_id = user_id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
    }

    public int getId() {
        return id;
    }

    public int getPrix() {
        return prix;
    }

    public int getMatch_id() {
        return match_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getEquipeA() {
        return equipeA;
    }

    public String getEquipeB() {
        return equipeB;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setEquipeA(String equipeA) {
        this.equipeA = equipeA;
    }

    public void setEquipeB(String equipeB) {
        this.equipeB = equipeB;
    }

    public String getProxtot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




}
