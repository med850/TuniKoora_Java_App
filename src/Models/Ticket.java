
package Models;


public class Ticket {

       private int id, prix, match_id, user_id;
       private String equipeA, equipeB;


     public Ticket(){

    }


      public Ticket(int id, String equipeA, String equipeB, int prix,int match_id, int user_id){
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.prix = prix;
        this.match_id = match_id;
        this.user_id = user_id;
       
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", equipeA=" + equipeA + ", equipeB=" + equipeB + ", prix=" + prix + ", match_id=" + match_id + ", user_id=" + user_id +  '}';
    }
      
      
      
      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getprix() {
        return prix;
    }

    public void setprix(int prix) {
        this.prix = prix;
    }

    public int getuser_id() {
        return user_id;
    }
    
    public int getmatch_id() {
        return match_id;
    }

    public void setmatch_id(int match_id) {
        this.match_id = match_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getequipeA() {
        return equipeA;
    }

    public void setequipeA(String equipeA) {
        this.equipeA = equipeA;
    }

    public String getequipeB() {
        return equipeB;
    }

    public void setequipeB(String equipeB) {
        this.equipeB = equipeB;
    }

      
      
      
      
      

}
