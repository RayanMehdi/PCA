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
    private double vect[] = new double [taille];
    //Methodes

    public Vecteur(int taille) {
        this.taille = taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setVect(double[] vect) {
        this.vect = vect;
    }

    public int getTaille() {
        return taille;
    }

    public double[] getVect() {
        return vect;
    }
    

}
