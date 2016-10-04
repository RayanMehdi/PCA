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
    
    public Image(String nom){
        this.nomFichier=nom;
    }
    
    public Matrice MatriceNoirBlanc(){
        try {
        BufferedImage image = ImageIO.read(new File(this.nomFichier));
         
        int largeurImage = image.getWidth();
        int hauteurImage = image.getHeight();
         
        matrix=new Matrice(largeurImage,hauteurImage);
        
        
        Color couleur;
        for(int colonne = 0; colonne < largeurImage; colonne++){
            for(int ligne = 0; ligne < hauteurImage; ligne++){
                
                couleur = new Color(image.getRGB(colonne, ligne), false);
                // Traitement ici
                //System.out.print(image.getRGB(colonne, ligne)+" | ");
                //System.out.print(couleur.getRed()+" "+couleur.getGreen()+" "+couleur.getBlue()+" | ");
                matrix.setElement(ligne, colonne, couleur.getRed());
                //matrix.setElement(ligne, colonne, couleur.getRGB());
                
            }
            //System.out.println("");
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
            System.out.println("");
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
