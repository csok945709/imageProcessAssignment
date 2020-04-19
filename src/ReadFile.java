import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

    public static void main(String[] args) {
        try {
            FileInputStream myInputFile = new FileInputStream("images/yoda.tif");
            int value, j, i, k, first = 0,second = 0,m = 0;
            String hexValue = "";
            
            i = 1;
            System.out.println("-----------------------------Header Info-----------------------------");
                System.out.print("Byte Order : ");
                while((i<=2)&&((value = myInputFile.read())!=-1)){
                hexValue = String.format("%02x", value);
                System.out.print(hexValue.toUpperCase());
                i++;
                }
            System.out.println("");
            System.out.print("Version : ");
                while((i<=4)&&((value = myInputFile.read())!=-1)){
                hexValue = String.format("%02x", value);
                if(value==0){
                    hexValue = "";
                }
                System.out.print(hexValue.toUpperCase());
                i++;
                }
            System.out.println("");
            System.out.print("Offset : ");
                while((i<=8)&&((value = myInputFile.read())!=-1)){
                hexValue = String.format("%02x", value);
                if(value==0){
                    hexValue = "";
                }
                System.out.print(hexValue.toUpperCase());
                i++;
                }
            System.out.println("");
            System.out.println("-----------------------------Data-----------------------------");
            
            
            first = myInputFile.read();
            second = myInputFile.read();
            m = getM(first,second);
            
            System.out.println("");
            System.out.println("-----------------------------Data Entry-----------------------------");
            System.out.println("Tag Type Count Value");
            System.out.println("---------------------------------------------------------------------");
            
           

            
           ArrayList<String> num = new ArrayList<String>(); // Create an ArrayList object
            num.add("0");
            for(int array=1;array<=(12*m);array++){
                value = myInputFile.read();
                String data = String.format("%02X", value);
                num.add(data);
            }
            
            // System.out.println(num.get(192));
            int tag = 0;
            int type = 0;
            int length = 0;
            int fvalue = 0;
            int StripOff = 0;
            String name = "",ntype = "";
          
            for(j=0;j<m;j++){
                tag = getTag(num.get(1+(12*j)),num.get(2+(12*j)));
                type = getType(num.get(3+(12*j)),num.get(4+(12*j)));
                length = getLength(num.get(5+(12*j)),num.get(6+(12*j)),num.get(7+(12*j)),num.get(8+(12*j)));
                fvalue = getValue(num.get(9+(12*j)),num.get(10+(12*j)),num.get(11+(12*j)),num.get(12+(12*j)));
                String decimal_tag = String.format("%02X", tag);  

                if(type == 1 ){
                    ntype = "byte";
                }else if(type == 2){
                    ntype = "ASCII";
                }else if(type == 3){
                    ntype = "short";
                }else if(type == 4){
                    ntype = "long";
                }else if(type == 5){
                    ntype = "rational";
                }
                
                if(tag==254){
                    name = "NewSubfileType";
                    System.out.println(decimal_tag + "(" +name + ")                   " + type + "(" + ntype + ")" + "          " + 1 + "       " + fvalue);
                }else if(tag==256){
                    name = "Image width";
                    System.out.println(decimal_tag + "(" +name + ")                      " + type + "(" + ntype + ")" + "         " + 1 + "       " + fvalue);
                }else if(tag==257){
                    name = "Image height";
                    System.out.println(decimal_tag + "(" +name + ")                     " + type + "(" + ntype + ")" + "         " + 1 + "       " + fvalue);
                }else if(tag==258){
                    name = "Bits per sample";
                    System.out.println(decimal_tag + "(" +name + ")                  " + type + "(" + ntype + ")" + "         " + 1 + "       " + fvalue);
                }else if(tag==259){
                    name = "Compression";
                    System.out.println(decimal_tag + "(" +name + ")                      " + type + "(" + ntype + ")" + "         " + 1 + "       " + fvalue);
                }else if(tag==262){
                    name = "Photometric interpretation";
                    System.out.println(decimal_tag + "(" +name + ")       " + type + "(" + ntype + ")" + "         " + 1 + "       " + fvalue);
                }else if(tag==273){
                    name = "Strip offsets";
                    StripOff = fvalue;
                    System.out.println(decimal_tag + "(" +name + ")                    " + type + "(" + ntype + ")" + "          " + 1 + "       " + fvalue);
                }else if(tag==277){
                    name = "Samples per pixel";
                    System.out.println(decimal_tag + "(" +name + ")                " + type + "(" + ntype + ")" + "         " + 1 + "       " + fvalue);
                }else if(tag==278){
                    name = "Rows per strip";
                    System.out.println(decimal_tag + "(" +name + ")                   " + type + "(" + ntype + ")" + "          " + 1 + "       " + fvalue);
                }else if(tag==279){
                    name = "Strip byte counts";
                    System.out.println(decimal_tag + "(" +name + ")                " + type + "(" + ntype + ")" + "          " + 1 + "       " + fvalue);
                }else if(tag==282){
                    name = "X resolution";
                    System.out.println(decimal_tag + "(" +name + ")                     " + type + "(" + ntype + ")" + "      " + 1 + "       " + fvalue);
                }else if(tag==283){
                    name = "Y resolution";
                    System.out.println(decimal_tag + "(" +name + ")                     " + type + "(" + ntype + ")" + "      " + 1 + "       " + fvalue);
                }
            }
            System.out.println("");
            System.out.println("-----------------------------Image  Data-----------------------------");
            int x = (m*12)+1;
            int sixten = 16;
//            System.out.println(count);
            while((value = myInputFile.read())!=-1){
                if(x>(StripOff-10)){
                   if(sixten == 1){
                       System.out.println();
                       sixten = 16;
                   }
                   hexValue = String.format("%02X", value);
                   System.out.print(hexValue + " "); 
                   sixten--;
                }
                x++;
            }
            //counter 10 times go to new line
            k = 0;
            while ((value = myInputFile.read()) != -1) {

                hexValue = String.format("%02x", value);
                System.out.printf("%3S ", hexValue);
                k++;
                if (k == 10) {
                    System.out.println("");
                    k = 0;
                } 
            }
            
            myInputFile.close();
        } catch (IOException ex) {
            System.out.println("File Error!");
        }
    }
    public static int getM(int i, int m){
        int tag = m + i;
        return tag;
    }
    public static int getTag(String i, String m){

        String tag = m + i;
        int value = Integer.parseInt(tag,16);
        return value;
    }
    public static int getType(String i, String m){

         String tag = m + i;
        int value = Integer.parseInt(tag,16);
        return value;
    }
    public static int getLength(String i, String m, String j, String k){
        String tag = k + j + m + i;
        int value = Integer.parseInt(tag,16);
        return value;
    }
    public static int getValue(String i, String m, String j, String k){
        String tag = k + j + m + i;
        int value = Integer.parseInt(tag,16);
        return value;
    }
}
