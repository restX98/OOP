import java.util.*;

public class MyList {
    
    private Node head;
    private Node tail;
    private int size;

    MyList(){
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    public void add(Object newValue){
        if(head == null) head = tail = new Node(newValue);
        else {
            tail.setNext(new Node(newValue));
            tail = tail.getNext();
        }
        size++;
    }
    
    public void addLast(Object newValue){
        this.add(newValue);
    }

    public void addFirst(Object newValue){
        head = new Node(newValue, head);
        if(tail == null) tail = head;
        size++;
    }

    public int indexOf(Object value){
        Node iterator = head;
        for(int i = 0; iterator != null; i++, iterator = iterator.getNext())
            if(iterator.getValue().equals(value)) return i;
        return -1;
    }

    public Object get(int index) throws IndexOutOfBoundsException {
        if(index > size) throw new IndexOutOfBoundsException();

        Node iterator = head;
        for(int i = 0; iterator != null; i++){
            if(index == i) break;
            iterator = iterator.getNext();
        }
        return iterator.getValue();
    }

    public boolean remove(Object value){
        Node tmp = head, prev = null;
        boolean status = false;
        if(tmp != null && tmp.getValue().equals(value)){
            head = tmp.getNext();
            size--;
            return true;
        }

        for( ; tmp != null && !tmp.getValue().equals(value); prev = tmp, tmp = tmp.getNext());
        if(tmp != null){
            prev.setNext(tmp.getNext());
            size--;
            return true;
        }

        return false;
    }

    public Object removeFirst() throws NoElementsException {
        if(head == null) throw new NoElementsException();
        Object value = head.getValue();
        if(head == tail) head = tail = null;
        else head = head.getNext();
        size--;
        return value;
    }

    public Object removeLast() throws NoElementsException {
        if(head == null) throw new NoElementsException();
        Object value = tail.getValue();
        if(head == tail) head = tail = null;
        else{
            Node tmp;
            for(tmp = head; tmp.getNext() != tail; tmp = tmp.getNext());
            tail = tmp;
            tail.setNext(null);
        }
        size--;
        return value;
    }

    public Node getHead(){
        return this.head;
    }
    public Node getTail(){
        return this.tail;
    }
    public int getSize(){
        return this.size;
    }

    @Override
    public String toString(){
        String array = new String("[ ");

        for(Node iterator = head; iterator != null; iterator = iterator.getNext()){
            array = array.concat(iterator.getValue() + ", ");
        }

        array = array.substring(0, array.length()-2).concat(" ]");
        return array;
        
    }
}
