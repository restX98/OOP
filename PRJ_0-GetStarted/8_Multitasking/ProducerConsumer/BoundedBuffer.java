
public class BoundedBuffer<T> {

    public final int dim;
    private int count;
    private int in;
    private int out;
    private T[] buffer;

    public BoundedBuffer(){
        this(10);
    }

    public BoundedBuffer(int dimension){
        this.dim = dimension;
        // this.buffer = new T[dimension]; // Errore: Non si può istanziare un array con i Generics
        this.buffer = (T[]) new Object[dimension]; // Warning
        this.count = 0;
        this.in = 0;
        this.out = 0;
    }

    public synchronized T remove(){
        while(count == 0){
            try{
                this.wait();
            } catch(InterruptedException ex){}
        }
        count-=1;
        T val = buffer[out];
        out = (out+1) % dim;
        if(count == dim-1) this.notify();
        return val;
    }

    public synchronized void insert(T val){
        while(count == dim){
            try{
                this.wait();
            } catch(InterruptedException ex){}
        }
        count+=1;
        buffer[in] = val;
        in = (in+1) % dim;
        if(count == 1) this.notify();
    }
    

    @Override
    public String toString(){
        String arrayStr = new String("[ ");
        if(count == 0) return "[ ] - Count: " + count + " - In: " + in + " - Out: " + out;
        
        int start = out;
        int end = (in<=out) ? in+dim : in;

        for(int i = start; i<end; i++){
            arrayStr = arrayStr.concat(this.buffer[i%dim] + ", ");
        }
        return arrayStr.substring(0, arrayStr.length()-2)
                            .concat(" ] - Count: " + count + " - In: " + in + " - Out: " + out);
    }

    public String toStringArray(){
        String arrayStr = new String("[ ");

        for(int i = 0; i<dim; i++){
            arrayStr = arrayStr.concat(this.buffer[i] + ", ");
        }
        return arrayStr.substring(0, arrayStr.length()-2).concat(" ]");
    }
}