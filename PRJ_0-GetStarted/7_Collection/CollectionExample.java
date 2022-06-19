import java.util.*;

public class CollectionExample {

    public static void main(String args[]){
        /* Collection è la radice delle interfacce che modella
         * l'astrazione di una collezione di dati e le operazioni
         * applicabili ad essa.
         * Da Collection derivano Set e List:
         * Set: Interfaccia che rappresenta un insieme di elementi
         *      non duplicati (HashSet).
         *      SortedSet: estende Set e garantisce l'ordine del
         *                 Set di dati. (TreeSet)
         * List: Lista o Sequenza di elementi anche duplicati.
         *       (ArrayList, LinkedList, Vector).
         * Map: Non estende Collection! Contiene degli elementi
         *      recuperabili tramite un'associazione chiave valore.
         *      (HashMap, HastTable).
         *      SortedMap: Mappa Ordinata (TreeMap).
         */

        // List
        String[] strings = {"Prova", "Prova", "Prova", "Ciao", "Mondo", "Qwerty"};
        
        List<String> linkedList = new LinkedList<String>();
        CollectionExample.loadAndPrintCollection(strings, linkedList, "LinkedList: ");

        List<String> arrayList = new ArrayList<String>();
        CollectionExample.loadAndPrintCollection(strings, arrayList, "ArrayList: ");
        
        List<String> vector = new Vector<String>(); // Uguale ad ArrayList ma synchronized
        CollectionExample.loadAndPrintCollection(strings, vector, "Vector: ");

        Set<String> hashSet = new HashSet<String>(); // Doppie non ripetute
        // L'ordine non coincide con quello di inserimento
        CollectionExample.loadAndPrintCollection(strings, hashSet, "HashSet: ");
        
        Set<String> treeSet = new TreeSet<String>();
        // L'ordine non coincide con quello di inserimento
        CollectionExample.loadAndPrintCollection(strings, treeSet, "TreeSet: ");

        // Mappa costituita da una chiave di tipo String che rappresenta identifica
        // ogni parola inserita e un valore che rappresenta l'incidenza di ogni parola.
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        CollectionExample.loadAndPrintMap(strings, hashMap, "HashMap: ");

        Map<String, Integer> treeMap = new TreeMap<String, Integer>();
        CollectionExample.loadAndPrintMap(strings, treeMap, "TreeMap: ");

    }

    public static void loadAndPrintCollection(String[] strings, Collection<String> list, String label){
        for(int i = 0; i<strings.length; i++){
            list.add(strings[i]);
        }
        System.out.println(label + list);
    }

    public static void loadAndPrintMap(String[] strings, Map<String, Integer> map, String label){
        Integer freq;
        for(int i = 0; i<strings.length; i++){
            freq = map.get(strings[i]);
            map.put(strings[i], (freq == null) ? 1 : ++freq);
        }
        System.out.println(label + map);
    }
}
