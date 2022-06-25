package reseller;

import java.util.*;

/**
 *
 * @author enric
 */
public class Product {
    
    private final String ID;
    private String description;
    private float price;
    private boolean selled;
    private List<Category> category;

    public Product(String ID, String description, float price, boolean isSelled, Category category) {
        this.ID = ID;
        this.description = description;
        this.price = price;
        this.selled = isSelled;
        this.category = new LinkedList<Category>();
        this.category.add(category);
    }
    
    public String getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSelled() {
        return selled;
    }

    public void setSelled(boolean isSelled) {
        this.selled = selled;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void addCategory(Category category) {
        this.category.add(category);
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
        final Product other = (Product) obj;
        return Objects.equals(this.ID, other.ID);
    }
    
    
    
}
