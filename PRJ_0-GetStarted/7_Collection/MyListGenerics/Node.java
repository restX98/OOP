public class Node<T> {
    private Node<T> next;
    private T value;

    public Node(T value){
        this(value, null);
    }
    public Node(T value, Node<T> next){
        this.value = value;
        this.next = next;
    }

    public void setValue(T value){
        this.value = value;
    }
    public T getValue(){
        return this.value;
    }

    public void setNext(Node<T> node){
        this.next = node;
    }
    public Node<T> getNext(){
        return this.next;
    }
}
