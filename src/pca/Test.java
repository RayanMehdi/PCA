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

    Image imag = new Image("test1.png");

    public Test(){
        
        
        /*for( int i = 0; i < m.getColonnes(); i++ ){
               for( int j = 0; j < m.getLigne(); j++ ){
                   m.setElement(j, i, 2);
               }
        }*/
        /*
        m.setElement(0, 0, 1);
        m.setElement(1, 0, 4);
        m.setElement(0, 1, 4);
        m.setElement(2, 0, 5);
        m.setElement(0, 2, 5);
        m.setElement(1, 2, 6);
        m.setElement(2, 1, 6);
        m.setElement(1, 1, 2);
        m.setElement(2, 2, 3);
        */

        //imag.();
        
        Matrice m=imag.MatriceNoirBlanc();
        
        //m.aff_matrice();
        Vecteur v = new Vecteur(m.getLigne());
                for(int i = 0; i < v.getTaille() ; i++){
            v.setElement(i, 1);
        }

        CalculMatriciel c = new CalculMatriciel(m, v, 0.5);
        c.calcul_valeurpropre();
        //c.affTab();
        m.creerImage();
        
        //v.aff_vecteur();

       // v.aff_vecteur();

        
    }

    
}
