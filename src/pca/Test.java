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
import javax.imageio.ImageIO;

/**
 *
 * @author rayanmehdi1
 */
public class Test {

    //test10.png pour une image 10x10
    Image imag = new Image("sans.png");

    public Test(){
        
       
        Matrice mR=imag.toMatrice('R');
        Matrice mG=imag.toMatrice('G');
        Matrice mB=imag.toMatrice('B');
        Vecteur v = new Vecteur(mR.getLigne());
        
       // v = m.random();
                
        double val=0.3;
        CalculMatriciel cR = new CalculMatriciel(mR, v, val);
        CalculMatriciel cG = new CalculMatriciel(mG, v, val);
        CalculMatriciel cB = new CalculMatriciel(mB, v, val);
        //mR=(Matrice) cR.calcul_valeurpropre().clone();
        //mG=(Matrice) cG.calcul_valeurpropre().clone();
        //mB=(Matrice) cB.calcul_valeurpropre().clone();
        
        creerImage(cR.calcul_valeurpropre(), cG.calcul_valeurpropre(), cB.calcul_valeurpropre());
        
        
        //c.affTab();
        //c.getMvp().creerImage();
        
        //v.aff_vecteur();

       // v.aff_vecteur();

        
    }
    
        public Matrice multiplicate(Matrice m, Matrice m2){
        Matrice mReturn = new Matrice( m.getLigne(), m.getColonnes());
        double somme =0;
        for (int i = 0; i < m.getLigne(); i++) {
            for (int j = 0; j < m.getColonnes(); j++) {
                for (int k = 0; k < m.getColonnes(); k++) {
                    somme += m.getElement(i, k) * m2.getElement(k, i);
                }
                mReturn.setElement(j, i, somme);
                somme = 0;
            }
        }
        return mReturn;
    }
    public Matrice MatriceRandom(){
        Random rn = new Random();
        rn.setSeed(24);
        Matrice m = new Matrice(5, 5);
        for(int i=0;i<m.getLigne();i++){
            for(int j=0;j<m.getColonnes();j++){
                m.setElement(i, j, rn.nextDouble()*255);
            }
        }
        m.aff_matrice();
        return m;
    }
    
    
    
    
    public void creerImage(Matrice R,Matrice G,Matrice B){
        
        try{
        
        //TYPE_INT_RGB or TYPE_BYTE_GRAY
        BufferedImage b = new BufferedImage(R.getLigne(), R.getColonnes(), BufferedImage.TYPE_INT_RGB);
        int[] pixels = new int[R.getLigne() * R.getColonnes()];
        for(int x = 0; x < R.getLigne(); x++) {
            for(int y = 0; y < R.getColonnes(); y++) {
                
                               
                if (R.getElement(x, y) < 0) {
                    R.setElement(x, y,R.getElement(x, y) * -1);
                }
                if (G.getElement(x, y) < 0) {
                    G.setElement(x, y,G.getElement(x, y) * -1);
                }
                if (B.getElement(x, y) < 0) {
                    B.setElement(x, y,B.getElement(x, y) * -1);
                }
                if (R.getElement(x, y) > 255) {
                    R.setElement(x, y,255);
                }
                if (G.getElement(x, y) > 255) {
                    G.setElement(x, y,255);
                }
                if (B.getElement(x, y) > 255) {
                    B.setElement(x, y,255);
                }
                //this.matr[x][y] = this.matr[x][y]%255;
                //System.out.println("Rouge :"+R.getElement(x, y));
                //System.out.println("vert :"+G.getElement(x, y));
                //System.out.println("bleu :"+B.getElement(x, y));
                pixels[y*R.getColonnes() + x] = new Color((int) R.getElement(x, y),(int) G.getElement(x, y),(int) B.getElement(x, y)).getRGB();
                
                /*
                    Color col = new Color((float)this.matr[x][y], (float)this.matr[x][y] , (float)this.matr[x][y]);
                    //Color col = new Color((int)this.matr[x][y]);
                    int rgb = col.getRGB();
                    System.out.println(rgb);
                    
                    //int rgb = (int)this.matr[x][y];
                    b.setRGB(y, x, rgb);*/
                    
            }
        }
            //System.out.println("COlor " + Color.RED.getRGB());
        b.setRGB(0, 0, R.getLigne(), R.getColonnes(), pixels, 0, R.getLigne());
        ImageIO.write(b, "png", new File("test.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
	}
       
        
    }
}
