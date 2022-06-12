public class Examples {

    /* Una eccezione è un oggetto, istanza di Throwable o di una sua
     * sottoclasse: Exception e Error
     * Error è utilizzato per errori relatici alla JVM (ThreadDeat).
     * Exception rappresenta errori durante l'esecuzione del codice
     * (ArrayIndexOutOfBoundsException).
     * 
     */


    public static void main(String[] args) {

        try{
            Examples.myExceptionMethod(true);
            System.out.println("Io salto");
        } /*catch (Exception ex) { // Errore in compilazione
            System.out.println("E gli altri?");
        } */catch(ArrayIndexOutOfBoundsException IOBEx){
            IOBEx.printStackTrace();
            return;
        } catch(MyException MyEx){
            MyEx.printStackTrace();
            return;
        } catch(Exception ex){
            System.out.println("Da aggiungere solo alla fine per eventuali altre eccezioni");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            System.out.println(ex.toString());
        } finally {
            System.out.println("...dal finally pero' ci passa!");
        }

        System.out.println("Il return dentro il primo catch non mi fa arrivare qui,...");

    }

    public static void myExceptionMethod(boolean isMine)
            throws ArrayIndexOutOfBoundsException, MyException {
        
        if(isMine){
            throw new MyException("Custom Exception");
        } else {
            Integer[] arr = new Integer[5];
            arr[5] = 10;
        }
    }


}