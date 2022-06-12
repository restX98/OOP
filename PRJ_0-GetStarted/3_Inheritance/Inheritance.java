public class Inheritance {

    public static void main(String[] args) {

        System.out.println("INHERITANCE");

        LabeledPoint p1 = new LabeledPoint("Ciccio");
        Point p2 = new Point(10, 10);
        Point p3 = new Point();

        System.out.println(p1.toString());
        System.out.println(p1.equals(p3));

        Point genericPoint = new LabeledPoint("Paccio", 1, 3);
        // LabeledPoint genericPoint = new Point("Paccio", 1, 3); // Error: LabeledPoint è un Point 
                                                                  //        ma Point non è un LabeledPoint

        ((LabeledPoint)genericPoint).setLabel("Paccio1"); //Però perdo l'accesso ai nuovi metodi definiti
        
        System.out.println(genericPoint.toString()); // Ma uso quelli Overrided.
        /* Binding Dinamico: le chiamate ai metodi sono determinate a runtime in funzione
         * della classe dell'istanza su cui è stato chiamato.
         * Un metodo si dice polimorfo quando è in grado di adattare il suo comportamento
         * all'oggetto specifivo.
        */
    }

}