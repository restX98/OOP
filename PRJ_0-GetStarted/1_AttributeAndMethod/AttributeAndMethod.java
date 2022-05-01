class AttributeAndMethod{
    public static void main(String[] args){
        System.out.println("Attribute And Method Overview");

        // Posso chiamarlo prima di istanziare helper. Helper non è helper.
        Helper.genericTask();

        Helper helper = new Helper();

        // ERRORE:
        // helper.internalMethod();

        helper.methodOne(1, "Str1");
        helper.methodOne(1, "Str1", "Str2");

    }
}
