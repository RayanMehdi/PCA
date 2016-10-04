/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pca;

/**
 *
 * @author rayanmehdi1
 */
public class Vecteur {
    //Attributs
    private int taille;
    private double vect[];
    //Methodes
    
    public void aff_vecteur(){
       for( int i = 0; i < taille; i++){
           System.out.println("["+i+"] "+vect[i]);
       }
        System.out.println("fin");
    }

    public double getElement(int i){
        return vect[i];
    }
    
    public Vecteur(int taille) {
        this.taille = taille;
        vect = new double[taille];
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setVect(double[] vect) {
        this.vect = vect;
    }
    
    public void setElement(int i, double d){
        this.vect[i] = d;
    }

    public int getTaille() {
        return taille;
    }

    public double[] getVect() {
        return vect;
    }
    

}
