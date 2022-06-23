public class ProducerConsumer {

    public static void main(String[] args){
        BoundedBuffer<Integer> buffer = new BoundedBuffer<Integer>(4);

        Producer p1 = new Producer(buffer);
        Consumer c1 = new Consumer(buffer);
        Consumer c2 = new Consumer(buffer);

        p1.start();
        c1.start();
        c2.start();
    }
}