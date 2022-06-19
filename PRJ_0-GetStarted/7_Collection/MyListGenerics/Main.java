public class Main{

    public static void main(String args[]) {
        System.out.println("My Simple Generics List Implementation\n\n");

        MyListGenerics<Integer> list = new MyListGenerics<Integer>();

        list.add(3);
        list.add(4);
        list.addLast(5);
        list.addFirst(6);
        list.addFirst(7);
        list.addFirst(8);
        list.addFirst(9);

        // list.add(new String("Compile Time Error"));

        System.out.println(list.toString());

        System.out.println("Value at index 1: " + list.get(1));

        System.out.println("Index of value 6: " + list.indexOf(6));

        list.remove(4); // Remove Last Element
        System.out.println("Remove Node with Value 4: " + list.toString());

        /* Il funzionamento è identico ma si potrà limitare l'utilizzo della
         * lista ad una classe definita e alle sue sottoclassi.
         */
        // Assegnando Object torniamo al caso della MyList
        MyListGenerics<Object> objectList = new MyListGenerics<Object>();

        objectList.add(new Point(3, 5));
        objectList.add(new Point(4, 5));
        objectList.addLast(10);
        objectList.addFirst(new String("String Examples"));

        System.out.println(objectList.toString());

        /* Nonostante una qualunque classe C sia derivata da Object data
         * un'implementazione generica G, non è vero che G<C> sia derivata
         * di G<Object>.
         */
        
        //MyListGenerics<Object> objList = list; // Compile Error

    }
}
