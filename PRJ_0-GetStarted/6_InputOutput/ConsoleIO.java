import java.io.*;

public class ConsoleIO {

    public static void main(String[] args){
        String str = null;
        try(BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter video = new BufferedWriter(new OutputStreamWriter(System.out))){
            str = keyboard.readLine();
            video.write(str);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
