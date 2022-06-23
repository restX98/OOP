public class ImplementedRunnable implements Runnable {
    
    @Override
    public void run(){
        System.out.println("Started " + Thread.currentThread());

        try{
            for(int i = 0; i<5; i++){
                System.out.println(i);
                Thread.currentThread().sleep(1000);
            }
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("Ended " + Thread.currentThread());
    }
}
