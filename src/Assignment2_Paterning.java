
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Assignment2_Paterning {
    public static void main(String[] args) {
        try{
            Scanner myObj = new Scanner(System.in);
            String file;
            System.out.println("Enter Image Name : "); 
            file = myObj.nextLine();
            FileInputStream myInputFile = new FileInputStream("images/"+file+".raw");
            FileOutputStream myOutputFile = new FileOutputStream("images/output/"+file+"_Assignment2_Patterning.raw");
            int value;
            int i=0;
            Scanner input_width = new Scanner(System.in);
            System.out.println("Enter Width : "); 
            int width = input_width.nextInt();
            
            Scanner input_height = new Scanner(System.in);
            System.out.println("Enter Height : "); 
            int height = input_height.nextInt();
            
            int[][] data = new int[((height+1)*3)][((width+1)*3)];
            int num_pat = 0;
            int[][] pattern = { { 0, 0, 0 }, { 0, 0, 0 },{ 0, 0, 0 } };
            int[][] pattern1 = { { 0, 0, 0 }, { 0, 0, 0 },{ 0, 0, 255 } };
            int[][] pattern2 = { { 255, 0, 0 }, { 0, 0, 0 },{ 0, 0, 255 } };
            int[][] pattern3 = { { 255, 0, 255 }, { 0, 0, 0 },{ 0, 0, 255 } };
            int[][] pattern4 = { { 255, 0, 255 }, { 0, 0, 0 },{ 255, 0, 255 } };
            int[][] pattern5 = { { 255, 0, 255 }, { 0, 0, 0 },{ 255, 255, 255 } };
            int[][] pattern6 = { { 255, 0, 255 }, { 255, 0, 0 },{ 255, 255, 255 } };
            int[][] pattern7 = { { 255, 255, 255 }, { 255, 0, 0 },{ 255, 255, 255 } };
            int[][] pattern8 = { { 255, 255, 255 }, { 255, 0, 255 },{ 255, 255, 255 } };
            int[][] pattern9 = { { 255, 255, 255 }, { 255, 255, 255 },{ 255, 255, 255 } };
              int start_Width = 0;
              int start_Height = 0;
            while((value = myInputFile.read())!=-1){
                num_pat = getPattern(value);
                if((i%width==0)&&(i!=0)){
                    start_Height = start_Height + 3;
                    start_Width = 0;
                }
                switch (num_pat) {
                    case 0:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern[m][j];
                            }
                        }   break;
                    case 1:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern1[m][j];
                            }
                        }   break;
                    case 2:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern2[m][j];
                            }
                        }   break;
                    case 3:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern3[m][j];
                            }
                        }   break;
                    case 4:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern4[m][j];
                            }
                        }   break;
                    case 5:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern5[m][j];
                            }
                        }   break;
                    case 6:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern6[m][j];
                            }
                        }   break;
                    case 7:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern7[m][j];
                            }
                        }   break;
                    case 8:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern8[m][j];
                            }
                        }   break;
                    case 9:
                        for (int m = 0; m < 3; m++) {
                            for (int j = 0; j < 3; j++) {
                                data[start_Height+m][start_Width+j]  = pattern9[m][j];
                            }
                        }   break;
                    default:
                    break;
                }
                start_Width = start_Width + 3;
                i++;
            }      
            for(int count = 0; count < (height*3); count++){
                for(int num = 0; num < (width*3); num++){
                      myOutputFile.write(data[count][num]);
                }
            }
            myInputFile.close();
            myOutputFile.close();
        }catch(IOException ex){
            System.out.println("File Error!!!");
        }
    }
    public static int getPattern(int value){
        int data=0;
        if(value<=25){
             data = 0;
        }else if((value>25)&&(value<=50)){
             data = 1;
        }else if((value>50)&&(value<=75)){
             data = 2;
        }else if((value>75)&&(value<=100)){
             data = 3;
        }else if((value>100)&&(value<=125)){
             data = 4;
        }else if((value>125)&&(value<=150)){
             data = 5;
        }else if((value>150)&&(value<=175)){
             data = 6;
        }else if((value>175)&&(value<=200)){
             data = 7;
        }else if((value>200)&&(value<=225)){
             data = 8;
        }else{
             data = 9;
        }
        return data;
    }
}
