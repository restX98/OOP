// Se la classe contiene un metodo abstract deve esser definita anch'essa abstract
/* abstract  */class Helper{

    public int x;
    //int x // Attributo publico all'interno del package
    
    private float y; // Attributo accessibile solo da un metodo di questa classe
    protected boolean isTrue = true; // Accessibile dalla classe corrente e dalle sue sottoclassi.
    
    public static String className = "Helper"; // Attributo globale a tutte le istanze di questa classe. Se ne modifico uno li modifico tutti.

    private final String value = "Final String"; // Attributo modificabile qui o nel costruttore durante l'istanziamento

    private transient String psw; // Provando a scrivere su file un'istanza di questa classe verrà offuscato (Immagino settato a null) il suo valore

    private volatile int counter = 0; 
    /* Si utilizza in caso in cui l'oggetto sia condiviso tra dei thread, in particolare solo quando uno solo ne modifica
     * i valore e gli altri lo leggono. In pratica ogni core ha una cache in cui salva il valore delle variabili che
     * utilizza, nel caso dei due thread se l'attributo non fosse volatile non ci sarebbe certezza che il valore in cache
     * venga aggiornato prima che l'altro thread lo legga.
     * TODO: Da approfondire con i thread.
     */
    // http://tutorials.jenkov.com/java-concurrency/volatile.html 


    // I metodi private possono essere richiamati solo da un'altro metodo della stessa classe
    private void internalMethod(){
        // Do Stuff...
    }
    // E' possibile definire l'ultimo argomento come un array del tipo definito (varargs)
    public int methodOne(int x, String... args){
        for(int i=0; i<args.length; i++){
            internalMethod();
        }
        return x;
    }

    // I metodi statici possono essere chiamati senza istanziare un oggetto, quindi non lavorano sugli attributi
    public static void genericTask(){
        // Do Stuff...
    }

    // I metodi abastract necessitano l'implementazione da parte delle sottoclassi
    // public abstract void notDefined();

    // I metodi final non possono essere riscritti
    public final void notOverridable(){
        // Do Stuff...
    }
    

    // E' buona norma aggiungere i Getter e Setter per ogni attributo (anche public).
    public float getY(){
        return y;
    }
    public void setY(float y){
        this.y = y;
    }

    public boolean getIsTrues(){
        return isTrue;
    }
    public void setIsTrues(boolean isTrue){
        this.isTrue = isTrue;
    }

    public String getValue(){
        return value;
    }
    // ERROR:
    // public void setValue(String value){
    //     this.value = value;
    // }
}
