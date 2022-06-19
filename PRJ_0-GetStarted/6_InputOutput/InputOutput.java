import java.io.*;
import java.lang.*;

public class InputOutput {

    /* Java gestisce l'I/O tramite stream, uno stream è monodirezionale,
     * e può trasferire Byte o Caratteri.
     * java.io distingue tra 4 classi base astratte di stream:
     * Stream di Byte: InputStream e OutputStrea;
     * Stream di Caratteri: Reader e Writer;
     * Esistono classi di stream più specializzate che incapsulano quell
     * base per fornire delle funzionalità (Lettura da tastiera,
     * scrittura/lettura su file);
     * Gli stream di byte sono più efficienti degli stream di Byte.
     * Tutte le operazioni di input sono bloccanti (Si noti che potrebbe
     * essere bloccante anche un operazione di output su un socket).
     * java.nio offre Stream non bloccanti.
     */

    public static void main(String[] args){
        
        InputOutput.readWriteByte("text.bin");
        InputOutput.readWrite("text2.bin");
        InputOutput.randomAccessFile("text.dat");
        InputOutput.readWriteChar("text2.txt");

        // E' possibile reinterpretare uno StreamReader come un Reader nel caso
        // in cui trasmetta byte di caratteri tramite InputStreamReader e OutputStreamWriter

    }

    public static void readWriteByte(String fileName){

        // try-with-resources Statement - Da utilizzare sempre in
        //caso di istanza da chiudere;
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            FileInputStream fileInputStream = new FileInputStream(fileName);){

            fileOutputStream.write(3);
            fileOutputStream.write(5);
            fileOutputStream.write(6);

            System.out.println(fileInputStream.read()); // Legge un byte per volta.
                                                        // return 0-255;
            System.out.println(fileInputStream.read());
            System.out.println(fileInputStream.read());
            System.out.println(fileInputStream.read()); // return -1
        } catch (FileNotFoundException FNFEx){
            FNFEx.printStackTrace();
            System.exit(1);
        } catch (IOException IOEx){
            IOEx.printStackTrace();
            System.exit(1);
        }
    }

    public static void readWrite(String fileName){
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        try{
            // I Data_Stream permettono di estendere i File_Stream per leggere e scrivere
            // dati di lunghezza diversa al Byte.
            dataOutputStream = new DataOutputStream(new FileOutputStream(fileName));
            dataInputStream = new DataInputStream(new FileInputStream(fileName));

            dataOutputStream.writeInt(12);
            dataOutputStream.writeBoolean(true);

            
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readBoolean());
        } catch (FileNotFoundException FNFEx){
            FNFEx.printStackTrace();
            System.exit(1);
        } catch (IOException IOEx){
            IOEx.printStackTrace();
            System.exit(1);
        }finally{ // In alternativa a try-with-resources Statement
            try{
                if (dataOutputStream != null) dataOutputStream.close();
                if (dataOutputStream != null) dataInputStream.close();
            }catch(IOException IOEx){
                IOEx.printStackTrace();
            }
            
        }
    }

    public static void randomAccessFile(String fileName){
        
        try(RandomAccessFile raf = new RandomAccessFile(fileName, "rw")){
            System.out.println(raf.readLine());

        }catch(IOException IOEx){
            IOEx.printStackTrace();
            System.exit(1);
        }
    }

    public static void readWriteChar(String fileName){

        try(FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write("pippo");           
        }catch(IOException IOEx){
            IOEx.printStackTrace();
            System.exit(1);
        }
        // Chiudo il file e poi lo posso leggere. Gli altri non danno problemi! BHO!!
        try(FileReader fileReader = new FileReader(fileName)){
            int n=0, x = fileReader.read();
            while (x>=0) {
                char ch = (char) x;
                System.out.print(" " + ch); n++;
                x = fileReader.read();
            }
        }catch(IOException IOEx){
            IOEx.printStackTrace();
            System.exit(1);
        }
    }
}
