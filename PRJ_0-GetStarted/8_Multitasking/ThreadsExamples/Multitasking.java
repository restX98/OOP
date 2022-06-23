public class Multitasking {
    /*  Esistono più modi per implementare un Thread in Java:
     *  Definendo una classe ed estendendola al Thread
     *  Definendo una classe ed implementarla a Runnable
     */

    public static void main(String[] args){

        ThreadGroup myThreadGroup = new ThreadGroup("My Thread's Group");

        ExtendedThread extendedThread = new ExtendedThread(myThreadGroup, "Thread of MyGroup"); 
        // Stato New Thread. L'unico metodo invocabile è start().
        System.out.println("My cystom group: " + extendedThread.getThreadGroup());

        extendedThread.start(); // Stato Runnable. Crea le risorse necessario per eseguire il thread.
                                // Diverso da Running.
        extendedThread.interrupt(); // Sveglia il thread dallo sleep().
                                    // Motivo per cui è necessario il catch.
        try{
            Thread.currentThread().sleep(2000); // Stato Not Runnable che si suddivide in vari stati.
            // Lo stesso stato può verificarsi in caso di operazioni di I/O
            // o di chiamata del metodo wait()
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }

        System.out.println("State of extendedThread: " + extendedThread.getState());
        System.out.println("ExtendedThread is alive? " + extendedThread.isAlive());
        
        Thread t1 = new Thread(new ImplementedRunnable());
        t1.start();
        // Stato Dead. Al termine del metodo run().

        // In questo caso il Main Thread termina prima dei thread secondari.
        // E' possibile far attendere al Main Thread la terminazione di un sotto
        // thread tramite il metodo join().

        try{
            t1.join();
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("Joinable Thread Ended");

        /* Esistono 2 tipi di thread, gli User Thread (precedenti) e i Deamon Thread.
         * I Deamon Thread vengono utilizzati per eseguire operazioni ad uso di altri
         * thread, ad esempio il Garbage Collector è un Deamon Thread.
         * Questi thread vengono eseguiti in background, quindi usano il provessore
         * solo quando altrimenti il tempo verrebbe perso.
         * Inoltre se il thread creante termina terminano anch'essi.
         */

         ExtendedThread daemonThread = new ExtendedThread();
         daemonThread.setDaemon(true);
         System.out.println("Is " + daemonThread + " a Deamon? " + daemonThread.isDaemon());

    }
}
