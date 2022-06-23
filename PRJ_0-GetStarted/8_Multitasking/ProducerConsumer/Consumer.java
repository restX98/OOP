public class Consumer extends Thread {

    public BoundedBuffer<Integer> buffer;

    public Consumer(BoundedBuffer<Integer> buffer){
        super();
        this.buffer = buffer;
    }

    @Override
    public void run(){
        for(int i = 0; ; i++){
            try{
                System.out.println("Consumer: " + buffer.remove() + " BUF: " + buffer);
                Thread.currentThread().sleep(6000);
            } catch(InterruptedException ex){}
        }
    }
}