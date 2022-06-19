public class Main{

    public static void main(String args[]) {
        System.out.println("My Simple List Implementation\n\n");

        Main.intExample();
        System.out.println("");
        Main.pointExample();
        System.out.println("");
        Main.problemsExample();

    }
    public static void intExample(){
        System.out.println("Int Example:");

        MyList list = new MyList();

        list.add(3);
        list.add(4);
        list.addLast(5);
        list.addFirst(6);
        list.addFirst(7);
        list.addFirst(8);
        list.addFirst(9);

        System.out.println(list.toString());

        System.out.println("Value at index 1: " + list.get(1));

        System.out.println("Index of value 6: " + list.indexOf(6));

        list.remove(4); // Remove Last Element
        System.out.println("Remove Node with Value 4: " + list.toString());
        
        
        try{
            list.removeFirst();
            System.out.println("Remove First Node: " + list.toString());

            list.removeLast();
            System.out.println("Remove Last Node: " + list.toString());

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static void pointExample(){
        System.out.println("Point Example:");

        MyList list = new MyList();

        list.add(new Point(3, 5));
        list.add(new Point(4, 5));
        list.addLast(new Point(5, 5));
        list.addFirst(new Point(6, 10));
        list.addFirst(new Point(7, 10));
        list.addFirst(new Point(8, 10));
        list.addFirst(new Point(9, 10));

        System.out.println(list.toString());

        System.out.println("Value at index 1: " + list.get(1));

        System.out.println("Index of value 6: " + list.indexOf(new Point(7, 10)));

        list.remove(4); // Remove Last Element
        System.out.println("Remove Node with Value 4: " + list.toString());
        
        
        try{
            list.removeFirst();
            System.out.println("Remove First Node: " + list.toString());

            list.removeLast();
            System.out.println("Remove Last Node: " + list.toString());

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static void problemsExample(){
        System.out.println("Problems Example:");

        MyList list = new MyList();
        
        /* Grazie all'implementazione tramite object posso aggiungere
         * alla lista oggetti di qualunque Classe.
         */
        list.add(new Point(3, 5));
        list.add(new Point(4, 5));
        list.addLast(10);
        list.addFirst(new String("String Examples"));

        System.out.println(list.toString());

        /* Il che non è sempre positivo dato che ogni volta che voglio 
         * utilizzare un oggetto dovrei controllare di che classe è
         * l'oggetto e dopo effettuare il cast
         */

        Point p1 = (Point)list.get(1); // OK
        // Point p2 = (Point)list.get(3); // Runtime Error
        System.out.println("Value at index 1: " + p1);

        Object o = list.get(0);
        // So che nella lista ci sono Points, Int e String, quindi:
        if(o instanceof Point){
            Point p = (Point)o;
            // DO STUFF
        }else if(o instanceof Integer){
            int x = (int)o;
            // DO STUFF

        }else if(o instanceof String){
            String str = (String)o;
            // DO STUFF
        }
        // Che è poco efficiente, quindi in generale conviene utilizzare
        // i generics.
    }    
}
