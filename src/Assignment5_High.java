
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Assignment5_High {
    public static void main(String[] args) {
        try{
            Scanner myObj = new Scanner(System.in);
            
            String file;
            System.out.println("Enter Image Name : "); 
            file = myObj.nextLine();
            FileInputStream myInputFile = new FileInputStream("images/"+file+".raw");
            FileOutputStream myOutputFile = new FileOutputStream("images/output/"+file+"_Assignemnt5_HighPass.raw");
            int value;
            int i=0;
            Scanner input_width = new Scanner(System.in);
            System.out.println("Enter Width : "); 
            int width = input_width.nextInt();
            
            Scanner input_height = new Scanner(System.in);
            System.out.println("Enter Height : "); 
            int height = input_height.nextInt();
            
            int[][] data = new int[height][width];
            int start_Width = 0;
            int start_Height = 0;
            while((value = myInputFile.read())!=-1){
                if((i%width==0)&&(i!=0)){
                    start_Height++;
                    start_Width = 0;
                }
                data[start_Height][start_Width]  = value;
                start_Width++;
                i++;
            }
            for(int m=0;m<height;m++){
                for(int n=0;n<width;n++){
                    if((n==0)||(m==0)||(m==(height-1))||(n==(width-1))){
                        data[m][n]=255;
                    }
                }
            }
            
            int[][] convolution = { { -1, -1, -1 }, { -1, 8, -1 },{ -1, -1, -1 } };
            int sum = 0;
            int[][] f_data = new int[height][width];
            for(int y=1;y<(height-2);y++){
                for(int x=1;x<(width-2);x++){
                    sum = ((convolution[0][0]*data[y+1][x+1])+
                            (convolution[1][0]*data[y+1][x])+
                            (convolution[2][0]*data[y+1][x-1])+
                            (convolution[0][1]*data[y][x+1])+
                            (convolution[1][1]*data[y][x])+
                            (convolution[2][1]*data[y][x-1])+
                            (convolution[0][2]*data[y-1][x+1])+
                            (convolution[1][2]*data[y-1][x])+
                            (convolution[2][2]*data[y-1][x-1]));
                    f_data[y][x] = sum;
                    if(sum<0){
                        f_data[y][x] = 0;
                    }else if(sum>255){
                        f_data[y][x] = 255;
                    }
                }
            }
            
            for(int k=0;k<height;k++){
                for(int l=0;l<width;l++){
                        myOutputFile.write(f_data[k][l]);
                }
            }
            
            myInputFile.close();
            myOutputFile.close();
        }catch(IOException ex){
            System.out.println("File Error!!!");
        }
    }
}
