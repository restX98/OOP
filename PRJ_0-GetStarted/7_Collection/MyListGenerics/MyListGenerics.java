import java.util.*;

public class MyListGenerics<T> {
    
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyListGenerics(){
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    public void add(T newValue){
        if(head == null) head = tail = new Node<T>(newValue);
        else {
            tail.setNext(new Node<T>(newValue));
            tail = tail.getNext();
        }
        size++;
    }
    
    public void addLast(T newValue){
        this.add(newValue);
    }

    public void addFirst(T newValue){
        head = new Node<T>(newValue, head);
        if(tail == null) tail = head;
        size++;
    }

    public int indexOf(T value){
        Node<T> iterator = head;
        for(int i = 0; iterator != null; i++, iterator = iterator.getNext())
            if(iterator.getValue().equals(value)) return i;
        return -1;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if(index > size) throw new IndexOutOfBoundsException();

        Node<T> iterator = head;
        for(int i = 0; iterator != null; i++){
            if(index == i) break;
            iterator = iterator.getNext();
        }
        return iterator.getValue();
    }

    public boolean remove(T value){
        Node<T> tmp = head, prev = null;
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

    public T removeFirst() throws NoElementsException {
        if(head == null) throw new NoElementsException();
        T value = head.getValue();
        if(head == tail) head = tail = null;
        else head = head.getNext();
        size--;
        return value;
    }

    public T removeLast() throws NoElementsException {
        if(head == null) throw new NoElementsException();
        T value = tail.getValue();
        if(head == tail) head = tail = null;
        else{
            Node<T> tmp;
            for(tmp = head; tmp.getNext() != tail; tmp = tmp.getNext());
            tail = tmp;
            tail.setNext(null);
        }
        size--;
        return value;
    }

    public Node<T> getHead(){
        return this.head;
    }
    public Node<T> getTail(){
        return this.tail;
    }
    public int getSize(){
        return this.size;
    }

    @Override
    public String toString(){
        String array = new String("[ ");

        for(Node<T> iterator = head; iterator != null; iterator = iterator.getNext()){
            array = array.concat(iterator.getValue() + ", ");
        }
        array = array.substring(0, array.length()-2).concat(" ]");
        return array;
    }
}
