public class Matrix {
    public static void main (String[]args){
        final int i=3,j=2,k=3;
        int a[][]=new int [i][j];
        int b[][]=new int [j][k];
        int c[][]=new int [i][k];
        //Initialisierung
        for(int zeilen=0;zeilen<i;zeilen++){
            for(int spalten=0;spalten<j;spalten++){
                a[zeilen][spalten]=10*zeilen+spalten;
            }
        }
        for(int zeilen=0;zeilen<j;zeilen++){
            for(int spalten=0;spalten<k;spalten++){
                b[zeilen][spalten]=zeilen+10*spalten;
            }
        }
        //Ausgabe
        System.out.println("A::");
        for(int zeilen=0;zeilen<i;zeilen++){
            for(int spalten=0;spalten<j;spalten++){
                System.out.print(a[zeilen][spalten]+" ");
            }
            System.out.println();
        }
        System.out.println("B::");
        for(int zeilen=0;zeilen<j;zeilen++){
            for(int spalten=0;spalten<k;spalten++){
                System.out.print(b[zeilen][spalten]+" ");
            }
            System.out.println();
        }
        // Matrizen Multiplikation
        for (int x=0;x<3;x++){
            for(int y =0;y<2;y++){
                for(int z=0;z<3;z++){
                    c[x][z]+=a[x][y]*b[y][z];
                }


            }
        }



        System.out.println("C::");
        for(int zeilen=0;zeilen<i;zeilen++){
            for(int spalten=0;spalten<k;spalten++){
                System.out.print(c[zeilen][spalten]+" ");
            }
            System.out.println();
        }
    }
}
