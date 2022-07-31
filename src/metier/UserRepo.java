package metier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserRepo {
    
    Connection con = MysqlConnect.getConnection();

    public int addUser(User user) {
    
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("insert into user (email, password, nom, prenom, numtel, user_role, autorized) values ('"+user.getEmail()+"','"+user.getPassword()+"', '"+user.getNom()+"', '"+user.getPrenom()+"', '"+user.getTelnum()+"', '"+user.getUser_role()+"', "+user.isAutorized()+")");
            return rs == 1 ? 1 : 0;
        } catch (Exception e) {
            System.out.println(e);
        }       
        return 0;
    }

    public int deleteUser(String email){
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("delete from user where email = '"+email+"'");
            return rs == 1 ? 1 : 0;
        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;
    }


    public List<User> getUsers(){
        
        List<User> users = new ArrayList<User>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");

            while (rs.next()){
                users.add(new User(rs.getInt("id") ,rs.getString("email"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("numtel"), rs.getString("user_role"), rs.getBoolean("autorized")));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public int updateUser(String id, String email, String password, String nom, String prenom, String telnum, String user_role){

        int idlocal = Integer.parseInt(id);
        
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("update user set email = '"+email+"', password = '"+password+"', nom = '"+nom+"', prenom = '"+prenom+"', numtel = '"+telnum+"', user_role = '"+user_role+"' where id = "+idlocal+"");
            return rs == 1 ? 1 : 0;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int autoriser(String email){
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("update user set autorized = 1 where email = '"+email+"'");
            return rs == 1 ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int bloquer(String email){
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("update user set autorized = 0 where email = '"+email+"'");
            return rs == 1 ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public User findOneUser(String email){
        User user = null;

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user where email = '"+email+"'");

            while (rs.next()){
                user = new User(rs.getInt("id"), rs.getString("email"), Crypt.decrypt(rs.getString("password")) , rs.getString("nom"), rs.getString("prenom"), rs.getString("numtel"), rs.getString("user_role"), rs.getBoolean("autorized"));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
