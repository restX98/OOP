package gymmanager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrico Restuccia
 */
public class ReportThread extends Thread {
    
    private Gym gym;

    public ReportThread(Gym gym) {
        this.gym = gym;
    }   
    
    @Override
    public void run() {
        try {
            gym.createReport();
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ReportThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.run();
    }
}
