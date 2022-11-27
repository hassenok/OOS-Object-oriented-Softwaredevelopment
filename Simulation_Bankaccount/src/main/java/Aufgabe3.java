import java.util.Scanner;

public class Aufgabe3 {
    public static void main(String [] args){
        Scanner nbr=new Scanner(System.in);
        int a=nbr.nextInt();
        String str ="";

        int i=2;
        while(i!=a){
            if((a/i)==0){
                a /= i;
                String s = (i) + "*";
                str=str+ s;
            }
            else
                ++i;
        }
        System.out.println(str);
    }
}
