package pharmacymanager;

/**
 *
 * @author Enrico Restuccia
 */
public class ClientNotFoundException extends Exception {

    public ClientNotFoundException() {
        super("Client Not Found");
    }
    
}
