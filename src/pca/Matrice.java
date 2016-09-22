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
public class Matrice {
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
                System.out.print(this.matr[j][i]+" | ");
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
    
}