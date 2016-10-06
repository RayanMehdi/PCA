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
public class Vecteur implements Cloneable{
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
    
    public Object clone() {
		Object o = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la 
			// méthode super.clone()
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implémentons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return o;
	}
    

}
