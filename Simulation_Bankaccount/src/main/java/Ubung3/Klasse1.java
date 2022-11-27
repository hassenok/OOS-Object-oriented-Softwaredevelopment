package Ubung3;

public class Klasse1 {

    public static void main(String [] args){
        Klasse2 klasse2 = null;
        klasse2.pro=0;
        klasse2.pub=0;
        klasse2.fri=0;
        //klasse2.pri=0; //nicht moeglich da es private ist
        Klasse3 klasse3=new Klasse3();
        klasse3.pro=0;
        klasse3.pub=0;
        klasse3.fri=0;
        //klasse3.pri=0;//nicht moeglich da es private ist


    }
     class Klasse2{
        public int pub;
        protected int pro;
        int fri;
        private int pri;


    }
}
