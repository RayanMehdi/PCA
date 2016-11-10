/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pca;


import java.util.Random;

/**
 *
 * @author rayanmehdi1
 */
public class Matrice implements Cloneable {
    //Attributs
    private int lignes;
    private int colonnes;
    private double[][] matr;
    //Methodes
  
    public Matrice(int Ligne, int Colonnes){
        this.lignes=Ligne;
        this.colonnes=Colonnes;
        matr = new double[lignes][colonnes];
        /*for( int i = 0 ; i < lignes ; i++){
            for( int j = 0 ; i < colonnes ; j++){
                
            }
        }*/
    }
   
    
    public void aff_matrice(){
        for(int i = 0; i < this.colonnes; i++){
            for( int j = 0; j < this.lignes; j++){
                System.out.print(this.matr[i][j]+", ");
            }
            System.out.println();
        }
    }
    
    
    
    
    
    
    public void setLigne(int ligne){
        this.lignes=ligne;
    }
    public void setColonnes(int colonnes){
        this.colonnes = colonnes;
    }
    public void setElement(int ligne, int colonne, double element){
        matr[ligne][colonne]=element;
    }
    public int getLigne(){
        return lignes;
    }
    public int getColonnes(){
        return colonnes;
    }
    public double getElement(int ligne, int colonne){
        return matr[ligne][colonne];
    }
    
    public Vecteur random(){
        Random rn = new Random();
        //rn.setSeed(124);
        Vecteur v= new Vecteur(this.getLigne());
        int li = rn.nextInt(this.getLigne());
        for (int i = 0; i < this.getLigne(); i++) {
            v.setElement(i,this.getElement(i, li));
        }
        return v;
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