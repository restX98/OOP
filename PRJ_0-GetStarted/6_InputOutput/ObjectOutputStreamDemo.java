import java.io.*;

class Point implements Serializable { // Se non implemento serializzable da errore

    protected int x;
    protected int y;

    public Point(){
        this(0, 0);
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return this.getClass().getName() + "(" + x + ", " + y + ")";
    }
}

public class ObjectOutputStreamDemo extends ObjectOutputStream {
    public ObjectOutputStreamDemo(String fileName) throws IOException
    {
        super(new FileOutputStream(fileName));
    }
     
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ObjectOutputStreamDemo oot = new ObjectOutputStreamDemo("objectStream.txt");
        Point point = new Point(4, 5);
         
        //illustrating annotateClass(Class<?> cl) method
        //oot.annotateClass(Character.class);
         
        //Write the specified object to the ObjectOutputStream
        // oot.writeObject(3); // I tipi base e i wrapper sono Serializzable di default 
        oot.writeObject(point);
         
        //flushing the stream
        //oot.flush();
         
        //closing the stream
        oot.close();
        
        ObjectInputStream oit = new ObjectInputStream(new FileInputStream("objectStream.txt"));
        System.out.print(oit.readObject().toString());
        oit.close();
    }
}
