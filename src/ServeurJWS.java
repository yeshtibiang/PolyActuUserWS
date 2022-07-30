import javax.xml.ws.Endpoint;
import service.UserService;
import service.AuthentificationService;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServeurJWS {
    public static void main(String[] args) {

        InetAddress ip;

        try {
            ip = InetAddress.getLocalHost();
            String url1 = "http://"+ ip.getHostAddress() + ":8585/";
            String url2 = "http://"+ ip.getHostAddress() + ":8586/";
            Endpoint.publish(url1, new UserService());
            Endpoint.publish(url2, new AuthentificationService());
            System.out.println("Service Utilisateur: " +url1);
            System.out.println("Service d'authentification: " +url2);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        
    }
}
