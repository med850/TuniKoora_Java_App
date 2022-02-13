/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author aroua
 */
public class Users {
    private int id,cin, tel;
    private String nom,prenom,email,password,repeatpassword,typeuser;
    

    public Users() {
    }

    public Users(int id, int cin, int tel, String nom, String prenom, String email, String password, String repeatpassword, String typeuser) {
        this.id = id;
        this.cin = cin;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.repeatpassword = repeatpassword;
        this.typeuser = typeuser;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", cin=" + cin + ", tel=" + tel + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", repeatpassword=" + repeatpassword + ", typeuser=" + typeuser + '}';
    }

    public int getId() {
        return id;
    }

    public int getCin() {
        return cin;
    }

    public int getTel() {
        return tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatpassword() {
        return repeatpassword;
    }

    public String getTypeuser() {
        return typeuser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepeatpassword(String repeatpassword) {
        this.repeatpassword = repeatpassword;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }
    
}
