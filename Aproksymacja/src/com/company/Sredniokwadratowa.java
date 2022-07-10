package com.company;

public class Sredniokwadratowa {

    static int n;

    static double x = -0.25;

    static double b = 1;
    static double a = -1;

    public Sredniokwadratowa(int n) {
        this.n = n;
    }

    static double funkcja(double x){

        return Math.sqrt(3 + 7 * Math.pow(x,2));
    }

    static double px = 1;

    public static double Całka(double x, int i, double px){
        double f = Math.pow(x, i) * funkcja(x) * px ;
        return f;
    }
    public static double Całka2(double x, int i, int j, double px){
        double f2 = Math.pow(x, i) * Math.pow(x, j) * px ;
        return f2;
    }

    void Oblicz()
    {
        double C1 = 20;
        double[] tab = new double[n+1];
        for ( int i = 0; i < n+1; i++){
            for ( int j = 0; j < C1; j ++){
                double x1 = a + ((j/C1) * (b-a));
                double x2 = a + (((j+1)/C1) * (b-a));
                double t = (x2 + x1) / 2;
                double h = (x2 - x1) / 2;
                tab[i] = tab[i] + ((h/3) * (Całka(x1, i, px) + (4*(Całka(t, i, px))) +
                        Całka(x2, i ,px)) );
            }
        }
        double C = 20;
        double[][] tablica = new double[n+1][n+1];
        for ( int i = 0; i < n+1; i++){
            for ( int j = 0; j < n+1; j ++){
                for ( int k = 0; k < C; k++){
                    double x1 = a + ((k/C) * (b-a));
                    double x2 = a + (((k+1)/C) * (b-a));
                    double t = (x2 + x1) / 2;
                    double h = (x2 - x1) / 2;
                    tablica[i][j] = tablica[i][j] + ((h/3) * (Całka2(x1, i, j, px) + (4*(Całka2(t, i, j, px)))
                            + Całka2(x2, i, j, px)) );
                }
            }
        }
        double[] Obliczanie = new double[tab.length];
        double[][] temp = new double[tablica.length][tablica.length];
        for (int i = 0; i < tablica.length; i++) {
            temp[0][i] = tablica[0][i];
        }
        Obliczanie[0] = tab[0];
        for (int i = 0; i < tablica.length; i++) {
            for (int k = i + 1; k < tablica.length; k++) {
                Obliczanie[k] = tab[k] - (tab[i] * (tablica[k][i] / tablica[i][i]));
                for (int j = i + 1; j < tablica.length; j++) {
                    temp[j][k] = tablica[j][k] - (tablica[j][i] / tablica[i][i]) * tablica[i][k];
                }
            }
            for (int z = 0; z < temp.length; z++) {
                for (int v = 0; v < temp[z].length; v++) {
                    tablica[z][v] = temp[z][v];
                }
                tab[z] = Obliczanie[z];
            }
        }
        Obliczanie[tab.length-1] = Obliczanie[Obliczanie.length-1] / tablica[tablica.length-1][tablica.length-1] ;
        for ( int i = tab.length-2; i > -1; i--){
            for ( int j = i + 1; j < tab.length; j++){
                Obliczanie[i] = Obliczanie[i] - tablica[i][j] * Obliczanie[j];
            }
            Obliczanie[i] = Obliczanie[i] / tablica[i][i];
        }
        double Wx = 0;
        for ( int i = 0 ; i < Obliczanie.length; i++){
            Wx = Wx + Obliczanie[i] * Math.pow(x, i);
        }
        System.out.println("Wynik Sredniokwadratowa: " + Wx);
    }

}

