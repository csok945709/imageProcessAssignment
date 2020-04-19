
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Assignment3_Histogram {
    public static void main(String[] args) {
        try{
            Scanner myObj = new Scanner(System.in);
            
            String file;
            System.out.println("Enter Image Name : "); 
            file = myObj.nextLine();
            FileInputStream myInputFile = new FileInputStream("images/"+file+".raw");
            FileOutputStream myOutputFile = new FileOutputStream("images/output/"+file+".Assignment3_Histogram.raw");
            
            Scanner input_width = new Scanner(System.in);
            System.out.println("Enter Width : "); 
            int width = input_width.nextInt();
            
            Scanner input_height = new Scanner(System.in);
            System.out.println("Enter Height : "); 
            int height = input_height.nextInt();
            
            int data[] = new int [256];
            int image[] = new int [width*height];
            int num[] = new int [256];
            int pixel[] = new int [256];
            int value;
            int m=0;
            int sum = 0;
            double nor  = 0.0;
            int mutiple = 0;
            int total = 0;
            while((value = myInputFile.read())!=-1){
                image[m] = value;
                data[value] = data[value]+1;    
                m++;
            }
            for(int i=0;i<256;i++){
                sum  += data[i];
                nor = ((double)sum/m);
                mutiple = (int) Math.round(nor*255);
                num[i] = mutiple;
                
                if(i!=0){
                    if(num[i]==num[i-1]){
                        pixel[mutiple] = pixel[mutiple]+data[i];
                    }else{
                        pixel[mutiple] = data[i];
                    }
                }else{
                    pixel[mutiple] = data[i];
                }
            }
            for(int k=0;k<m;k++){
                  image[k] = num[image[k]];
                  myOutputFile.write(image[k]);
            }
            
            myInputFile.close();
            myOutputFile.close();
        }catch(IOException ex){
            System.out.println("File Error!!!");
        }
    }
}
