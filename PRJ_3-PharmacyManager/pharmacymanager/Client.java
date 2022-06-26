package pharmacymanager;

import java.util.*;

/**
 *
 * @author Enrico Restuccia
 */
public class Client {
    
    private String name;
    public final String IDCode;
    private float score;
    private List<Medicine> medicines;
    
    public Client(String name, String IDCode, float score) {
        this.name = name;
        this.IDCode = IDCode;
        this.score = score;
        this.medicines = new LinkedList<>();
    }
    
    public void addMedicine(Medicine newMedicine){
        medicines.add(newMedicine);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public String getIDCode() {
        return IDCode;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        String clientStr = name + ", " + IDCode + ", Punteggio: " + score + "\n";
        for(Medicine medicine : this.medicines){
            clientStr = clientStr.concat(medicine.toString() + "\n");
        }
        return clientStr;
    }
}
