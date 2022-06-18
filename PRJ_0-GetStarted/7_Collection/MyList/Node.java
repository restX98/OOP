public class Node {
    private Node next;
    private Object value;

    public Node(Object value){
        this(value, null);
    }
    public Node(Object value, Node next){
        this.value = value;
        this.next = next;
    }

    public void setValue(Object value){
        this.value = value;
    }
    public Object getValue(){
        return this.value;
    }

    public void setNext(Node node){
        this.next = node;
    }
    public Node getNext(){
        return this.next;
    }
}
