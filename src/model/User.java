package model;

import javax.xml.bind.annotation.XmlRootElement;

import metier.Crypt;

@XmlRootElement
public class User {
    private int id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String telnum;
    private String user_role;
    private boolean autorized;

    public User() {
    }

    public User(int id, String email, String password, String nom, String prenom, String telnum, String user_role, boolean autorized) {
        this.id = id;
        this.email = email;
        this.password = Crypt.crypt(password);
        this.nom = nom;
        this.prenom = prenom;
        this.telnum = telnum;
        setUser_role(user_role);
        this.autorized = autorized;
    }

    public User(String email, String password, String nom, String prenom, String telnum, String user_role) {
        this.email = email;
        this.password = Crypt.crypt(password);
        this.nom = nom;
        this.prenom = prenom;
        this.telnum = telnum;
        setUser_role(user_role);
        this.autorized = false;
    }

    public User(String email, String password, String nom, String prenom, String telnum, String user_role, boolean autorized) {
        this.email = email;
        this.password = Crypt.crypt(password);
        this.nom = nom;
        this.prenom = prenom;
        this.telnum = telnum;
        setUser_role(user_role);
        this.autorized = autorized;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Crypt.crypt(password);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        if (user_role.equals("admin") || user_role.equals("editeur")){
            this.user_role = user_role;
        } else {
            throw new IllegalArgumentException("user_role must be admin or editeur");
        }
    }

    public boolean isAutorized() {
        return autorized;
    }

    public void setAutorized(boolean autorized) {
        this.autorized = autorized;
    }

}
