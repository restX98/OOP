package pharmacymanager;

import java.util.Objects;

/**
 *
 * @author Enrico Restuccia
 */
public class Medicine {
    private String name;
    public final String ID;
    private float price;
    private String purhcasedDate;
    
    public Medicine(String ID){
        this("", ID, 0, "");
    }

    public Medicine(String name, String ID, float price, String purhcasedDate) {
        this.name = name;
        this.ID = ID;
        this.price = price;
        this.purhcasedDate = purhcasedDate;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPurhcasedDate() {
        return purhcasedDate;
    }

    public void setPurhcasedDate(String purhcasedDate) {
        this.purhcasedDate = purhcasedDate;
    }

    @Override
    public String toString() {
        return name + ", " + ID + ", " + price + ", " + purhcasedDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medicine other = (Medicine) obj;
        return Objects.equals(this.ID, other.ID);
    }
}
