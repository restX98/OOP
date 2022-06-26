package pharmacymanager;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrico Restuccia
 */
public class Pharmacy {

    private static final Logger LOG = Logger.getLogger(Pharmacy.class.getName());
    
    public final static String CLIENTS_FILE = "clients.txt";
    public final static String MEDICINE_FILE = "purchasedMedicines.txt";
    public final static int PROMOTION_RATIO = 2;
    
    public List<Client> clients;
    public List<Client> lastClients;
    
    public Pharmacy() {
        this.clients = new LinkedList<>();
        this.lastClients = new LinkedList<>();
    }
    
    public synchronized void loadClients(){
        try(BufferedReader file = new BufferedReader(new FileReader(Pharmacy.CLIENTS_FILE))){
            String clientLine;
            while((clientLine = file.readLine()) != null){
                this.loadClient(clientLine);   
            }
        }catch(Exception ex){
            LOG.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()), ex);
            System.exit(1);
        }
    }
    private synchronized void loadClient(String clientLine){
        try{
            String[] fields = clientLine.split(",");
            trimFields(fields);
            this.clients.add(new Client(fields[0], fields[1], Float.parseFloat(fields[2])));
        }catch(Exception ex){
            LOG.log(Level.WARNING, "Wrong File Formatting", ex);
        }
    }
    
    public synchronized void loadMedicines(){
        try(BufferedReader file = new BufferedReader(new FileReader(Pharmacy.MEDICINE_FILE))){
            String medicineLine;
            while((medicineLine = file.readLine()) != null){
                this.loadMedicine(medicineLine);  
            }
        }catch(Exception ex){
            LOG.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()), ex);
            System.exit(1);
        }
    }
    private synchronized void loadMedicine(String medicineLine){
        try{
            String[] fields = medicineLine.split(",");
            trimFields(fields);
            Client client = this.getClientByID(fields[0]);
            client.addMedicine(new Medicine(fields[1], fields[2], Float.parseFloat(fields[3]), fields[4]));
        }catch(ClientNotFoundException ex){
            LOG.log(Level.WARNING, null, ex);
        }catch(Exception ex){
            LOG.log(Level.WARNING, "Wrong File Formatting", ex);
        }
    }
    
    public synchronized Client getClientByID(String ID) throws ClientNotFoundException{
        for(Client client : this.clients){
            if(client.IDCode.compareTo(ID) == 0) return client;
        }
        throw new ClientNotFoundException();
    }
    
    public synchronized void updateScore(Client currentClient) {
        float tot = 0;
        for(Medicine medicine : currentClient.getMedicines()){
            tot += medicine.getPrice();
        }
        int totInt = (int)tot;
        currentClient.setScore((float)totInt/Pharmacy.PROMOTION_RATIO);
    }
    
    private synchronized void trimFields(String[] fields){
        for(int i = 0; i<fields.length; i++) fields[i] = fields[i].trim();
    }


    public synchronized List<Client> getClientsByMedicineList(List<String> IDs) {
        List<Client> filteredClients = new LinkedList<>();
        
        for(Client c : this.clients){
            for(String id : IDs){
                if(c.getMedicines().indexOf(new Medicine(id)) > -1)
                    filteredClients.add(c);
            }
        }
        return filteredClients;
    }
    
    public synchronized void createReport(String date){
        for(Client client : this.clients){
            for(Medicine medidice : client.getMedicines()){
                if(medidice.getPurhcasedDate().compareTo(date) > 0) lastClients.add(client);
            }
        }
    }
    
    public synchronized void printReport(){
        for(Client client : this.lastClients){
            System.out.println(client);
        }
    }
    
    @Override
    public String toString() {
        String pharmacyStr = "";
        for(Client client : this.clients){
            pharmacyStr = pharmacyStr.concat(client.toString() + "\n");
        }
        return pharmacyStr;
    }
}
