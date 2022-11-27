package Ubung3;

public class AusnahmeBsp {
    int att;
    void nullpointer(){
        AusnahmeBsp ausnahmeBsp=null;
        ausnahmeBsp.att=1;
       // ausnahmeBsp=null;
    }
    public static void main(String[]args){
        try{
        AusnahmeBsp ab= new AusnahmeBsp();
        ab.nullpointer();}catch (NullPointerException e){
            System.out.println(" Variable ist nicht initialisiert");
        }
    }
}
