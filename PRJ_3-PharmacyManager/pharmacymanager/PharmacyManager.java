package pharmacymanager;

/**
 *
 * @author Enrico Restuccia
 */
public class PharmacyManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Pharmacy pharmacy = new Pharmacy();
        
        MainThread main = new MainThread(pharmacy);
        
        ReportThread reporter = new ReportThread(pharmacy);
        reporter.setDaemon(true);
        
        main.start();
        reporter.start();

    }
    
}
