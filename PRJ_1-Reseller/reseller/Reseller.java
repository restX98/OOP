package reseller;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Enrico Restuccia
 */
public class Reseller {
    
    public static final String CATEGORIES = "categories.txt";
    public static final String PRODUCTS = "products.txt";
    
    private List<Category> categories;
    private List<Product> products;
    
    private Category moreProductForSale;
    private Category morePriceProductForSale;

    
    public final Category uncategorized = new Category("Uncategorized", "Prodotti senza categoria");
    
    public Reseller(){
        categories = new LinkedList<>();
        products = new LinkedList<>();
        categories.add(uncategorized);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Reseller reseller = new Reseller();        
        
        InteractiveThread interactiveThread = new InteractiveThread(reseller);
        Reporter reporter = new Reporter(reseller);
        
        interactiveThread.start();
        
        reporter.setDaemon(true);
        reporter.start();
    }
    
    public synchronized void createReport(){
        int max = 0, count;
        float maxPrice = 0, tmpPrice;
        for(Category cat : this.categories){
            if((count = cat.getProducts().size()) > max){
                max = count;
                this.moreProductForSale = cat;
            }
            if((tmpPrice = cat.getTotalPrice()) > maxPrice){
                maxPrice = tmpPrice;
                this.morePriceProductForSale = cat;
            }
        }
    }
    
    
    public synchronized void printReport(){
        System.out.println("La categoria con piu' oggetti in vendita e': "
                                            + this.moreProductForSale.name);
        System.out.println("La categoria con maggiore valore e': "
                                        + this.morePriceProductForSale.name);
    }
    
    
    public synchronized void printAll(){
        for(Category cat : this.getCategories()){
            System.out.println(cat.name + " " + cat.getDescription());
            for(Product prod : cat.getProducts()){
                System.out.println(prod.getID() + " " + prod.getDescription() + " " + prod.getPrice() + " " + prod.isSelled());
            }
            System.out.println("");
        }
    }
    
    public synchronized void printAvgPriceOfCategory(String category){
        float avgPrice = this.getAveragePriceOfCategory(category);
        if(avgPrice == 0) System.out.println("Categoria vuota");
        else System.out.println("Il prezzo medio è: " + avgPrice);
    }
    
    public float getAveragePriceOfCategory(String categoryName){
        float sum = 0;
        int count = 0;
        Category category = this.getCategoryByName(categoryName);
        for(Product prod : category.getProducts()){
            sum += prod.getPrice();
            count++;
        }
        return (count != 0) ? sum/count : 0;
    }
    
    public synchronized void removeProduct(String ID){
        Product product = this.getProductByID(ID);
        for(Category category : this.getCategories()){
            for (Iterator<Product> prods = category.getProducts().iterator(); prods.hasNext(); ){
                Product prod = prods.next();
                if(prod.equals(product)) prods.remove();   
            }
        }
    }
    
    public synchronized void loadProducts(){
        try(BufferedReader categoriesReader =
                new BufferedReader(new FileReader(Reseller.PRODUCTS))){
            
            String productLine;
            Product product;
            while((productLine = categoriesReader.readLine()) != null){
                if((product = this.createProduct(productLine)) != null){
                    if(!product.isSelled()) this.products.add(product);
                }
            }
        }catch(FileNotFoundException ex){
            Logger.getLogger(Reseller.class.getName())
                    .log(Level.SEVERE, "File not found", ex);
            System.exit(1);
        }catch(IOException ex){
            Logger.getLogger(Reseller.class.getName())
                    .log(Level.SEVERE, "IO Error", ex);
            System.exit(1);
        }catch(Exception ex){
            Logger.getLogger(Reseller.class.getName())
                    .log(Level.SEVERE, "Generic Error", ex);
            System.exit(1);
        }
    }
    
    public synchronized Product createProduct(String productLine){
        Product product = null;
        try{
            String[] fields = productLine.split("\\|");
            trimFields(fields);
            
            Category currentCategory = getCategoryByName(fields[4]);
            Product currentProduct = this.getProductByID(fields[0]);
            
            if(currentProduct == null){
                currentProduct = new Product(fields[0], fields[1],
                                            Float.parseFloat(fields[2]),
                                            Boolean.parseBoolean(fields[3]),
                                            currentCategory );
            } else {
                currentProduct.addCategory(currentCategory);
            }
            if(!currentProduct.isSelled()){
                currentCategory.addProduct(currentProduct);
                product = currentProduct;
            }
        }catch(Exception ex){
            Logger.getLogger(Reseller.class.getName())
                .log(Level.WARNING, "Error on file " + Reseller.CATEGORIES, ex);
        }
        return product;
    }
    
    public synchronized void loadCategories(){
        try(BufferedReader categoriesReader =
                new BufferedReader(new FileReader(Reseller.CATEGORIES))){
            
            String categoryLine;
            Category category;
            while((categoryLine = categoriesReader.readLine()) != null){
                if((category = this.createCategory(categoryLine)) != null){
                    this.categories.add(category);
                }
            }
        }catch(FileNotFoundException ex){
            Logger.getLogger(Reseller.class.getName())
                    .log(Level.SEVERE, "File not found", ex);
            System.exit(1);
        }catch(IOException ex){
            Logger.getLogger(Reseller.class.getName())
                    .log(Level.SEVERE, "IO Error", ex);
            System.exit(1);
        }catch(Exception ex){
            Logger.getLogger(Reseller.class.getName())
                    .log(Level.SEVERE, "Generic Error", ex);
            System.exit(1);
        }
    }
    
    public synchronized Category createCategory(String categoryLine){
        Category category = null;
        try{
            String[] fields = categoryLine.split("\\|");
            trimFields(fields);
            category = new Category(fields[0], fields[1]);
        }catch(Exception ex){
            Logger.getLogger(Reseller.class.getName())
                .log(Level.WARNING, "Error on file " + Reseller.CATEGORIES, ex);
        }
        return category;
    }
    
    public synchronized Category getCategoryByName(String searchCategory){
        Category category = null;
        for(Category cat : this.categories){
            if(cat.name.compareTo(searchCategory) == 0){
                category = cat;
                break;
            }
        }
        return category;
    }
    
    public synchronized Product getProductByID(String ID){
        Product product = null;
        for(Product prod : this.products){
            if(prod.getID().compareTo(ID) == 0){
                product = prod;
                break;
            }
        }
        return product;
    }
    
    public void trimFields(String[] fields){
        for(int i = 0; i<fields.length; i++){
            fields[i] = fields[i].trim();
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        return products;
    }  
    
}
