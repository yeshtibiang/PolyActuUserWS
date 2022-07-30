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

        if (user != null && user.getPassword().equalsIgnoreCase(Crypt.crypt(password))) {
            return user;
        }else{
            return null;
        }
    }
}
