package com.orbits;

public class Main {
    public static void main(String[] args) {
        double Vi= 0;
        double Pi= 20;
        double g= 9.8;
        double dt= 0.01;
        double m= 7;
 
        while(Pi>0){
            double Vf= Vi + g*dt;
            double Pf= Pi + Vf*dt;
            Pi= Pf;
            Vi= Vf;
            double t=0;
            t= t + dt;
            System.out.println("Cambio en velocidad:" + " "+ Vf);
            System.out.println("Cambio en posicion:" + " " +Pf);
            System.out.println("Paso de dt" + " " + t);
        }
    }
}