public interface MyListInterface {
    /*  Come Abstract Class ma i metodi non possono essere implementati
     *  Posso utilizzarla come struttura per delle costanti
     */

    public final String constantString = "My List Interface";

    public boolean add(int index, Object o);
    public Object get(int index);
}