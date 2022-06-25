package reseller;

import java.util.*;

/**
 *
 * @author Enrico Restuccia
 */
public class Category {
    
    public String name;
    private String description;
    private List<Product> products;
    
    
    public Category(String categoryName){
        this(categoryName, "");
    }
    
    public Category(String name, String description){
        this.name = name;
        this.description = description;
        this.products = new LinkedList<Product>();
    }
    
    public float getTotalPrice(){
        float totPrice = 0;
        for(Product prod : this.products){
            totPrice += prod.getPrice();
        }
        return totPrice;
    }
    
    public void addProduct(Product product){
        this.products.add(product);
    }
    
    public void removeProduct(Product product){
        this.products.remove(product);
    }
    
    public List<Product> getProducts() {
        return products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Category other = (Category) obj;
        return Objects.equals(this.name, other.name);
    }
}
