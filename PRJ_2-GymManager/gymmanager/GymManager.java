package gymmanager;

/**
 *
 * @author Enrico Restuccia
 */
public class GymManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gym gym = new Gym();
        
        MainThread main = new MainThread(gym);
        ReportThread reporter = new ReportThread(gym);
        reporter.setDaemon(true);
        
        main.start();
        reporter.start();
    }
    
}
