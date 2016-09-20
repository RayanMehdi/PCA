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
    private int Ligne;
    private int Colonnes;
    private double Matr[][]=new double [Ligne][Colonnes];
    //Methodes
    public Matrice(int Ligne, int Colonnes){
        this.Ligne=Ligne;
        this.Colonnes=Colonnes;
    }
    public void setLigne(int ligne){
        Ligne=ligne;
    }
    public void setColonnes(int colonnes){
        Colonnes = colonnes;
    }
    public void setElement(int ligne, int colonne, double element){
        Matr[ligne][colonne]=element;
    }
    public int getLigne(){
        return Ligne;
    }
    public int getColonnes(){
        return Colonnes;
    }
    public double getElement(int ligne, int colonne){
        return Matr[ligne][colonne];
    }
    
}