package pharmacymanager;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Enrico Restuccia
 */
public class MainThread extends Thread {
    
    private static final Logger LOG = Logger.getLogger(MainThread.class.getName());

    private final Pharmacy pharmacy;

    public MainThread(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
    
    
    @Override
    public void run() {
        
        pharmacy.loadClients();
        pharmacy.loadMedicines();
        
        try(BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))){
            int choise;
            while((choise = this.menu(keyboard)) != 0){
                switch(choise){
                    case 1:
                        System.out.println(pharmacy);
                    break;
                    case 2:
                        this.sellMedicine(keyboard);
                    break;
                    case 3:
                        this.findCustomerByMedicinesList(keyboard);
                    break;
                    case 4:
                        pharmacy.printReport();
                    break;
                    default:
                        System.out.println("Seleziona un valore valido");
                    break;
                }
            }
            
        }catch(Exception ex){
            LOG.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()), ex);
            System.exit(1);
        }
    }
    
    public int menu(BufferedReader keyboard){
        int val;
        try {            
            System.out.println("1. Mostra tutto");
            System.out.println("2. Vendi un nuovo farmaco");
            System.out.println("3. Trova tutti i clienti per lista prodotti");
            System.out.println("4. Clienti che hanno acquistato dal 01/05/2020");
            System.out.println("0. Esci");
            System.out.print("Inserisci la tua scelta -> ");
            val = Integer.parseInt(keyboard.readLine());
        } catch (IOException ex) {
            val = -1;
            LOG.log(Level.INFO, null, ex);
        }
        return val;
    }
    
    public void sellMedicine(BufferedReader keyboard){
        try {            
            System.out.print("Inserisci codice fiscale: ");
            String IDCode = keyboard.readLine();
            Client currentClient = pharmacy.getClientByID(IDCode);
            System.out.print("Inserisci il nome del farmaco: ");
            String mName = keyboard.readLine();
            System.out.print("Inserisci l'ID del farmaco: ");
            String mID = keyboard.readLine();
            System.out.print("Inserisci il prezzo del farmaco: ");
            float mPrice = Float.parseFloat(keyboard.readLine());
            System.out.print("Inserisci data di vendita: ");
            String mDate = keyboard.readLine();
            
            currentClient.addMedicine(new Medicine(mName, mID, mPrice, mDate));
            pharmacy.updateScore(currentClient);
        } catch (Exception ex) {
            LOG.log(Level.INFO, null, ex);
        }
    }
    
    public void findCustomerByMedicinesList(BufferedReader keyboard){
        try {
            List<String> IDs = new LinkedList<>();
            System.out.print("Quanti prodotti vuoi inserire in lista? ");
            int n = Integer.parseInt(keyboard.readLine());
            for(int i = 0; i<n; i++){
                IDs.add(keyboard.readLine());
            }
            List<Client> filteredClient = pharmacy.getClientsByMedicineList(IDs);
            
            for(Client c : filteredClient) System.out.println(c);
        } catch (Exception ex) {
            LOG.log(Level.INFO, null, ex);
        }
    }
}
