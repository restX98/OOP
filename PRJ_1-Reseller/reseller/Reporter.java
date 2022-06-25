package reseller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enric
 */
public class Reporter extends Thread {
    
    Reseller reseller;
    
    public Reporter(Reseller reseller){
        this.reseller = reseller;
    }

    @Override
    public void run() {
        try {
            reseller.createReport();
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.run();
    }
}
