public class Anwendungen {
    public static void main(String []args)
    {
        int [][] dreieck = new int [3][];
        int fuellung=1;
        for (int i=0;i<dreieck.length;i++){
            dreieck[i]=new int[i+1];
            for (int j=0;j<i+1;j++){
                dreieck[i][j]=fuellung++;
                System.out.print(dreieck[i][j]);
            }
            System.out.println();
        }
        char c = 'c';
        int i = c;
        long l = i;
        System.out.println(l);
        l= i;
        boolean test;
        test =true || false ;
        System.out.println(test);
        test=true||true;
        System.out.println(test);


    }


}
