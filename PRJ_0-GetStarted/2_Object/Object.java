class Object{
    public static void main(String[] args){
        System.out.println("Reference:");

        Helper helper = new Helper();
        helper.print();

        Helper.changeXFail(helper.x, 15);
        helper.print("Non è ancora cambiato: ");

        Helper.changeXSuccess(helper, 15);
        helper.print("E ora si: ");

        helper.y = 30;
        System.out.println(helper);
        System.out.println(helper.toString());
        
        
        // possibile utilizzo dello scope:
        // attualmente x = 15 e y = 30
        {
            // Dichiariamo tmp dentro un nested scope così da non conservarlo in memoria per tutta la durata del main
            int tmp = helper.x;
            helper.x = helper.y;
            helper.y = tmp;
        }
        helper.print();
        System.out.println("Y in helper vale: " + helper.y);
        // ERRORE:
        // System.out.println("tmp vale: " + tmp);
    }
}
