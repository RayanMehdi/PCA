/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pca;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Jo'
 */
public class Image {
    private String nomFichier;
    private Matrice matrix;

    public String getNomFichier() {
        return nomFichier;
    }
    
    
    
    public Image(String nom){
        this.nomFichier=nom;
    }
    
    public Matrice toMatrice(char c){
        try {
            System.out.println(this.nomFichier);
        BufferedImage image = ImageIO.read(new File(this.nomFichier));
         
        int largeurImage = image.getWidth();
        int hauteurImage = image.getHeight();
        
            System.out.println(largeurImage + " " + hauteurImage);
        
        matrix=new Matrice(largeurImage,hauteurImage);
        
        
        Color couleur;
        for(int ligne = 0; ligne < largeurImage; ligne++){
            for(int colonne = 0; colonne < hauteurImage; colonne++){
                //System.out.println(" Ligne : " + ligne + " et col : " + colonne);
                couleur = new Color(image.getRGB(ligne, colonne), false);
                // Traitement ici
                //System.out.print(image.getRGB(ligne, colonne)+" | ");
                //System.out.print(couleur.getRed()+" "+couleur.getGreen()+" "+couleur.getBlue()+" | ");
                switch(c){
                    case 'R':
                        matrix.setElement(ligne, colonne, couleur.getRed());
                        break;
                    case 'G':
                        matrix.setElement(ligne, colonne, couleur.getGreen());
                        break;
                    case 'B':
                        matrix.setElement(ligne, colonne, couleur.getBlue());
                        break;
                    case 'M':
                        matrix.setElement(ligne, colonne, couleur.getRGB());
                        break;
                }
                
                
               
                
                //matrix.setElement(ligne, colonne, couleur.getRGB());
                
            }
            
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return matrix;
}
    
    public void lectureImage() {
    try {
        BufferedImage image = ImageIO.read(new File(this.nomFichier));
         
        int largeurImage = image.getWidth();
        int hauteurImage = image.getHeight();
         
        
        
        
        Color couleur;
        for(int colonne = 0; colonne < largeurImage; colonne++){
            for(int ligne = 0; ligne < hauteurImage; ligne++){
                //couleur = new Color(image.getRGB(colonne, ligne), false);
                couleur = new Color(image.getRGB(colonne, ligne), false);
                // Traitement ici
                //System.out.print(image.getRGB(colonne, ligne)+" | ");
                //System.out.print(couleur.getRed()+" "+couleur.getGreen()+" "+couleur.getBlue()+" | ");
                
            }
            
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    
}
