class Helper{

    int x = 10;
    int y;

    public static void changeXFail(int x, int value){
        x = value;
    }

    public static void changeXSuccess(Helper h, int value){
        h.x = value;
    }

    public void print(){
        System.out.println("X in helper vale: " + this.x);
    }

    // ! Non ritorna nessun errore cambiando il valore di ritorno.
    public int print(String str){
        System.out.println(str + this.x);
        return 0;
    }
}
