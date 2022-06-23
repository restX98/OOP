public class Synchronization implements Runnable {

    public boolean isStatic;

    public Synchronization(){
        this(false);
    }
    public Synchronization(boolean isStatic){
        this.isStatic = isStatic;
    }

    public static void main(String[] args){
        try{
            // Uncomment to try:
            // Synchronization.sameInstance();
            // Synchronization.differentInstance();
            // Synchronization.staticSynchronization();
            //Synchronization.synchronizedStatements();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static void sameInstance(){
        Synchronization sync = new Synchronization();
        Thread t1 = new Thread(sync);

        t1.start(); // t1 chiama printInt() di sync attraverso run()
        sync.printChar(); // main chiama printChar() di sync
        // Essendo entrambi i metodi dichiarati synchronized  il primo thread
        // scelto dallo scheduler acquisirà il lock su sync.
    }

    public static void differentInstance(){
        Synchronization sync1 = new Synchronization();
        Thread t1 = new Thread(sync1);
        Synchronization sync2 = new Synchronization();
        
        t1.start(); // t1 chiama printInt() di sync1 attraverso run()
        sync2.printChar(); // main chiama printChar() di sync2
        // Entrambi i thread procederanno in maniera concorrente.
    }

    public static void staticSynchronization(){
        Synchronization sync1 = new Synchronization(true);
        Thread t1 = new Thread(sync1);
        
        t1.start();
        sync1.staticPrintChar();
        // In caso di metodi statici agirà una sincronizzazione a livello di classe.
        // Chiamando un metodo synchronized ma non statico verrà eseguito simultaneamente.
    }

    public static void synchronizedStatements(){
        Synchronization sync = new Synchronization();
        Thread t1 = new Thread(sync);
        
        t1.start();
        synchronized(sync){
            sync.notSynchronizedPrintChar();
        }

    }

    public synchronized static void staticPrintInt(){
        try{
            System.out.println("Static Method PrintInt");
            for(int i = 1; i<6; i++) {
                System.out.println(Thread.currentThread() + ": " + i);
                Thread.currentThread().sleep(1000);
            }
        }catch(InterruptedException IEx){
            IEx.printStackTrace();
        }
    }

    public synchronized static void staticPrintChar(){
        try{
            System.out.println("Static Method PrintChar");
            for(char c = 'A'; c<'F'; c++) {
                System.out.println(Thread.currentThread() + ": " + c);
                Thread.currentThread().sleep(1000);
            }
        }catch(InterruptedException IEx){
            IEx.printStackTrace();
        }
    }


    @Override
    public void run(){
        if(this.isStatic) this.staticPrintInt();
        else this.printInt();
    }

    public synchronized void printInt(){
        try{
            for(int i = 1; i<6; i++) {
                System.out.println(Thread.currentThread() + ": " + i);
                Thread.currentThread().sleep(1000);
            }
        }catch(InterruptedException IEx){
            IEx.printStackTrace();
        }
    }

    public synchronized void printChar(){
        try{
            for(char c = 'A'; c<'F'; c++) {
                System.out.println(Thread.currentThread() + ": " + c);
                Thread.currentThread().sleep(1000);
            }
        }catch(InterruptedException IEx){
            IEx.printStackTrace();
        }
    }

    public void notSynchronizedPrintChar(){
        try{
            System.out.println("Not Synchronized PrintChar");
            for(char c = 'A'; c<'F'; c++) {
                System.out.println(Thread.currentThread() + ": " + c);
                Thread.currentThread().sleep(1000);
            }
        }catch(InterruptedException IEx){
            IEx.printStackTrace();
        }
    }
}