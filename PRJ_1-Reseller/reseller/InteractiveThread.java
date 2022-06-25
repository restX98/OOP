package reseller;

import java.io.*;
import java.util.logging.*;

/**
 *
 * @author Enrico Restuccia
 */
public class InteractiveThread extends Thread {
    
    Reseller reseller;
    
    public InteractiveThread(Reseller reseller){
        this.reseller = reseller;
    }

    @Override
    public void run() {
        
        try(BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))){
            
            reseller.loadCategories();
            reseller.loadProducts();
            
            int choise;
            while((choise = this.menu(keyboard)) != 0){
                switch(choise){
                    case 1:
                        System.out.print("Scegli categoria: ");
                        String categoryName = keyboard.readLine();
                        this.reseller.printAvgPriceOfCategory(categoryName);
                    break;
                    case 2:
                        System.out.print("Inserisci ID articolo: ");
                        String idProduct = keyboard.readLine();
                        this.reseller.removeProduct(idProduct);
                    break;
                    case 3: 
                        this.reseller.printReport();
                    break;
                    case 4: 
                        this.reseller.printAll();
                    break;
                    default:
                        System.out.println("Inserisci un valore valido.");
                    break;
                }
            }            
        }catch(IOException ex){
            Logger.getLogger(Reseller.class.getName())
                .log(Level.WARNING, "Error on file " + Reseller.CATEGORIES, ex);
        }
    }
    
    
    public Integer menu(BufferedReader keyboard){
        Integer choise = null;
        try {
            System.out.println("1. Stampa il prezzo medio di una categoria.");
            System.out.println("2. Elimina prodotto.");
            System.out.println("3. Stampa Report.");
            System.out.println("4. Stampa Tutto.");
            System.out.println("0. Esci.");
            System.out.print("Inserisci un'operazione --> ");
            choise = Integer.parseInt(keyboard.readLine());
        } catch (IOException ex) {
            Logger.getLogger(InteractiveThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return choise;
    }
}