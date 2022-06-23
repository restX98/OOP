public class Producer extends Thread {

    public BoundedBuffer<Integer> buffer;

    public Producer(BoundedBuffer<Integer> buffer){
        super();
        this.buffer = buffer;
    }

    @Override
    public void run(){
        for(int i = 0; ; i++){
            try{
                buffer.insert(i);
                System.out.println("Producer: " + i + " BUF: " + buffer);
                System.out.println(buffer.toStringArray());
                Thread.currentThread().sleep(2000);
            } catch(InterruptedException ex){}
        }
    }
}