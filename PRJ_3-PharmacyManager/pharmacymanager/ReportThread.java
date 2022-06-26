package pharmacymanager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrico Restuccia
 */
public class ReportThread extends Thread {

    private static final Logger LOG = Logger.getLogger(ReportThread.class.getName());
    
    private final String date = "20211004";
    
    private final Pharmacy pharmacy;

    public ReportThread(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
    
    @Override
    public void run() {
        try {
            this.pharmacy.createReport(this.date);
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException ex) {
            LOG.log(Level.WARNING, null, ex);
        }
        this.run();
    }    
}
