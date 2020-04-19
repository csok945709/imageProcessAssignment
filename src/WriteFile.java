import java.io.*;

public class WriteFile {
    public static void main(String[] args) {
        //File myOutputFile
        try{
            FileOutputStream myOutputFile = new FileOutputStream("myFile.txt");
            myOutputFile.write(1);
            myOutputFile.write(2);
            myOutputFile.write('t');
            myOutputFile.write('h');
            myOutputFile.write('i');
            myOutputFile.write('s');
            myOutputFile.close();
        }catch(IOException ex){
            System.out.println("File Output Error! ");
        }
        
    }
}
