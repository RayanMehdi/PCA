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
public class Test {
    
    Matrice m = new Matrice(3,3);
    Vecteur v = new Vecteur(3);
    Image imag = new Image("test.jpg");
    public Test(){
        
        
        for( int i = 0; i < m.getColonnes(); i++ ){
               for( int j = 0; j < m.getLigne(); j++ ){
                   m.setElement(j, i, 2);
               }
        }
        
        for(int i = 0; i < v.getTaille() ; i++){
            v.setElement(i, 1);
        }
        imag.lectureImage();
        //m.aff_matrice();
        //v.aff_vecteur();
        
    }
    
}
