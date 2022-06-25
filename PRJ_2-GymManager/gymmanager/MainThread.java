package gymmanager;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrico Restuccia
 */
public class MainThread extends Thread {

    private static final Logger LOG = Logger.getLogger(MainThread.class.getName());
    
    private Gym gym;

    public MainThread(Gym gym) {
        this.gym = gym;
    }    

    @Override
    public void run() {
        try(BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))){
            
            System.out.print("Inserisci la data di scadenza degli abbonamenti: ");
            String expiringDate = keyboard.readLine();
            gym.loadCourses();
            gym.loadPersons(expiringDate);
        
            int choise;
            while((choise = this.menu(keyboard)) != 0){
                switch(choise){
                    case 1:
                        System.out.println(gym.toString());
                    break;
                    case 2:
                        this.addPerson(keyboard);
                    break;
                    case 3:
                        this.removePerson(keyboard);
                    break;
                    case 4:
                        System.out.println("Il miglior corso è: " + gym.maxRevenueCourse);
                    break;
                    default:
                        System.out.println("Inserisci un valore valido");
                    break;
                }
            }
        }catch(Exception ex){
            LOG.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()), ex);
            System.exit(1);
        }
    }
    
    private int menu(BufferedReader keyboard) {
        Integer value = -1;
        try {
            System.out.println("1. Stampa tutto.\n");
            System.out.println("2. Inserisci persona.\n");
            System.out.println("3. Elimina persona.\n");
            System.out.println("4. Report.\n");
            System.out.println("0. Esci.\n");
            System.out.print("Inserisci comando -> ");
            value = Integer.parseInt(keyboard.readLine());
            
        } catch (IOException ex) {
            LOG.log(Level.INFO, null, ex);
        }
        return value;
    }
    
    private void addPerson(BufferedReader keyboard){
        try{
            System.out.print("Inserisci nome e cognome: ");
            String name = keyboard.readLine();
            System.out.print("Inserisci il codice fiscale: ");
            String ID = keyboard.readLine();
            System.out.print("Inserisci data di scadenza abbonamento: ");
            String expireDate = keyboard.readLine();
            System.out.print("Inserisci il corso: ");
            String courseName = keyboard.readLine();
            
            gym.getCourseByName(courseName).addPerson(new Person(name, 
                                                ID, expireDate, courseName));    
        } catch (Exception ex) {
            LOG.log(Level.INFO, null, ex);
        }
    }
    
    private void removePerson(BufferedReader keyboard){
        try{
            System.out.print("Inserisci codice fiscale da eliminare: ");
            String ID = keyboard.readLine();
            gym.removePerson(ID);
        } catch (Exception ex) {
            LOG.log(Level.INFO, null, ex);
        }
    }
}
