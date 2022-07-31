package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import metier.Crypt;
import metier.UserRepo;
import model.User;

@WebService(serviceName = "authentificationWS")
public class AuthentificationService {

    private UserRepo userRepo = null;

    public AuthentificationService() {
        this.userRepo = new UserRepo();
    }

    @WebMethod(operationName = "login")
    public User login(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        
        User user = userRepo.findOneUser(email);
        System.out.println(user.getPassword());

        if (user != null && Crypt.decrypt(user.getPassword()).equalsIgnoreCase(password)) {
            return user;
        }else{
            return null;
        }
    }
}
