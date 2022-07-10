package com.company;

public class Najmniejszych_kwadratow {

    double[] xi = {-1,-0.5,0,0.5,1};
    double[] yi = {funkcja(-1), funkcja(-0.5), funkcja(0), funkcja(0.5), funkcja(1)};
    double x = -0.25;
    int m;
    int n = xi.length;

    public Najmniejszych_kwadratow(int m) {
        this.m = m;
    }

    public static double[] gaussianElimination(double mat[][])
    {
        int N = mat[0].length-1;

        int singular_flag = forwardElim(mat);

        if (singular_flag != -1)
        {
            System.out.println("Pojedyncza macierz");

            if (mat[singular_flag][N] != 0)
                System.out.print("Niespójny system");
            else
                System.out.print(
                        "Może mieć nieskończenie wiele rozwiązań");
        }

        double[] x = backSub(mat);
        return x;
    }

    public static void swap_row(double mat[][], int i, int j)
    {
        int N = mat[0].length-1;
        for (int k = 0; k <= N; k++)
        {
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }

    public static void print(double mat[][])
    {
        int N = mat[0].length-1;
        for (int i = 0; i < N; i++, System.out.println())
            for (int j = 0; j <= N; j++)
                System.out.print(mat[i][j]);
        System.out.println();
    }

    public static int forwardElim(double mat[][])
    {
        int N = mat[0].length-1;
        for (int k = 0; k < N; k++)
        {

            int i_max = k;
            int v_max = (int)mat[i_max][k];


            for (int i = k + 1; i < N; i++)
                if (Math.abs(mat[i][k]) > v_max)
                {
                    v_max = (int)mat[i][k];
                    i_max = i;
                }

            if (mat[k][i_max] == 0)
                return k;

            if (i_max != k)
                swap_row(mat, k, i_max);

            for (int i = k + 1; i < N; i++)
            {
                double f = mat[i][k] / mat[k][k];
                for (int j = k + 1; j <= N; j++)
                    mat[i][j] -= mat[k][j] * f;
                mat[i][k] = 0;
            }
        }
        return -1;
    }

    public static double[] backSub(double mat[][])
    {
        int N = mat[0].length-1;
        double x[] = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            x[i] = mat[i][N];
            for (int j = i + 1; j < N; j++)
            {
                x[i] -= mat[i][j] * x[j];
            }
            x[i] = x[i] / mat[i][i];
        }
        return x;
    }

    public static double funkcja(double x)
    {
        return Math.sqrt(3 + 7 * Math.pow(x,2));
    }


    public static double S(int k,int j,int n,double[] arg)
    {
        double suma = 0;
        for (int i = 0; i < n; i++) {
            suma += Math.pow(arg[i],k+j);
        }
        return suma;
    }

    public static double T(int k,int n,double[] args,double[] wart)
    {
        double suma=0;
        for (int i = 0; i < n; i++) {
            suma += Math.pow(args[i],k) * wart[i];
        }
        return suma;
    }

    public static double[] najmn_kwadrat(double[] arg,double[] wart,int n,int m)
    {
        double[][] macierz = new double[m+1][m+2];
        for (int i = 0; i < macierz.length; i++) {
            for (int j = 0; j < macierz[i].length; j++) {
                if(j < macierz[i].length-1)
                    macierz[i][j] = S(i,j,n,arg);
                else
                    macierz[i][j] = T(i,n,arg,wart);
            }
        }

        double[] gauss = gaussianElimination(macierz);
        return gauss;
    }

    void Wyswietl()
    {
        System.out.println("Wynik Najmniejszych kwadratow: " + Oblicz(x,m,najmn_kwadrat(xi, yi,n,m)));

    }

    public static double Oblicz(double x, int m, double[] wynik_gaussa)
    {
        double suma=0;
        for (int i = 0; i <= m; i++) {
            suma += wynik_gaussa[i] * Math.pow(x,i);
        }
        return suma;
    }

}
