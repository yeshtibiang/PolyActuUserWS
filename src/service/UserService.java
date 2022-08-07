package service;

import javax.jws.*;
import java.util.*;

import metier.Crypt;
import metier.UserRepo;
import model.User;

@WebService(serviceName = "UserWS")
public class UserService {

    UserRepo userRepo = new UserRepo();

    @WebMethod(operationName = "inserer")
    public int addUser(@WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "telnum") String telnum, @WebParam(name = "user_role") String user_role, @WebParam(name = "autorized") Boolean autorized){

        User user = new User(email, password, nom, prenom, telnum, user_role, autorized);

        return userRepo.addUser(user);
    }

    @WebMethod(operationName = "sup")
    public int deleteUser(@WebParam(name = "email") String email){
        return userRepo.deleteUser(email);
    }

    @WebMethod(operationName = "lister")
    public List<User> getEtudiants(){
        return userRepo.getUsers();
    }

    @WebMethod(operationName = "update")
    public int updateUser(@WebParam(name = "id") String id, @WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "telnum") String telnum, @WebParam(name = "user_role") String user_role){
        return userRepo.updateUser(id, email, Crypt.crypt(password), nom, prenom, telnum, user_role);
    }

    @WebMethod(operationName = "find")
    public User findOneUser(@WebParam(name = "email") String email){
        return userRepo.findOneUser(email);
    }


    @WebMethod(operationName = "autoriser")
    public int autoriserUser(@WebParam(name = "email") String email){
        return userRepo.autoriser(email);
    }

    @WebMethod(operationName = "bloquer")
    public int bloquerUser(@WebParam(name = "email") String email){
        return userRepo.bloquer(email);
    }
  
}
