/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pca;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
                System.out.print(this.matr[j][i]+", ");
            }
            System.out.println();
        }
    }
    
    public void creerImage(){
        
        try{
        
        //TYPE_INT_RGB or TYPE_BYTE_GRAY
        BufferedImage b = new BufferedImage(this.lignes, this.colonnes, BufferedImage.TYPE_INT_ARGB);
        int[] pixels = new int[this.lignes * this.colonnes];
        for(int x = 0; x < this.lignes; x++) {
            for(int y = 0; y < this.colonnes; y++) {
                
                               
                if (this.matr[x][y] < 0) {
                    this.matr[x][y] = this.matr[x][y] * -1;
                }
                //this.matr[x][y] = this.matr[x][y]%255;
                pixels[y*colonnes + x] = new Color((int)this.matr[x][y]).getRGB();
                /*
                    Color col = new Color((float)this.matr[x][y], (float)this.matr[x][y] , (float)this.matr[x][y]);
                    //Color col = new Color((int)this.matr[x][y]);
                    int rgb = col.getRGB();
                    System.out.println(rgb);
                    
                    //int rgb = (int)this.matr[x][y];
                    b.setRGB(y, x, rgb);*/
                    
            }
        }
            System.out.println("COlor " + Color.RED.getRGB());
        b.setRGB(0, 0, lignes, colonnes, pixels, 0, lignes);
        ImageIO.write(b, "png", new File("test.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
        for (int i = 0; i < this.getLigne(); i++) {
            double value = rn.nextDouble()*2 - 1;
            v.setElement(i,value);
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