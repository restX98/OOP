import java.util.*;

public class IteratorExample {

    public static void main(String[] args){

        List<Integer> list = new LinkedList<Integer>();
        int val;
        for(int i = 0; i<100000; i++) list.add(i);

        System.out.println("Iterator Start");
        for(Iterator<Integer> iter = list.iterator(); iter.hasNext(); val = iter.next());
        System.out.println("Iterator End");

        System.out.println("ForEach Start");
        for(int i:list) val = i;
        System.out.println("ForEach End");


        System.out.println("Get Start");
        // Molto lenta perché ogni volta ricomincia dalla prima posizione
		for (int i = 0;  i < list.size(); i++) val = list.get(i); 
		System.out.println("Get End");
    }

}