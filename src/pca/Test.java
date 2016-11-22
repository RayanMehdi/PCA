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
public class Test {

    private Image imag;

    public Image getImag() {
        return imag;
    }
    
    

    public Test(String name_image){
        
        this.imag = new Image(name_image);
        
        
        //c.getMvp().creerImage();
        
        //v.aff_vecteur();

       // v.aff_vecteur();

        
    }
    
    public void init(float pourcentage_compression){
        Matrice m=imag.MatriceNoirBlanc();
        
        Vecteur v = new Vecteur(m.getLigne());
        
        CalculMatriciel c = new CalculMatriciel(m, v, pourcentage_compression);
        c.calcul_valeurpropre();
        
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
    
}
